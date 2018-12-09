package com.example.tecsup.proyecto002_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    EditText txt5;
    EditText txt6;
    EditText txt7;
    EditText txt8;
    EditText txt9;
    EditText txt10;
    EditText txt11;
    EditText txt12;
    EditText txt13;
    EditText txt14;
    EditText txt15;
    EditText txt16;
    Button btncalcular;
    TextView r;
    EditText txtE1;
    EditText txtE2;
    EditText txtE3;
    EditText txtE4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1=(EditText)findViewById(R.id.txt1);
        txt2=(EditText)findViewById(R.id.txt2);
        txt3=(EditText)findViewById(R.id.txt3);
        txt4=(EditText)findViewById(R.id.txt4);
        txt5=(EditText)findViewById(R.id.txt5);
        txt6=(EditText)findViewById(R.id.txt6);
        txt7=(EditText)findViewById(R.id.txt7);
        txt8=(EditText)findViewById(R.id.txt8);
        txt9=(EditText)findViewById(R.id.txt9);
        txt10=(EditText)findViewById(R.id.txt10);
        txt11=(EditText)findViewById(R.id.txt11);
        txt12=(EditText)findViewById(R.id.txt12);
        txt13=(EditText)findViewById(R.id.txt13);
        txt14=(EditText)findViewById(R.id.txt14);
        txt15=(EditText)findViewById(R.id.txt15);
        txt16=(EditText)findViewById(R.id.txt16);
        btncalcular=(Button) findViewById(R.id.btncalcular);
        btncalcular.setOnClickListener(this);
        r=(TextView) findViewById(R.id.r);

        txtE1=(EditText)findViewById(R.id.txtE1);
        txtE2=(EditText)findViewById(R.id.txtE1);
        txtE3=(EditText)findViewById(R.id.txtE1);
        txtE4=(EditText)findViewById(R.id.txtE1);


    }

    @Override
    public void onClick(View v) {
        int valor1 = Integer.parseInt(txt1.getText().toString());
        int valor2 = Integer.parseInt(txt2.getText().toString());
        int valor3 = Integer.parseInt(txt3.getText().toString());
        int valor4 = Integer.parseInt(txt4.getText().toString());
        int valor5 = Integer.parseInt(txt5.getText().toString());
        int valor6 = Integer.parseInt(txt6.getText().toString());
        int valor7 = Integer.parseInt(txt7.getText().toString());
        int valor8 = Integer.parseInt(txt8.getText().toString());
        int valor9 = Integer.parseInt(txt9.getText().toString());
        int valor10 = Integer.parseInt(txt10.getText().toString());
        int valor11 = Integer.parseInt(txt11.getText().toString());
        int valor12 = Integer.parseInt(txt12.getText().toString());
        int valor13 = Integer.parseInt(txt13.getText().toString());
        int valor14 = Integer.parseInt(txt14.getText().toString());
        int valor15 = Integer.parseInt(txt15.getText().toString());
        int valor16 = Integer.parseInt(txt16.getText().toString());

        int examen1= Integer.parseInt(txtE1.getText().toString());
        int examen2= Integer.parseInt(txtE2.getText().toString());
        int examen3= Integer.parseInt(txtE3.getText().toString());
        int examen4= Integer.parseInt(txtE4.getText().toString());


        int x = (examen1+examen2+examen3+examen4)/4;

        int s = (valor1+valor2+valor3+valor4+valor5+valor6+valor7+valor8+valor9+valor10+valor11+valor12+valor13+valor14+valor15+valor16)/16;



        r.setText((x*(0.3))+(s*(0.7))+"");
    }
}


