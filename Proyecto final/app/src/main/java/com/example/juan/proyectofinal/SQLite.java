package com.example.juan.proyectofinal;
    import java.util.ArrayList;
    import java.util.List;
//
    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.util.Log;


public class SQLite {

        private static SQLiteHelper sqliteHelper;
        private static SQLiteDatabase db;

        /** Constructor de clase */
        public SQLite(Context context)
        {
            sqliteHelper = new SQLiteHelper( context );
        }

        /** Abre conexion a base de datos */
        public void abrir(){
            //Log.i("SQLite", "Se abre conexion a la base de datos " + sqliteHelper.getDatabaseName() );
            db = sqliteHelper.getWritableDatabase();
        }

        /** Cierra conexion a la base de datos */
        public void cerrar()
        {
           //Log.i("SQLite", "Se cierra conexion a la base de datos " + sqliteHelper.getDatabaseName() );
            sqliteHelper.close();
        }

        /**
         * Metodo para agregar un nuevo registro.
         * Devuelve true si se consigue o false si da error.
         * */
        public boolean addRegistro( Persona trab )
        {
            if( trab.getNombre().length()> 0 )
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put( sqliteHelper.__campo_nombre2 , trab.getNombre());
                contentValues.put( sqliteHelper.__campo_fechanac , trab.getFecha());
                contentValues.put( sqliteHelper.__campo_pais , trab.getPais());
                contentValues.put( sqliteHelper.__campo_sexo , trab.getSexo());
                contentValues.put(sqliteHelper.__campo_ingles, trab.getIngles());
                //Log.i("SQLite", "Nuevo registro " );
                return ( db.insert( sqliteHelper.__tabla2__ , null, contentValues ) != -1 )?true:false;
            }
            else
                return false;
        }

        /**
         * Metodo que devuelve el ID del ultimo trabajador registrado
         * Devuelve -1 si no existen registros
         * */
        public int getUltimoID()
        {
            int id = -1;
            //query(String table,
            //String[] columns,
            //String selection, String[] selectionArgs, String groupBy, String having,
            //String orderBy, String limit)
            Cursor cursor = db.query( sqliteHelper.__tabla2__ ,
                    new String[]{ sqliteHelper.__campo_id2 },
                    null, null, null,null,
                    sqliteHelper.__campo_id2 + " DESC ", "1");
            if( cursor.moveToFirst() )
            {
                do
                {
                    id = cursor.getInt(0);
                } while ( cursor.moveToNext() );
            }
            return id;
        }
        /**
         * Borra registro, le pasamos id del registro a borrar
         * */
        public boolean borrar_registro( int id )
        {
            return  (db.delete( sqliteHelper.__tabla2__ , sqliteHelper.__campo_id2 + " = " + id ,  null) > 0) ? true:false;
        }
        /**
         * Obtiene todos los registros de la base de datos
         * */
        public Cursor getRegistros()
        {
            return db.query( sqliteHelper.__tabla2__ ,
                    new String[]{
                            sqliteHelper.__campo_id2 ,
                            sqliteHelper.__campo_nombre2,
                            sqliteHelper.__campo_fechanac,
                            sqliteHelper.__campo_pais,
                            sqliteHelper.__campo_sexo,
                            sqliteHelper.__campo_ingles
                    },
                    null, null, null, null, null);
        }
        /**
         * Obtiene un registro
         * */
        public Cursor getRegistro( int id )
        {
            return db.query( sqliteHelper.__tabla2__ ,
                    new String[]{
                            sqliteHelper.__campo_id2 ,
                            sqliteHelper.__campo_nombre2,
                            sqliteHelper.__campo_fechanac,
                            sqliteHelper.__campo_pais,
                            sqliteHelper.__campo_sexo,
                            sqliteHelper.__campo_ingles
                    },
                    sqliteHelper.__campo_id2 + " = " + id ,
                    null, null, null, null);
        }

        /**
         * Devuelve un arraylist con los datos formateados de un registro
         * */
        public ArrayList<String> getFormatListTrab( Cursor cursor )
        {
            ArrayList<String> listData = new ArrayList<String>();
            String item = "";
            if( cursor.moveToFirst() )
            {
                do
                {
                    item += "ID: [" + cursor.getInt(0) + "]\r\n";
                    item += "Nombre: " + cursor.getString(1) + "\r\n";
                    item += "Fecha de Nacimiento: " + cursor.getString(2) + "\r\n";
                    item += "Pais: " + cursor.getString(3) + "\r\n";
                    item += "Sexo: " + cursor.getString(4) + "\r\n";
                    item += "Habla ingles: " + cursor.getString(5) + "";
                    listData.add( item );
                    item="";

                } while ( cursor.moveToNext() );
            }
            return listData;
        }
        public void addPaises (){
            String[] paises={"Andorra", "España", "Inglaterra", "Francia", "Alemania", "Italia", "Portugal", "Grecia"};
            ContentValues contentValues2 = new ContentValues();
            for(int i=0;i<paises.length;i++) {
                contentValues2.put(sqliteHelper.__campo_nombre1, paises[i]);
                db.insert(sqliteHelper.__tabla1__, null, contentValues2);
            }
        }

        /**Devuelve todos los paises para poblar el spinner
         *
         */
        public static Cursor getPaises(){
        /*
        Seleccionamos todas las filas de la tabla paises
         */
            return db.rawQuery(
                    "select * from " + sqliteHelper.__tabla1__, null);
        }

        public static List<Pais> getPaises2(){
            List<Pais> paisesArray = new ArrayList<Pais>();
            Cursor c = db.rawQuery("select * from " + sqliteHelper.__tabla1__+" GROUP BY id", null);

            if (c.moveToFirst()) {
                do {
                    Log.i("*********************",c.getString(1)+"");
                    String pais = c.getString(1);
                    Pais p = new Pais();
                    p.setId(c.getInt(c.getColumnIndex(sqliteHelper.__campo_id1)));
                    p.setNombre(c.getString(c.getColumnIndex(sqliteHelper.__campo_nombre1)));
                    paisesArray.add(p);
                } while (c.moveToNext());
            }

            return paisesArray;
        }

    }


