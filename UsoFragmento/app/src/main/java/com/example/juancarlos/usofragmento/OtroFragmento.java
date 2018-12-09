package com.example.juancarlos.usofragmento;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class OtroFragmento extends Fragment {
    Button btnFragmento1,btnFragmento2,btnDatos,btnRespuesta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otro_fragmento, container, false);

        btnFragmento1=(Button) view.findViewById(R.id.btnFragmento1);
        btnFragmento2= (Button)view.findViewById(R.id.btnFragmento2);
        btnDatos= (Button)view.findViewById(R.id.btnDatos);
        btnRespuesta= (Button)view.findViewById(R.id.btnRespuesta);

        btnFragmento1.setOnClickListener(new View.OnClickListener(){
          @Override
            public void onClick (View view){
              Fragmento1 fragmento1= new Fragmento1();
              android.support.v4.app.FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.contenedor,fragmento1);
              transaction.commit();
          }
        });
        btnFragmento2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Fragmento2 fragmento2= new Fragmento2();
                android.support.v4.app.FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,fragmento2);
                transaction.commit();
            }
        });
        btnDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                datos data= new datos();
                android.support.v4.app.FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,data);
                transaction.commit();
            }
        });
        btnRespuesta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                respuesta res= new respuesta();
                android.support.v4.app.FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,res);
                transaction.commit();
            }
        });
        return view;
    }
}
