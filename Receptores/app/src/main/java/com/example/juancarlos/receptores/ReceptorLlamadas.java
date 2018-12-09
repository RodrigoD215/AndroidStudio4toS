package com.example.juancarlos.receptores;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Juancarlos on 25/10/2017.
 */

public class ReceptorLlamadas extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,"Llamada Entrante",Toast.LENGTH_LONG).show();

        String estado = "", numero="";
        Bundle extras= intent.getExtras();
        if(extras != null){
            estado = extras.getString(TelephonyManager.EXTRA_STATE);
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                numero= extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                crearNotificacion(context);
                iniciarActividad(context,numero);
            }
        }

    }
    public void crearNotificacion(Context context){
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder= (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.tecsup)
                .setContentTitle("Llamada entrante")
                .setSound(alarm)
                .setContentText("Contesta por favor. Es el condor mendoza")
                .setAutoCancel(true)
                .setOngoing(true);
        Intent notificacionIntent= new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,notificacionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        long[] vibraPattern = {1000,1000,1000,1000,1000,1000};
        builder.setVibrate(vibraPattern);

        NotificationManager manager= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    public void iniciarActividad(Context context, String numero){
        Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
       intentone.putExtra("numero",numero);
        context.startActivity(intentone);
    }

}
