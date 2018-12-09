package com.example.tecsup.googlemapv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class destinos extends AppCompatActivity {

    String destino="";
    String latitud="";
    String longitud="";
    String info="";
    String urlImagen="";
    TextView lblDestino, lblCoordenadas, lblInfo;
    ImageView imagenR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos);

        lblDestino = findViewById(R.id.lblDestino);
        lblCoordenadas = findViewById(R.id.lblCoordenadas);
        lblInfo = findViewById(R.id.lblInfo);
        imagenR = findViewById(R.id.imagenR);

        Bundle recibidos = this.getIntent().getExtras();
        if(recibidos !=null) {
            destino = getIntent().getExtras().getString("destino");
            latitud = getIntent().getExtras().getString("latitud");
            longitud = getIntent().getExtras().getString("longitud");
            urlImagen = getIntent().getExtras().getString("url");
            info = getIntent().getExtras().getString("info");
        }
        lblDestino.setText(destino);
        lblCoordenadas.setText(latitud + " , " + longitud);
        lblInfo.setText(info);
        loadImageFromUrl(urlImagen);


    }
    public void volverLista(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadImageFromUrl(String urlImagen) {
        Picasso.with(this).load(urlImagen).placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher)//if error
                .into(imagenR, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

}
