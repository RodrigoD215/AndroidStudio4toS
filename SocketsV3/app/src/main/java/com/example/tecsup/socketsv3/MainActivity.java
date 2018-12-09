package com.example.tecsup.socketsv3;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnIniciarServer, btnEnviarData;
    EditText txtMensaje;
    TextView lblMensajes;
    private ServerSocket serverSocket;
    private Socket tempClientSocket;
    Thread serverThread = null;
    public static int SERVER_PORT = 3009;
    String nic,puerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
                    detectDiskReads().detectNetwork().penaltyLog().build());

        txtMensaje = findViewById(R.id.txtMensaje);
        lblMensajes = findViewById(R.id.lblMensajes);
        btnEnviarData = findViewById(R.id.btnEnviarData);
        btnIniciarServer = findViewById(R.id.btnIniciarServer);

        nic = getIntent().getStringExtra("nick");
        puerto = getIntent().getStringExtra("Puerto");

        SERVER_PORT=Integer.parseInt(puerto);

        btnIniciarServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblMensajes.setText("");
                actualizarMensajes("Iniciando Servidor...");
                serverThread = new Thread(new HiloServidor());
                serverThread.start();
                //return;
            }
        });

        btnEnviarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = txtMensaje.getText().toString();
                enviarMensajes( nic + mensaje);
                actualizarMensajes(verHora() + " | " +nic+ mensaje);
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

    private void enviarMensajes(String mensaje) {
        try {
            if (null != tempClientSocket) {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(tempClientSocket.getOutputStream())),
                        true);
                out.println(mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String verHora() {
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        return hora.format(new Date());
    }

    class HiloComunicacion implements Runnable {

        private Socket clientSocket;

        private BufferedReader input;

        public HiloComunicacion(Socket clientSocket) {

            this.clientSocket = clientSocket;
            tempClientSocket = clientSocket;

            try {
                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            actualizarMensajes("Servidor Iniciado...");
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String read = input.readLine();
                    //Log.i(TAG, "Message Received from Client : " + read);
                    if (null == read || "Desconectado".contentEquals(read)) {
                        Thread.interrupted();
                        read = "Cliente Desconectado";
                        actualizarMensajes(verHora() + " | : " + read);
                        break;
                    }
                    actualizarMensajes(verHora() + " | : " + read);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class HiloServidor implements Runnable {

        public void run() {
            Socket socket;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != serverSocket) {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        socket = serverSocket.accept();
                        HiloComunicacion commThread = new HiloComunicacion(socket);
                        new Thread(commThread).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != serverThread) {
            enviarMensajes("Desconectado");
            serverThread.interrupt();
            serverThread = null;
        }
    }






}



