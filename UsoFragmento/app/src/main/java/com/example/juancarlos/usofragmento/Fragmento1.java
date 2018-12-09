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
 */
public class Fragmento1 extends Fragment {
    TextView lblRecibido;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);

        lblRecibido = (TextView)view.findViewById(R.id.lblRecibido);

        if (getArguments()!=null){
            String texto = getArguments().getString("texto");
            lblRecibido.setText("Recibido: "+ texto);
        }

        return view;

    }

}
