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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoOperaciones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentoOperaciones extends Fragment {
    private OnFragmentInteractionListener mListener;
    public FragmentoOperaciones() {
        // Required empty public constructor
    }
    EditText lblNumero1, lblNumero2;
    RadioGroup radioGroup;
    RadioButton RBsuma,RBresta, RBproducto, RBdivision;
    Button BtnEnvio;
    double rpta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_fragmento_operaciones, container, false);
        lblNumero1=vista.findViewById(R.id.lblN1);
        lblNumero2=vista.findViewById(R.id.lblN2);
        RBsuma=vista.findViewById(R.id.rbSuma);
        RBresta=vista.findViewById(R.id.rbResta);
        RBproducto=vista.findViewById(R.id.rbProducto);
        RBdivision=vista.findViewById(R.id.rbDivision);
        radioGroup=(RadioGroup) vista.findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      // checkedId is the RadioButton selected
                                                      double val1 = Double.parseDouble( lblNumero1.getText().toString() );
                                                      double val2 = Double.parseDouble( lblNumero2.getText().toString() );

                                                      switch (checkedId) {
                                                          case R.id.rbSuma:
                                                              rpta= val1 + val2;
                                                              break;
                                                          case R.id.rbResta:
                                                              rpta= val1 -val2;
                                                              break;
                                                          case R.id.rbProducto:
                                                              rpta= val1 *val2;
                                                              break;
                                                          case R.id.rbDivision:
                                                              rpta=val1/val2;
                                                              break;
                                                      }
                                                  }
                                              });
        BtnEnvio=vista.findViewById(R.id.btnEnviar);
        BtnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoRespuesta objR =new FragmentoRespuesta();
                Bundle parametros= new Bundle();

                parametros.putString("num1",lblNumero1.getText().toString());
                parametros.putString("num2",lblNumero2.getText().toString());
                parametros.putString("operacion",String.valueOf(rpta));
                objR.setArguments(parametros);
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor1,objR);
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
