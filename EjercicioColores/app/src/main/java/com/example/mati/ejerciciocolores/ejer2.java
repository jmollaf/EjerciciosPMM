package com.example.mati.ejerciciocolores;

//package institutoserpis.org.linearlayout;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;


public class ejer2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer2);

        final ImageButton buttonRed = (ImageButton) findViewById(R.id.imageButtonRed);
        final ImageButton buttonBlue = (ImageButton) findViewById(R.id.imageButtonBlue);
        final Button buttonAnterior = (Button) findViewById(R.id.buttonAnterior);
        final Button buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        final TextView textView = (TextView) findViewById(R.id.textViewColor);

        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundColor(Color.RED);
            }
        });

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundColor(Color.BLUE);
            }
        });

        buttonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ejer2.this, MainActivity.class);
                startActivity(i);
            }
        });

        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ejer2.this, ejer3.class);
                startActivity(i);
            }
        });
    }
}