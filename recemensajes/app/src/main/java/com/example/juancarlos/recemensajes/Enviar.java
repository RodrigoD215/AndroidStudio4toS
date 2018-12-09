package com.example.juancarlos.recemensajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Enviar extends AppCompatActivity {
    TextView tnumero;
    TextView tmensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);
        tnumero = (TextView) findViewById(R.id.txtNumero);
        tmensaje = (TextView) findViewById(R.id.txtMensaje);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            String numero = getIntent().getExtras().getString("numero");
            String mensaje = getIntent().getExtras().getString("mensaje");
            tnumero.setText(numero + " ");
            tmensaje.setText(mensaje +" ");
        }
    }


}
