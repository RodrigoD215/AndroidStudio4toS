package com.example.tecsup.rodrigoduenaspractica03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class receptorMen extends AppCompatActivity {

    TextView lblNumero;
    int contadorLlamada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor_men);

        lblNumero = findViewById(R.id.lblNumero);
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            String numero = getIntent().getExtras().getString("numero");
            lblNumero.setText(numero + "");
        }

    }}
