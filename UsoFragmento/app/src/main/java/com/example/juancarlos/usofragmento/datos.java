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
public class datos extends Fragment {
    EditText txtEnviar;
    EditText txtClave;
    Button btnEnviar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_datos, container, false);
        txtEnviar = (EditText)view.findViewById(R.id.txtEnviar);
        txtClave = (EditText)view.findViewById(R.id.txtClave);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                respuesta resp= new respuesta();
                Bundle args =  new Bundle();
                args.putString("texto",txtEnviar.getText().toString());
                args.putString("texto2",txtClave.getText().toString());
                resp.setArguments(args);
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,resp);
                transaction.commit();
            }
        });
        return view;
    }


}
