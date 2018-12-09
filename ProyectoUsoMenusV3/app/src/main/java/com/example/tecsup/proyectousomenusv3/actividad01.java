package com.example.tecsup.proyectousomenusv3;

import android.content.ClipData;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class actividad01 extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    ListView lista;
    ListView lista_lateral;
    DrawerLayout layout_lateral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);
        toolbar = findViewById(R.id.mi_toolbar);
        lista_lateral = findViewById(R.id.lista_lateral);
        layout_lateral = findViewById(R.id.layout_lateral);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerForContextMenu(lista);




        String[] opciones_menu_lateral = {"Opcion1", "Opcion2", "Opcion3", "Opcion4"};
        ArrayAdapter<String> adapter_lateral = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                opciones_menu_lateral);
        lista_lateral.setAdapter(adapter_lateral);

        lista_lateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                int posicion = i;
                String valorElemento = (String) lista_lateral.getItemAtPosition(posicion);
                Toast.makeText(getApplicationContext(), "Elemento" + valorElemento + "esta es la posicion" +
                        posicion, Toast.LENGTH_LONG).show();

            }

        });


    }



    @Override
    public void onCreateContextMenu(ContextMenu menucontextual, View v,ContextMenu.ContextMenuInfo menuInfo){
        int seleccionlista;
        if (v.getId()==R.id.lista){
            seleccionlista =((AdapterView.AdapterContextMenuInfo)menuInfo).position;
            menucontextual.setHeaderTitle(lista.getAdapter().getItem(seleccionlista).toString());
            Toast.makeText(getBaseContext(),"Elegiste el Contacto:"+
            lista.getAdapter().getItem(seleccionlista).toString(),Toast.LENGTH_SHORT).show();
            this.getMenuInflater().inflate(R.menu.menu_contextual1,menucontextual);
        }
        super.onCreateContextMenu(menucontextual, v, menuInfo);
    }




    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.vnombres:
                Toast.makeText(getBaseContext(),"Elegiste Nombres",Toast.LENGTH_LONG).show();
                return true;
            case R.id.vsemestres:
                Toast.makeText(getBaseContext(),"Elegiste Semestre",Toast.LENGTH_LONG).show();
                return true;
            case R.id.vemail:
                Toast.makeText(getBaseContext(),"Elegiste Email",Toast.LENGTH_LONG).show();
                return true;
            case R.id.vcarrera:
                Toast.makeText(getBaseContext(),"Elegiste Carrera",Toast.LENGTH_LONG).show();
                return true;
                default:
                    return super.onContextItemSelected(item);
        }
    }




}
