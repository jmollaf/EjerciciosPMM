package com.example.mati.transicionimagenes;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LO COMENTADO ES SIN USAR EL ACTIVITYMAIN.XML
        //ImageView imagen = new ImageView(this);
        setContentView(R.layout.activity_main);//imagen);
        TransitionDrawable mi_transicion = (TransitionDrawable)
                getResources().getDrawable(R.drawable.transicion);
        ImageView imagen = (ImageView) findViewById(R.id.imagenTransicion);
        imagen.setImageDrawable(mi_transicion);
        mi_transicion.startTransition(2000);
    }

}
