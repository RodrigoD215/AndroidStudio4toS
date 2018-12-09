package com.example.juancarlos.usofragmento;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ContactosFragmento extends Fragment {
    ListView lstContactos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactos_fragmento, container, false);
        lstContactos = (ListView)view.findViewById(R.id.lstContactos);
        String[] values = new String[20];
        for(int i=0;i<20;i++){
            values[i]= "Usuario "+i;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,values);
        lstContactos.setAdapter(adapter);
        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                int itemPosition = position;
                String itemValue = (String)lstContactos.getItemAtPosition(position);
                Intent intent =  new Intent(getActivity(), DetallesContacto.class);
                intent.putExtra("nombre",itemValue);
                startActivity(intent);
            }
        });
        return view;
    }

}
