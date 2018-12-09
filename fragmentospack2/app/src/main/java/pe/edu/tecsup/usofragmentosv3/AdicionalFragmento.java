package pe.edu.tecsup.usofragmentosv3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdicionalFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AdicionalFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AdicionalFragmento() {
        // Required empty public constructor
    }
    Button btnFragOpe , btnFragRpta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_adicional_fragmento, container, false);
        btnFragOpe= vista.findViewById(R.id.btnOperaciones);
        btnFragRpta=vista.findViewById(R.id.btnRespuesta);
        btnFragOpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoOperaciones objFop=new FragmentoOperaciones();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor1,objFop);
                transaction.commit();
            }
        });

        btnFragRpta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoRespuesta objFrpta=new FragmentoRespuesta();
                FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor1,objFrpta);
                transaction.commit();

            }
        });
        return vista;
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
