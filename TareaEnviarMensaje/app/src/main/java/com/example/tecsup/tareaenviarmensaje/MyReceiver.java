package com.example.tecsup.tareaenviarmensaje;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        String numero="";

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                numero += "SMS de: " + msgs[i].getOriginatingAddress();
                str += "Mensaje: ";
                str += msgs[i].getMessageBody().toString();
                str += "";
            }

            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
            iniciarActividad(context,numero,str);
        }

    }
    public void iniciarActividad(Context context,String numero, String mensaje){
        Intent intentone = new Intent(context.getApplicationContext(), Enviar.class);
        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        intentone.putExtra("mensaje",mensaje);
        intentone.putExtra("numero",numero);
        context.startActivity(intentone);
    }

}