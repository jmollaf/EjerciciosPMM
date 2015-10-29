package com.example.mati.formasimplementareventos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context ctx=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx=this;
        Button cmdTres = (Button)findViewById(R.id.cmdTres);
        cmdTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Pulsado boton Tres",
                        Toast.LENGTH_SHORT).show();
            }
        });
   
}
    public void cmdDos_click(View v) {
        Toast.makeText(this, "Pulsado botón Dos", Toast.LENGTH_SHORT).show();
    }
}

