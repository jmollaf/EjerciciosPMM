package com.example.juan.proyectofinal;

/**
 * Created by juan on 19/02/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
public class SQLiteTrabajadorHelper extends SQLiteOpenHelper{

    String sqlCreate =
            "CREATE TABLE IF NOT EXISTS `Paises` (" +
            "  `id` INTEGER NOT NULL PRIMARY KEY," +
            "  `Nombre` TEXT NOT NULL," +
            "  );" +
            "CREATE TABLE IF NOT EXISTS `Trabajadores` (" +
            "  `id` INTEGER AUTOINCREMENT NOT NULL PRIMARY KEY," +
            "  `Nombre` TEXT NOT NULL," +
            "  `FechaNac` TEXT NOT NULL" +
            "  `Pais` INTEGER NOT NULL FOREIGN KEY (Pais) REFERENCES Paises(id)" +
            "  `Sexo` INTEGER NOT NULL" +
            "  `Ingles` INTEGER NOT NULL" +
            "  ); " ;
    String sqlInsert = "Insert into Paises set(Nombre) values ('España')";

    public SQLiteTrabajadorHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlInsert);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ( newVersion > oldVersion )
        {
            //eliminamos tablas
            db.execSQL( "DROP TABLE IF EXISTS Paises" );
            db.execSQL( "DROP TABLE IF EXISTS Trabajadores");
            //y luego creamos las nuevas tablas
            db.execSQL( sqlCreate );
        }
    }


}
