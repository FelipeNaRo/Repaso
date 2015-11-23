package com.example.dam2.repaso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Repaso2 extends Activity {

    Spinner provincia;
    Spinner localidad;
    ArrayAdapter adap_provincia;
    ArrayAdapter adap_localidad;
    TextView nombre;
    EditText nomb;
    Context context=Repaso2.this;
    Button boton;
    Switch nota;
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaso2);
        provincia = (Spinner) findViewById(R.id.provincia);
        localidad = (Spinner) findViewById(R.id.localidad);
        nombre=(TextView)findViewById(R.id.muestraNombre);
        nombre.setText("Nombre:");
        nomb=(EditText)findViewById(R.id.nombre);
        boton= (Button) findViewById(R.id.boton);
        nota=(Switch) findViewById(R.id.nota);
        Bundle b = getIntent().getExtras();
        contador=b.getInt("contador");
        ArrayList <String> aProvincia=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.provincias)));
        adap_provincia= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,aProvincia);
        provincia.setAdapter(adap_provincia);
        adap_localidad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               {

                    switch (parent.getId()) {
                        case R.id.provincia:

                            TypedArray arrayLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                            CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                            arrayLocalidades.recycle();
                            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item, android.R.id.text1, localidades);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            localidad.setAdapter(adapter);

                            break;

                        case R.id.localidad:

                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ciudad=localidad.getSelectedItem().toString();
                String prov=provincia.getSelectedItem().toString();
                String check;
                if(nota.isChecked()){check="si";
                }else{check="no";}


                Intent i= getIntent();
                i.putExtra("nota", check);
                i.putExtra("nombre", nomb.getText().toString());
                i.putExtra("provincia",prov);
                i.putExtra("ciudad",ciudad);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repaso2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
