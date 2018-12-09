package com.example.tecsup.proyecto002_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    boolean decimal =false;
    boolean sum =false;
    boolean res =false;
    boolean mul =false;
    boolean div =false;
    Double[] numero= new Double[20];
    Double dato;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nr0 = (Button)findViewById(R.id.cero);
        nr0.setOnClickListener(this);

        Button nr1 = (Button)findViewById(R.id.uno);
        nr1.setOnClickListener(this);

        Button nr2 = (Button)findViewById(R.id.dos);
        nr2.setOnClickListener(this);

        Button nr3 = (Button)findViewById(R.id.tres);
        nr3.setOnClickListener(this);

        Button nr4 = (Button)findViewById(R.id.cuatro);
        nr4.setOnClickListener(this);

        Button nr5 = (Button)findViewById(R.id.cinco);
        nr5.setOnClickListener(this);

        Button nr6 = (Button)findViewById(R.id.seis);
        nr6.setOnClickListener(this);

        Button nr7 = (Button)findViewById(R.id.siete);
        nr7.setOnClickListener(this);

        Button nr8 = (Button)findViewById(R.id.ocho);
        nr8.setOnClickListener(this);

        Button nr9 = (Button)findViewById(R.id.nueve);
        nr9.setOnClickListener(this);

        Button npunto = (Button)findViewById(R.id.punto);
        npunto.setOnClickListener(this);

        Button nigual = (Button)findViewById(R.id.igual);
        nigual.setOnClickListener(this);

        Button nmas = (Button)findViewById(R.id.mas);
        nmas.setOnClickListener(this);

        Button nmenos = (Button)findViewById(R.id.menos);
        nmenos.setOnClickListener(this);

        Button npor = (Button)findViewById(R.id.por);
        npor.setOnClickListener(this);

        Button ndiv = (Button)findViewById(R.id.division);
        ndiv.setOnClickListener(this);

        Button nbo1 = (Button)findViewById(R.id.B1);
        nbo1.setOnClickListener(this);

        Button nbot = (Button)findViewById(R.id.BT);
        nbot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView txtResultado =(TextView)findViewById(R.id.txtResultado);
        int selector = v.getId();
        String x = txtResultado.getText().toString();

        try{
                switch (selector){
                    case R.id.cero:
                    txtResultado.setText(x+"0");
                    break;

                        case R.id.uno:
                            txtResultado.setText(x+"1");
                            break;

                            case R.id.dos:
                                txtResultado.setText(x+"2");
                                break;

                                case R.id.tres:
                                    txtResultado.setText(x+"3");
                                    break;

                                    case R.id.cuatro:
                                        txtResultado.setText(x+"4");
                                        break;

                                        case R.id.cinco:
                                            txtResultado.setText(x+"5");
                                            break;

                                            case R.id.seis:
                                                txtResultado.setText(x+"6");
                                                break;

                                                case R.id.siete:
                                                    txtResultado.setText(x+"7");
                                                    break;

                                                    case R.id.ocho:
                                                        txtResultado.setText(x+"8");
                                                        break;

                                                        case R.id.nueve:
                                                            txtResultado.setText(x+"9");
                                                            break;

                                                            case R.id.punto:
                                                                    if(decimal==false){
                                                                        txtResultado.setText(x+".");
                                                                        decimal=true;
                                                                    }else{return;}


                                                                break;

                                                                case R.id.igual:
                                                                    numero[1]=Double.parseDouble(x);
                                                                    if(sum=true){
                                                                        dato=numero[0] + numero[1];
                                                                        txtResultado.setText(String.valueOf(dato));
                                                                    }else if(res=true){
                                                                        dato=numero[0] - numero[1];
                                                                        txtResultado.setText(String.valueOf(dato));
                                                                    }else if(mul=true){
                                                                        dato=numero[0] * numero[1];
                                                                        txtResultado.setText(String.valueOf(dato));
                                                                    }else if (div=true){
                                                                    dato=numero[0] / numero[1];
                                                                    txtResultado.setText(String.valueOf(dato));
                                                                    }
                                                                    decimal=false;
                                                                    sum=false;
                                                                    res=false;
                                                                    mul=false;
                                                                    div=false;

                                                                    break;

                                                                    case R.id.mas:
                                                                        sum=true;
                                                                        numero[0]=Double.parseDouble(x);
                                                                        txtResultado.setText("");
                                                                        decimal=false;
                                                                        break;

                                                                        case R.id.menos:
                                                                            res=true;
                                                                            numero[0]=Double.parseDouble(x);
                                                                            txtResultado.setText("");
                                                                            decimal=false;
                                                                            break;

                                                                            case R.id.por:
                                                                                mul=true;
                                                                                numero[0]=Double.parseDouble(x);
                                                                                txtResultado.setText("");
                                                                                decimal=false;
                                                                                break;

                                                                                case R.id.division:
                                                                                    div=true;
                                                                                    numero[0]=Double.parseDouble(x);
                                                                                    txtResultado.setText("");
                                                                                    decimal=false;
                                                                                    break;


                    case R.id.BT:
                        txtResultado.setText("");
                        decimal=false;
                        break;


                }
        }catch(Exception e ){
            txtResultado.setText("error");

        }

    }
}


