package com.example.tecsup.pasovariablesactividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class activity_profesores extends AppCompatActivity {

    ListView lista;
    String datosactividad01="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        lista= (ListView) findViewById(R.id.listadejuegos);
        String[] values = new String[] {
                "Yanina Villegaz",
                "Jose Carpio",
                "Elmer Zierra",
                "Alonso Flores",
                "Jimena Gomez"
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                values);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lista.getItemAtPosition(position);

                Intent intent=new Intent(activity_profesores.this,MainActivity.class);
                intent.putExtra("parametro",itemValue);
                intent.putExtra("datosactividad01",datosactividad01);
                startActivity(intent);
            }

        });

        Bundle recibidos=this.getIntent().getExtras();
        if(recibidos !=null){
            datosactividad01 = getIntent().getExtras().getString("Seleccionados");
        }
    }
}
