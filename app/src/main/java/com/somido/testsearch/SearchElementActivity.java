package com.somido.testsearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SearchElementActivity extends AppCompatActivity {

    private int[] arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_element);

        arrayList = getIntent().getIntArrayExtra("ARRAY_LIST");
        EditText searchElementInput = findViewById(R.id.search_element_input);
        Button searchButton = findViewById(R.id.search_button);
        searchElementInput.setTransformationMethod(null);
        searchButton.setOnClickListener(v ->{
            String searchText = searchElementInput.getText().toString();

            if (!searchText.isEmpty()){
                int element = Integer.parseInt(searchText);
                Intent intent = new Intent(SearchElementActivity.this, ResultActivity.class);
                intent.putExtra("ARRAY_LIST", arrayList);
                intent.putExtra("SEARCH_ELEMENT", element);
                startActivity(intent);
            }else{
                Toast.makeText(SearchElementActivity.this, "Please enter an element to search!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}