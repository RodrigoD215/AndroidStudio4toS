package com.example.tecsup.receptoresv3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class llamada extends AppCompatActivity {

    TextView lblNumero;
    int contadorLlamada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        lblNumero = findViewById(R.id.lblNumero);
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            String numero = getIntent().getExtras().getString("numero");
            lblNumero.setText(numero + "");
        }

    }

    public void guardarNumero(View v){
        // se apertura archivo de preferencias, para guardar datos en memoria
        SharedPreferences datos = getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();
        editor.putString("numero" + contadorLlamada++,lblNumero.getText().toString());
        editor.commit();

        Intent mostrarNumeros = new Intent(this,MainActivity.class);
        startActivity(mostrarNumeros);
        finish();
    }

    public void cerrar(View v){
        finish();
    }





}
