package com.example.mati.formasimplementareventos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cmdDos_click(View v) {
        Toast.makeText(this, "Pulsado botón Dos", Toast.LENGTH_SHORT).show();
    }
}
