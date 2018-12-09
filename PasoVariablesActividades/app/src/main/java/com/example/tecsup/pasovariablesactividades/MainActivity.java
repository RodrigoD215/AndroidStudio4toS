package com.example.tecsup.pasovariablesactividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    EditText txtseleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtseleccionados= (EditText) findViewById(R.id.txtseleccionados);
        txtseleccionados.setFocusable(false);
        lista=(ListView)findViewById(R.id.lista);
        String[] values = new String[] {
                "Juegos",
                "Aplicaciones",
                "Cursos",
                "Profesores",
                "Cerrar"
        };
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
        values);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String)lista.getItemAtPosition(position);

                if(itemPosition==0) {
                    String seleccionados = txtseleccionados.getText().toString();
                    Intent intent = new Intent(MainActivity.this, actividad_juegos.class);
                    intent.putExtra("seleccionados", seleccionados);
                    startActivity(intent);
                }
                if(itemPosition==1) {
                    String seleccionados = txtseleccionados.getText().toString();
                    Intent intent = new Intent(MainActivity.this, actividad_aplicaciones.class);
                    intent.putExtra("seleccionados", seleccionados);
                    startActivityForResult(intent,1);
                }
                if(itemPosition==2) {
                    String seleccionados = txtseleccionados.getText().toString();
                    Intent intent = new Intent(MainActivity.this, activity_cursos.class);
                    intent.putExtra("seleccionados", seleccionados);
                    startActivityForResult(intent,2);
                }
                if(itemPosition==3) {
                    String seleccionados = txtseleccionados.getText().toString();
                    Intent intent = new Intent(MainActivity.this, activity_profesores.class);
                    intent.putExtra("seleccionados", seleccionados);
                    startActivityForResult(intent,3);
                }
                if(itemPosition==4) {


                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("¿Desea salir de la Aplicacion?");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog =builder.create();
                    dialog.show();


                }



                }
        });


            Bundle parametros =this.getIntent().getExtras();
            if(parametros !=null){
                String recibidos = getIntent().getExtras().getString("parametro");
                String datosactividad01 = getIntent().getExtras().getString("datosactividad01");
                txtseleccionados.setText("Usted Selecciono: " +" "+ recibidos );

            }

            }
        }







