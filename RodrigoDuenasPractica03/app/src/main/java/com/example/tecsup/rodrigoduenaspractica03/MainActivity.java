package com.example.tecsup.rodrigoduenaspractica03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }

    public void contactos_onclick(View v){
        Intent intentContactos = new Intent(v.getContext(),
                Contactos.class);
        startActivity(intentContactos);
    }

}
