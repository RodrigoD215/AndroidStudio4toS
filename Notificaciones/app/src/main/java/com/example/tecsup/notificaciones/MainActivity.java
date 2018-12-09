package com.example.tecsup.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

    int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void agregarNotificacion(View v){
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder1 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icono)
                        .setContentTitle("Notificacion creada")
                        .setSound(alarm)
                        .setContentText("Abrir actividad")
                        .setOngoing(true)
                        .setAutoCancel(true)
                        .setNumber(ID);



        Intent notificacionIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificacionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder1.setContentIntent(contentIntent);
        long[] vibraPattern = { 1000, 1000, 1000, 1000,1000,1000 };
        builder1.setVibrate(vibraPattern);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder1.build());

        manager.notify(ID++,builder1.build());

    }

    


}
