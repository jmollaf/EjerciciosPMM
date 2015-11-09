package com.example.mati.examenjuanmolla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static String pizzas[] = {"Margarita", "Tres quesos", "Barbacoa", "Carbonara"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner miSpinner = (Spinner) findViewById(R.id.spinnerPizzas);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizzas);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        RadioButton radioLocal = (RadioButton) findViewById(R.id.radioLocal);
        RadioButton radioDomicilio = (RadioButton) findViewById(R.id.radioDomicilio);
        CheckBox chkGrande = (CheckBox) findViewById(R.id.checkBoxGrande);
        CheckBox chkMas = (CheckBox) findViewById(R.id.checkBoxMas);
        CheckBox chkMenos = (CheckBox) findViewById(R.id.checkBoxExtra);
        TextView txtUnidades = (TextView) findViewById(R.id.textViewUnidades);
        TextView txtTotal = (TextView) findViewById(R.id.textViewTotal);

    }
   
}
