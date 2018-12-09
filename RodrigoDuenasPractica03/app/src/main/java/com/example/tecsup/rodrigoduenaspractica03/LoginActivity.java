package com.example.tecsup.rodrigoduenaspractica03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM contactos" ;

        Cursor cursor = db.rawQuery(query, null);
        ListView tv =findViewById(R.id.hola);

        String cad = "";
        if (cursor.moveToFirst()) {
            do {
                String xid  = cursor.getString(0);
                String xnom = cursor.getString(1);
                String xdir = cursor.getString(2);
                String xcel = cursor.getString(3);
                cad += xcel + "\n";
            } while (cursor.moveToNext());
            tv.setText(cad);
            cursor.close();
        }
        db.close();
    }
    public void mensaje_onclick(View v) {
        Toast.makeText(getApplicationContext(),
                "Ya me faltaba poquito we :(", Toast.LENGTH_SHORT).show();

    }
}