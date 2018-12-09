
package com.example.tecsup.serviceweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }

    public void menuprincipal(View v){
        Intent ListSong = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ListSong);

    }
}
