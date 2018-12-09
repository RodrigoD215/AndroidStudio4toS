package com.example.tecsup.tareaenviarmensaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void  EnviarMensaje(String num, String msm){
        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(num,null,msm,null,null);
            Toast.makeText(getApplicationContext(),"Mensaje enviado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Mensaje enviado",Toast.LENGTH_SHORT).show();
        }
    }
}
