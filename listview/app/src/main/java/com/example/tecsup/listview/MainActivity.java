package com.example.tecsup.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    private String lenguajeProgramacion[]=new String[]{"Rodrigo Alejandro","Alexis Gym","Klau Dueñas","Anguie de Guillen","Anyuuu","Glenflow",
            "Alexander Valdivia ","Brian Cupe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setListAdapter(new ArrayAdapter<String>(
                this, R.layout.fila_lista,
                R.id.nombre_fila_lista,lenguajeProgramacion));
    }
}
