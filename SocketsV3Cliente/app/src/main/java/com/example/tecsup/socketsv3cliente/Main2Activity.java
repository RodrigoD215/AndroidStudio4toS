package com.example.tecsup.socketsv3cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText txtNick, txtIp,txtPuerto;
    Button Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtNick = findViewById(R.id.txtNick);
        txtIp = findViewById(R.id.txtIp);
        txtPuerto = findViewById(R.id.txtPuerto);

    }
    public void ingresar (View view){
        Intent parametros = new Intent(this,MainActivity.class);
        parametros.putExtra("nick",txtNick.getText().toString());
        parametros.putExtra("Ip",txtIp.getText().toString());
        parametros.putExtra("Puerto",txtPuerto.getText().toString());
        startActivity(parametros);
    }
}
