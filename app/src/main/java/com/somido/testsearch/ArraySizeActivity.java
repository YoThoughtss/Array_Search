package com.somido.testsearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ArraySizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_size);

        EditText arraySizeInput = findViewById(R.id.array_size_input);
        Button nextButton = findViewById(R.id.next_button);
        arraySizeInput.setTransformationMethod(null);
        nextButton.setOnClickListener(v ->{
            String sizeText = arraySizeInput.getText().toString();
            if(!sizeText.isEmpty()){
                int size = Integer.parseInt(sizeText);

                    if (size > 0){
                        Intent intent = new Intent(ArraySizeActivity.this, ArrayInputActivity.class);
                        intent.putExtra("ARRAY_SIZE",size);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Please Enter a Valid Size!", Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(ArraySizeActivity.this, "Size cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}