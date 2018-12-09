package com.example.tecsup.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opcion aun no disponible", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.ajustes) {
            Intent llamaractividad =new Intent(getApplicationContext(),actividad_configuracion.class);
            startActivity(llamaractividad);
            return true;
        }

        if (id == R.id.AcercaDe) {
            Intent llamaractividad =new Intent(getApplicationContext(),actividad_acercad.class);
            startActivity(llamaractividad);
            return true;
        }
        if (id == R.id.lupa) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Estas en la opcion buscar", Toast.LENGTH_LONG);
            toast1.show();
            return true;
        }
        if (id == R.id.mensaje) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Estas en la opcion Contactos", Toast.LENGTH_LONG);
            toast1.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}


