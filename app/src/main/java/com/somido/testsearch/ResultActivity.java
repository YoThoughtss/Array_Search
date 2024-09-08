package com.somido.testsearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView explanationText;
    private Button newEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result_text);
        explanationText = findViewById(R.id.explanation_text);
        newEntryButton = findViewById(R.id.btn_entry);

        int[] arrayList = getIntent().getIntArrayExtra("ARRAY_LIST");
        int searchElement = getIntent().getIntExtra("SEARCH_ELEMENT", -1);

        String algorithm;
        int index;

        if(isSorted(arrayList)){
            algorithm = "Binary Search";
            index = binarySearch(arrayList, searchElement);
            explanationText.setText("Binary Search was used because the array is sorted.");
        }else{
            algorithm = "Linear Search";
            index = linearSearch(arrayList, searchElement);
            explanationText.setText("Linear Search was used because the array is unsorted.");
        }

        if(index != -1){
            resultText.setText("Element found at index: " + index + " using " + algorithm);
        }else{
            resultText.setText("Element not found");
        }

        newEntryButton.setOnClickListener(v-> {
            Intent intent = new Intent(ResultActivity.this, ArraySizeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    private int linearSearch(int[] array, int element){
        for (int i=0; i < array.length; i++){
            if (array[i] == element){
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int[] array, int element){
        int left = 0, right = array.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(array[mid] == element){
                return mid;
            }

            if (array[mid] < element){
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }
        return -1;
    }
}