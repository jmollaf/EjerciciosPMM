package com.example.mati.ejerciciocolores;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button buttonSelect = (Button) findViewById(R.id.buttonSelect);
        final TextView textView = (TextView) findViewById(R.id.textViewColor);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int radioSelected = radioGroup.getCheckedRadioButtonId();

                if (radioSelected == R.id.radioButtonRed) {
                    textView.setBackgroundColor(Color.RED);
                } else if (radioSelected == R.id.radioButtonBlue) {
                    textView.setBackgroundColor(Color.BLUE);
                } else if (radioSelected == R.id.radioButtonGreen) {
                    textView.setBackgroundColor(Color.GREEN);
                } else {
                    showToast("No hay ningún color seleccionado");
                }
            }
        });

        final Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                radioGroup.clearCheck();
                textView.setBackgroundColor(Color.BLACK);
            }
        });

        final Button buttonActivity = (Button) findViewById(R.id.buttonChange);
        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(MainActivity.this, ejer2.class);
                startActivity(i);
            }
        });
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}