package com.example.tecsup.usofragmentosv3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Operaciones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Operaciones extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Operaciones() {
        // Required empty public constructor
    }

    EditText txtEnviarNum1, txtEnviarNum2;
    Button btnEnviarResp;
    String valor;
    String respu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operaciones, container, false);

        txtEnviarNum1 = view.findViewById(R.id.txtEnviarNum1);
        txtEnviarNum2 = view.findViewById(R.id.txtEnviarNum2);
        btnEnviarResp = view.findViewById(R.id.btnEnviaResp);


        final Spinner spinner = view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String valor1 = (String) spinner.getItemAtPosition(position);
                valor = valor1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnEnviarResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Respuesta fragmento1 = new Respuesta();
                Bundle args = new Bundle();

                if(valor.equals("Suma")){
                    int num1 = Integer.parseInt(txtEnviarNum1.getText().toString());
                    int num2 = Integer.parseInt(txtEnviarNum2.getText().toString());
                    int suma = num1 + num2;
                    respu = "" + suma;
                }else if(valor.equals("Resta")){
                    int num1 = Integer.parseInt(txtEnviarNum1.getText().toString());
                    int num2 = Integer.parseInt(txtEnviarNum2.getText().toString());
                    int suma = num1 - num2;
                    respu = "" + suma;
                }else if(valor.equals("Multiplicacion")){
                    int num1 = Integer.parseInt(txtEnviarNum1.getText().toString());
                    int num2 = Integer.parseInt(txtEnviarNum2.getText().toString());
                    int suma = num1 * num2;
                    respu = "" + suma;
                }else{
                    int num1 = Integer.parseInt(txtEnviarNum1.getText().toString());
                    int num2 = Integer.parseInt(txtEnviarNum2.getText().toString());
                    int suma = num1 / num2;
                    respu = "" + suma;
                }

                args.putString("text1", respu.toString());
                fragmento1.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor1, fragmento1);
                transaction.commit();
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
