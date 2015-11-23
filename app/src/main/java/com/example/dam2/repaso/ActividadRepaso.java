package com.example.dam2.repaso;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadRepaso extends Activity {

    TextView marcador;
    Button boton;
    int contador=0;
    final int subactivity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_repaso);
        boton=(Button) findViewById(R.id.boton);
        marcador=(TextView)findViewById(R.id.marcador);
        final  Notification.Builder nb =new Notification.Builder(this);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                marcador.setText(Integer.toString(contador));

                nb.setContentTitle("Pulsaciones");
                nb.setContentText("Llevas " + Integer.toString(contador) + " pulsaciones");
                nb.setSmallIcon(R.mipmap.ic_launcher);
                NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(1, nb.build());
                if (contador==5){
                    cambioPantalla();
                }
            }
        });

    }

    public void cambioPantalla(){

        Intent i = new Intent(getApplicationContext(),Repaso2.class);
        Bundle b= new Bundle();
        b.putInt("contador",contador);
        i.putExtras(b);
        startActivityForResult(i, subactivity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_repaso, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case subactivity:

        Bundle b=data.getExtras();
        String n=b.getString("nombre");
        String p=b.getString("provincia");
        String l=b.getString("ciudad");
        String c=b.getString("nota");
        Toast.makeText(getApplicationContext(), "Don " + n + " de " + l + " de la provincia de " + p + " ha realizado; " + contador + " intentos", Toast.LENGTH_LONG);
        break;
        }
    }
}
