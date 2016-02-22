package com.example.juan.proyectofinal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    //Variables para fecha
    private int mYear;
    private int mMonth;
    private int mDay;
    //
    private TextView txtFechaNac;
    private EditText txtName;
    private Spinner sPaises;
    private SimpleCursorAdapter paisesSpinnerAdapter;
    private Button btnRegistrar;
    private Button btnCancelar;
    private Button btnRegistros;
    private CheckBox chIdioma;
    private RadioGroup radioGroup;
    //
    String paisSeleccionado;
    //
    List<Pais> paisesX;
    //
    private SQLite sqlite;
    //DatePickerDialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    txtFechaNac.setText( ((mDay<10)?"0"+mDay:mDay) + "/" + ((mMonth<10)?"0"+mMonth:mMonth) + "/" + mYear );
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        chIdioma = (CheckBox) findViewById( R.id.chIdioma );
        txtName = (EditText) findViewById( R.id.txtName );
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar );
        btnRegistrar.setOnClickListener( this );
        btnCancelar = (Button) findViewById(R.id.btnCancelar );
        btnCancelar.setOnClickListener( this );
        btnRegistros = (Button) findViewById(R.id.btnRegistros );
        btnRegistros.setOnClickListener( this );
        radioGroup = (RadioGroup) findViewById( R.id.gpSexo );
        txtFechaNac = (TextView) findViewById(R.id.txtFechaNac );
        txtFechaNac.setOnClickListener( this );

        //base de datos
        sqlite = new SQLite(this);
        sqlite.abrir();
        sqlite.addPaises();
        //Obtenemos datos de la base de datos
        /*paisesSpinnerAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, sqlite.getPaises(),
                               new String []{sqlite.getPaises().getString(1)},new int[]{android.R.id.text1},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER );*/

        //String[] paises = getResources().getStringArray(R.array.paises);
        sPaises = (Spinner) findViewById(R.id.sPaises );

        paisesX = sqlite.getPaises2();
        AdaptadorPais adaptador = new AdaptadorPais(this);
        sPaises.setAdapter(adaptador);

        sPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paisSeleccionado = paisesX.get(position).getNombre();
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Asignamos el adaptador al spinner
        //sPaises.setAdapter(paisesSpinnerAdapter);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, paises );
        //sPaises.setAdapter(adapter);
        //Obtiene fecha actual y coloca en el textview
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //muestra la fecha de la forma 00/00/0000
        txtFechaNac.setText( ((mDay<10)?"0"+mDay:mDay) + "/" + ((mMonth<10)?"0"+mMonth:mMonth) + "/" + mYear );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() )
        {
            case R.id.btnRegistrar:
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById( radioButtonID );
                int index = radioGroup.indexOfChild( radioButton );
                //Registra en la base de datos
                Persona trabajador = new Persona(txtName.getText().toString(),txtFechaNac.getText().toString(), paisSeleccionado, (index == 0)?"Hombre":"Mujer", ( chIdioma.isChecked() )?"Si":"No" );
                if ( sqlite.addRegistro(trabajador))
                {
                    //recupera ID de ultimo registro y pasa como parametro
                    int id = sqlite.getUltimoID();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    Intent intent = new Intent( MainActivity.this, RegistroActivity.class );
                    intent.putExtras( bundle );
                    startActivity( intent );
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Error: Compruebe que los datos sean correctos", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancelar: sqlite.cerrar(); finish(); break;
            case R.id.btnRegistros:
                Intent iRegs = new Intent( MainActivity.this, RegistrosActivity.class );
                startActivity( iRegs );
                break;
            case R.id.txtFechaNac: verDatePicker(); break;
        }
    }

    /**
     * Metodo para mostrar un DatePickerDialog
     * */
    public void verDatePicker()
    {
        DatePickerDialog d = new DatePickerDialog( this , mDateSetListener, mYear, mMonth, mDay );
        d.show();
    }



    class AdaptadorPais extends ArrayAdapter<Pais> {

        public Activity context;

        public AdaptadorPais(Activity context) {
            super(context, R.layout.paises_view, paisesX);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.paises_view, null);

            TextView pais = (TextView) item.findViewById(R.id.textViewPaises);
            pais.setText(paisesX.get(position).getNombre()+"");

            return (item);
        }
    }

}
