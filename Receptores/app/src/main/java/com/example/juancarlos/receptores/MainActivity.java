package com.example.juancarlos.receptores;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView txtNumero;
    TextView txtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int READ_PHONE_STATE=123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE);
        }
        txtNumero = (TextView) findViewById(R.id.txtNumero);




        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            String numero = getIntent().getExtras().getString("numero");
            txtNumero.setText(numero + "");
        }
    }
    public void llamar(View v){
        try{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + txtNumero.getText())));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
