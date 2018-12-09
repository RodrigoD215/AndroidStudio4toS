package com.example.juancarlos.usofragmento;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Fragmento2 extends Fragment {
    EditText txtEnviar;
    Button btnEnviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragmento2, container, false);
        txtEnviar = (EditText)view.findViewById(R.id.txtEnviar);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Fragmento1 fragmento1= new Fragmento1();
                Bundle args =  new Bundle();
                args.putString("texto",txtEnviar.getText().toString());
                fragmento1.setArguments(args);
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,fragmento1);
                transaction.commit();
            }
        });
        return view;

    }


}
