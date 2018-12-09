package com.example.tecsup.usofragmentosv3;

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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Operaciones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Operaciones extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    public Operaciones() {
        // Required empty public constructor
    }

    private EditText txtN1,txtN2;
    private TextView lblresultado;
    private Button calcular,suma,resta,multi,divi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operaciones, container, false);

        lblresultado =view.findViewById(R.id.lblresultado);
        txtN1 = view.findViewById(R.id.txtN1);
        txtN2 = view.findViewById(R.id.txtN2);

        calcular = view.findViewById(R.id.calcular);
        suma = view.findViewById(R.id.suma);
        resta = view.findViewById(R.id.resta);
        multi = view.findViewById(R.id.multi);
        divi = view.findViewById(R.id.divi);

        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
        divi.setOnClickListener(this);
        multi.setOnClickListener(this);

        calcular.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view){

            String strv1 = txtN1.getText().toString();
            String strv2 = txtN2.getText().toString();

            int numv1 = Integer.parseInt(strv1);
            int numv2 = Integer.parseInt(strv2);
            int r;

            switch (view.getId()){
                case R.id.suma:
                    r = numv1 + numv2;
                    lblresultado.setText(String.valueOf(r));
                    break;
                case R.id.resta:
                    r = numv1 - numv2;
                    lblresultado.setText(String.valueOf(r));
                    break;
                case R.id.multi:
                    r = numv1 * numv2;
                    lblresultado.setText(String.valueOf(r));
                    break;
                case R.id.divi:
                    try {
                        r = numv1 / numv2;
                        lblresultado.setText(String.valueOf(r));
                    }catch (Exception e){
                        lblresultado.setText("Division Erronea");
                    }
                    break;
            }
        }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onClick(View v) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
