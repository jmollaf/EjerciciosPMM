package com.example.juan.proyectofinal;

    import android.database.sqlite.SQLiteOpenHelper;
    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class SQLiteHelper extends SQLiteOpenHelper {

        //nombre de la base de datos
        private static final String __DATABASE = "dbTrabajadores";
        //versión de la base de datos
        private static final int __VERSION = 3;
        //nombre tablas y campos de tablas
        public final String __tabla1__= "Paises";
        public final String __campo_id1 = "id";
        public final String __campo_nombre1 = "Nombre";
        public final String __tabla2__ = "Trabajadores";
        public final String __campo_id2 = "id";
        public final String __campo_nombre2 = "Nombre";
        public final String __campo_fechanac = "FechaNac";
        public final String __campo_pais = "Pais";
        public final String __campo_sexo = "Sexo";
        public final String __campo_ingles = "Ingles";

	/*
	 * CREATE TABLE "Trabajador" (
	                "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ,
	                "Nombre" TEXT, "FechaNac" DATETIME, "Pais" INT FK, "Sexo" TEXT, "Ingles" TEXT )
	 * */
        private String sql1 = "CREATE TABLE " + __tabla1__ + "(" + __campo_id1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + __campo_nombre1 + " TEXT NOT NULL);";
        private String sql2 = "CREATE TABLE " + __tabla2__ + " (" + __campo_id2 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                __campo_nombre2 + " TEXT NULL, " + __campo_fechanac + " TEXT, " + __campo_pais + " INTEGER NOT NULL, " +__campo_sexo + " TEXT NULL, " + __campo_ingles + " TEXT NULL," +
                "FOREIGN KEY(" +__campo_pais +") REFERENCES " + __tabla2__+ "(" + __campo_id2 + "));";

        /**
         * Constructor de clase
         * */
        public SQLiteHelper(Context context) {
            super( context, __DATABASE, null, __VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL( this.sql1 );
            db.execSQL( this.sql2 );
        }

        @Override
        public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {
            if ( newVersion > oldVersion )
            {
                //eliminamos TABLAS tabla
                db.execSQL( "DROP TABLE IF EXISTS " + __tabla1__ );
                db.execSQL( "DROP TABLE IF EXISTS " + __tabla2__ );

                //y luego creamos las nuevas tablas
                db.execSQL( sql1 );
                db.execSQL( sql2 );
            }
        }

    }


