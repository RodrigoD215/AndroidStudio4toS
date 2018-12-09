package com.example.tecsup.proyectosqlitev3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarCompromisos extends AppCompatActivity {

ListView Listal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromisos);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM compromisos" ;

        Cursor cursor = db.rawQuery(query, null);
        Listal=(ListView)findViewById(R.id.ListaC);
        ArrayList<String>lista = new ArrayList<>();
        String cad = "";
        if (cursor.moveToFirst()) {
            do {
                String xid  = cursor.getString(0);
                String xfecha = cursor.getString(1);
                String xdescri = cursor.getString(2);
                cad = xid +"\nFecha:" + xfecha + "\nDescripcion:" + xdescri + "  \n ";
                lista.add(cad);
            } while (cursor.moveToNext());
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,lista);
            Listal.setAdapter(adapter);
            cursor.close();
        }
        db.close();
    }
    public void regresar_onclick(View v) {
        finish();
    }
}

