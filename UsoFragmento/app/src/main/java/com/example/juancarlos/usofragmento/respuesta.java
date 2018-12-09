package com.example.juancarlos.usofragmento;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class respuesta extends Fragment {
    TextView lblRecibido;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);

        lblRecibido = (TextView)view.findViewById(R.id.lblRecibido);

        if (getArguments()!=null){
            String texto = getArguments().getString("texto");
            String texto2 = getArguments().getString("texto2");
            lblRecibido.setText("Usuario: "+ texto+"\nClave:  "+texto2);
        }

        return view;
    }

}
