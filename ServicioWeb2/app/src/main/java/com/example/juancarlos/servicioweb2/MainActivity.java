package com.example.juancarlos.servicioweb2;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstLista;
    EditText txtBuscar;
    Context context;
    Boolean comprobar= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstLista = (ListView) findViewById(R.id.lstLista);

            lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id){
                    int itemPosition = position;

                    String itemValue = (String) lstLista.getItemAtPosition(position);
                    String[] parts = itemValue.split("-");
                    String codigo= parts[0];
                    String clave = parts[1];
                    String nombre = parts[2];
                    String pru= ""+codigo+nombre+clave;
                    Toast.makeText(getBaseContext(),pru,Toast.LENGTH_SHORT).show();
                    iniciarActividad(getBaseContext(),nombre,clave,codigo);
                }
            });
    }
    public void cargarLista(View v){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String posturl = "http://172.23.15.214:8080/ServicioWeb2/recepcionListado";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpClient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuesta = EntityUtils.toString(ent).toString();

            JSONObject respJSON = new JSONObject(respuesta);
            int cantidad = respJSON.length();
            String[] values = new String[cantidad+1];
            int i=1;

            values[0]="Cod-Nombre-Clave";
            Iterator<?> items = respJSON.keys();
            while(items.hasNext()){
                String item = (String)items.next();
                Iterator<?> campo = respJSON.getJSONObject(item).keys();
                String valor = "";
                while(campo.hasNext()){
                    String dato = (String)campo.next();
                    valor += respJSON.getJSONObject(item).getString(dato)+"-";
                }
                values[i]=valor;
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
            lstLista.setAdapter(adapter);
            comprobar=true;
        }catch (Exception e){
            Toast.makeText(getBaseContext(),"Error al acceder al servidor",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
    public void buscar(View v){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            txtBuscar = (EditText)findViewById(R.id.txtBuscar);
            String usuario = txtBuscar.getText().toString();
            String posturl = "http://172.23.15.214:8080/ServicioWeb2/recepcionConsulta";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("usuario", usuario));
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpClient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuesta = EntityUtils.toString(ent).toString();

            JSONObject respJSON = new JSONObject(respuesta);
            int cantidad = respJSON.length();
            String[] values = new String[cantidad+1];
            int i=1;

            values[0]="Cod-Clave-Nombre";
            Iterator<?> items = respJSON.keys();
            while(items.hasNext()){
                String item = (String)items.next();
                Iterator<?> campo = respJSON.getJSONObject(item).keys();
                String valor = "";
                while(campo.hasNext()){
                    String dato = (String)campo.next();
                    valor += respJSON.getJSONObject(item).getString(dato)+"-";
                }
                values[i]=valor;
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
            lstLista.setAdapter(adapter);

        }catch (Exception e){
            Toast.makeText(getBaseContext(),"Error al acceder al servidor",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void iniciarActividad(Context context, String nombre,String clave,String id){
        Intent intentone = new Intent(context.getApplicationContext(), Editar.class);
        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        intentone.putExtra("nombre",nombre);
        intentone.putExtra("clave",clave);
        intentone.putExtra("id",id);
        context.startActivity(intentone);
    }


}
