package com.example.tecsup.socketsv3cliente;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView lblMensajes;
    EditText txtMensaje;
    Button btnConectarServer, btnEnviarData;
    public static int SERVERPORT = 3009;
    public static String SERVER_IP = "192.168.43.135";
    Thread thread;
    HiloCliente hiloCliente;
    String nic,Ip,puerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().
                detectNetwork().penaltyLog().build());
        lblMensajes = findViewById(R.id.lblMensajes);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnConectarServer = findViewById(R.id.btnConectarServer);
        btnEnviarData = findViewById(R.id.btnEnviarData);


        nic = getIntent().getStringExtra("nick");
        Ip = getIntent().getStringExtra("Ip");
        puerto = getIntent().getStringExtra("Puerto");

        SERVERPORT=Integer.parseInt(puerto);
        SERVER_IP=Ip;


        btnConectarServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblMensajes.setText("");
                hiloCliente = new HiloCliente();
                thread = new Thread(hiloCliente);
                thread.start();
            }
        });

        btnEnviarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = txtMensaje.getText().toString();
                hiloCliente.enviarMensaje(nic+":"+mensaje);
                actualizarMensajes(verHora() + "|: "+ nic + mensaje);
                txtMensaje.setText("");
            }
        });
    }

    public void actualizarMensajes(final String mensaje) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblMensajes.append(mensaje + "\n");
            }
        });
    }

    String verHora() {
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        return hora.format(new Date());
    }

    class HiloCliente implements Runnable {

        private Socket socket;
        private BufferedReader input;

        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
                while (!Thread.currentThread().isInterrupted()) {
                    //Log.i(TAG, "Waiting for message from server...");
                    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();
                    //Log.i(TAG, "Message received from the server : " + message);
                    if (null == message || "Desconectado".contentEquals(message)) {
                        Thread.interrupted();
                        message = "Servidor Desconectado.";
                        actualizarMensajes(verHora() + " |  " + message);
                        break;
                    }
                    actualizarMensajes(verHora() + " |  " + message);
                }
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        void enviarMensaje(String mensaje) {
            try {
                if (null != socket) {
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                            true);
                    out.println(mensaje);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (null != hiloCliente){
            hiloCliente.enviarMensaje("Desconectado");
            hiloCliente = null;
        }
    }
}