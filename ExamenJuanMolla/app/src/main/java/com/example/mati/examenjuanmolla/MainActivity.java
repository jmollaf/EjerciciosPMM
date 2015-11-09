package com.example.mati.examenjuanmolla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static String pizzas[] = {"Margarita", "Tres Quesos", "Barbacoa", "Romana"};
    int valorPizza=0;
    double incremento=1;
    int añadido=0;
    double total=0;
    int unidades=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner miSpinner = (Spinner) findViewById(R.id.spinnerPizzas);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizzas);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        final RadioButton radioLocal = (RadioButton) findViewById(R.id.radioLocal);
        final RadioButton radioDomicilio = (RadioButton) findViewById(R.id.radioDomicilio);
        final CheckBox chkGrande = (CheckBox) findViewById(R.id.checkBoxGrande);
        final CheckBox chkMas = (CheckBox) findViewById(R.id.checkBoxMas);
        final CheckBox chkExtra = (CheckBox) findViewById(R.id.checkBoxExtra);
        final EditText txtUnidades = (EditText) findViewById(R.id.EditTextUnidades);
        Button btnTotal = (Button) findViewById(R.id.btnTotal);
        final TextView txtTotal = (TextView) findViewById(R.id.textViewTotal);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        valorPizza = 10;
                                                    }
                                                    if (position == 1) {
                                                        valorPizza = 20;
                                                    }
                                                    if (position == 2) {
                                                        valorPizza = 30;
                                                    }
                                                    if (position == 3) {
                                                        valorPizza = 40;
                                                    }

                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {
                                                    valorPizza = 10;
                                                }
                                            }
        );

        btnTotal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (radioDomicilio.isChecked()){
                    incremento=1.1;
                }
                else{
                    if(radioLocal.isChecked()) {
                        incremento = 1.0;
                    }
                }
                if (chkGrande.isChecked()){
                    añadido+=1;
                }
                if (chkMas.isChecked()){
                    añadido+=1;
                }
                if (chkExtra.isChecked()){
                    añadido+=1;
                }
                 unidades=Integer.parseInt(txtUnidades.getText().toString());
                 total+=valorPizza;
                 total+=añadido;
                 total*=unidades;
                 total*=incremento;
                 txtTotal.setText(Double.toString(total));
                 total=0;
                 valorPizza=0;
                 añadido=0;
                 unidades=0;
                 incremento=0;
            }
        });

    }

}
