package com.example.juancarlos.recemensajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void enviar_onclick(View v) {
        String xnum = ((EditText)findViewById(R.id.txtnumero)).getText().toString();
        String xbody = ((EditText)findViewById(R.id.txtbody)).getText().toString();

        EnviarMensaje(xnum, xbody);

    }
    public void  EnviarMensaje(String num, String msm){
        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(num,null,msm,null,null);
            Toast.makeText(getApplicationContext(),"Mensaje enviado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Mensaje no enviado",Toast.LENGTH_SHORT).show();
        }
    }
}
