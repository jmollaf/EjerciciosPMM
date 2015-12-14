package com.example.mati.solobici;

/**
 * Created by mati on 14/12/15.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Vector;

public class VistaJuego extends View {
    //	COCHES	//
    private Vector<Grafico> Coches;	//Vector con los Coches
    private int numCoches = 5;		//Número inicial de Coches
    private int numMotos = 3;		//Fragmentos/Motos en que se dividirá un Coche

    public VistaJuego(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        Drawable graficoBici, graficoCoche, graficoRueda;
        //Obtenemos la imagen/recurso del coche
        graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);

        //Creamos un vector para contener todos los coches que irán por la pantalla
        //y lo rellenamos con gráficos de coches
        // con valores aleatorios para su velocidad, dirección y rotación.
        Coches = new Vector<Grafico>();
        for (int i=0; i<numCoches; i++) {
            Grafico coche = new Grafico(this, graficoCoche);
            coche.setIncX(Math.random() * 4 - 2);
            coche.setIncY(Math.random() * 4 - 2);
            coche.setAngulo((int) (Math.random() * 360));
            coche.setRotacion((int) (Math.random() * 8 - 4));
            Coches.add(coche);
        }
    }


        //Al comenzar y dibujar por primera vez la pantalla del juego
        //@Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            //Dibujamos los coches en posiciones aleatorias
            for (Grafico coche: Coches) {
                coche.setPosX(Math.random()*(w-coche.getAncho()));
                coche.setPosY(Math.random()*(h-coche.getAlto()));
            }
        }

        //@Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //Dibujamos cada uno de los coches
            for (Grafico coche: Coches) {
                coche.dibujaGrafico(canvas);
            }

        }
}

