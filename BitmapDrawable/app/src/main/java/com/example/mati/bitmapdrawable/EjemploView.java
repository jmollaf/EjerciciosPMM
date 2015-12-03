package com.example.mati.bitmapdrawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by mati on 3/12/15.
 */
public class EjemploView extends View {
    private BitmapDrawable miImagen;
    public EjemploView(Context contexto) {
        super(contexto);
        Resources res= contexto.getResources();
        miImagen = (BitmapDrawable) res.getDrawable(R.drawable.logo_cefire);
        miImagen.setBounds(new Rect(30,30,200,200));

    }
    protected void onDraw(Canvas canvas){
        miImagen.draw(canvas);
    }
}
