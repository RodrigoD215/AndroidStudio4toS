package com.example.juancarlos.servicioweb2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class Editar extends AppCompatActivity {
    EditText nombre,clave;
    String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        nombre=(EditText)findViewById(R.id.nombre);
        clave= (EditText)findViewById(R.id.clave);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            String name = getIntent().getExtras().getString("nombre");
            String pass = getIntent().getExtras().getString("clave");
            codigo = getIntent().getExtras().getString("id");
            nombre.setText(name + "");
            clave.setText(pass + "");
        }
    }
    public void actualizar(View v){

            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                String name = nombre.getText().toString();
                String pass = clave.getText().toString();

                String posturl = "http://172.23.15.214:8080/ServicioWeb2/editar";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(posturl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("codigo", codigo));
                params.add(new BasicNameValuePair("usuario", name));
                params.add(new BasicNameValuePair("clave", pass));
                httppost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse resp = httpClient.execute(httppost);
                HttpEntity ent = resp.getEntity();
                String respuesta = EntityUtils.toString(ent).toString();
                if (respuesta.toString().equals("OK")){
                    Toast.makeText(getBaseContext(),"Modificado correctamente",Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    clave.setText("");
                    Intent myactivity = new Intent(this,MainActivity.class);
                    startActivity(myactivity);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"No se puedo modificar",Toast.LENGTH_SHORT).show();
                    nombre.setFocusable(true);
                }
            } catch (Exception e) {
                Toast.makeText(getBaseContext(),"Error al acceder al servidor",Toast.LENGTH_SHORT).show();
            }
    }
    public void volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }



}
