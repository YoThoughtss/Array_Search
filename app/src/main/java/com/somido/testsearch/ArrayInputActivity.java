package com.somido.testsearch;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class ArrayInputActivity extends AppCompatActivity {

    private EditText[] arrayInputs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_input);

        int size = getIntent().getIntExtra("ARRAY_SIZE", 0);
        LinearLayout arrayInputContainer = findViewById(R.id.array_input_container);
        arrayInputs = new EditText[size];

        for (int i = 0; i < size; i++){
            EditText editText = new EditText(this);
            int numberIndex = i+1;
            editText.setHint("Enter value for index " + numberIndex);
            editText.setGravity(Gravity.CENTER);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            editText.setTransformationMethod(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                editText.setImportantForAutofill(TextView.IMPORTANT_FOR_AUTOFILL_NO);
            }

            arrayInputs[i] = editText;
            arrayInputContainer.addView(editText);
        }
        Button nextButton = new Button(this);
        nextButton.setText("Next");
        arrayInputContainer.addView(nextButton);

        nextButton.setOnClickListener(v ->{
            int[] array = new int[size];
            boolean valid = true;


            for(int i = 0; i< size; i++) {
                String value = arrayInputs[i].getText().toString();
                if (!value.isEmpty()) {
                    array[i] = Integer.parseInt(value);
                } else {
                    valid = false;
                    Toast.makeText(ArrayInputActivity.this, "All value must be filled", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            if (valid){
                Intent intent = new Intent(ArrayInputActivity.this, SearchElementActivity.class);
                intent.putExtra("ARRAY_LIST", array);
                startActivity(intent);
            }
        });

    }
}