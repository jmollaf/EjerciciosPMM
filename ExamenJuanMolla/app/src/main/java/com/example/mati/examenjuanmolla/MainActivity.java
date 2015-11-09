package com.example.mati.examenjuanmolla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    final static String pizzas[] = {"Margarita", "Tres quesos", "Barbacoa", "Carbonara"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner miSpinner = (Spinner) findViewById(R.id.spinnerPizzas);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizzas);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); /* MIRAR BIEN( es diferente al ListaView) */
        miSpinner.setAdapter(miAdaptador);


    }
}
