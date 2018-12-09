package com.example.tecsup.rodrigoduenaspractica03;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Contactos extends AppCompatActivity {

    private final int idNotificacion_basica = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
    }
    public void regresar_onclick(View v) {
        finish();
    }

    public void grabar_onclick( View v ) {
        String xnom = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        String xdir = ((EditText)findViewById(R.id.txtDireccion)).getText().toString();
        String xcel = ((EditText)findViewById(R.id.txtCelular)).getText().toString();
        insertarContacto(xnom,xdir,xcel);
        Toast.makeText(v.getContext(),"Grabando registro",Toast.LENGTH_SHORT).show();
        CrearNotificacion();
    }

    private void insertarContacto(String xnom, String xdir, String xcel) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NOMBRE, xnom);
        cv.put(DatabaseHelper.DIRECCION, xdir);
        cv.put(DatabaseHelper.CELULAR, xcel);
        db.insert("contactos", DatabaseHelper.NOMBRE, cv);
        db.close();
    }
    private void CrearNotificacion() {

        try{
            NotificationCompat.Builder NotiBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.chica)
                            .setContentTitle("Aplicación actualizada")
                            .setContentText("Vuelve a iniciar sesión")
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setDefaults(Notification.DEFAULT_ALL)//Requiere permisos de Vibración.
                            .setAutoCancel(true);


            //Creamos un nuevo Intent que contiene la clase/Actividad a abrir
            Intent intentFinal = new Intent(this, LoginActivity.class);

            //Definimos la nueva actividad como nueva tarea
            intentFinal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            //Encapsulamos el intenFinal anteriormente creado dentro de un nuevo PendingIntent.
            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intentFinal,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );


            //Añadimos el contenido del Intent al NotificactionCompat.Builder
            NotiBuilder.setContentIntent(resultPendingIntent);

            //Obtenemos una instancia del servicio de NotificactionManager
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //Creamos y mostramos la notificación
            mNotifyMgr.notify(idNotificacion_basica, NotiBuilder.build());


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}
