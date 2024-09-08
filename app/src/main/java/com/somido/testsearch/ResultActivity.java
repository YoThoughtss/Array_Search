package com.somido.testsearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView explanationText;
    private Button newEntryButton;
    private Button searchAnotherButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result_text);
        explanationText = findViewById(R.id.explanation_text);
        newEntryButton = findViewById(R.id.btn_entry);
        searchAnotherButton = findViewById(R.id.search_another_btn);

        int[] arrayList = getIntent().getIntArrayExtra("ARRAY_LIST");
        int searchElement = getIntent().getIntExtra("SEARCH_ELEMENT", -1);

        String algorithm;
        List<Integer> indices;

        if(isSorted(arrayList)){
            algorithm = "Binary Search";
            indices = binarySearch(arrayList, searchElement);
            explanationText.setText("Binary Search was used because the array is sorted.");
        }else{
            algorithm = "Linear Search";
            indices = linearSearch(arrayList, searchElement);
            explanationText.setText("Linear Search was used because the array is unsorted.");
        }


        if(!indices.isEmpty()){
            resultText.setText("Element Found at Indices: " + indices + " Using " + algorithm);
        }else {
            resultText.setText("Element not found!");
        }

        newEntryButton.setOnClickListener(v-> {
            Intent intent = new Intent(ResultActivity.this, ArraySizeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        searchAnotherButton.setOnClickListener(v ->{
            Intent intent = new Intent(ResultActivity.this, SearchElementActivity.class);
            intent.putExtra("ARRAY_LIST", arrayList);
            startActivity(intent);
            finish();
        });

    }

    private boolean isSorted(int[] array){
        for(int i = 1; i < array.length; i++){

            if(array[i-1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> linearSearch(int[] array, int element){
        List<Integer> indices = new ArrayList<>();
        for (int i=0; i < array.length; i++){
            if (array[i] == element){
                indices.add(i);
            }
        }
        return indices;
    }

    private List<Integer> binarySearch(int[] array, int element){
        List<Integer> indices = new ArrayList<>();
        int left = 0, right = array.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == element) {
                indices.add(mid);

                int leftIndex = mid - 1;
                while (leftIndex >= 0 && array[leftIndex] == element) {
                    indices.add(leftIndex);
                    leftIndex--;
                }

                int rightIndex = mid + 1;
                while (rightIndex < array.length && array[rightIndex] == element) {
                    indices.add(rightIndex);
                    rightIndex++;
                }
                break;

            }

            if (array[mid] < element){
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }
        return indices;
    }
}