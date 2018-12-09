package pe.edu.tecsup.usofragmentosv3;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMusic.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentMusic extends Fragment {
    private OnFragmentInteractionListener mListener;
    private MediaPlayer mediaplayer;
    private int playbackPosition = 0;
    TextView lblNombre;
    private Handler mHandler = new Handler();
    SeekBar progresoMusica;
    TextView lblDuracion, lblPosicionActual;
    SeekBar sk_volumen;
    AudioManager audioManager;
    public FragmentMusic() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_fragment_music, container, false);
        lblNombre=(TextView) vista.findViewById(R.id.lblNombre);

        lblNombre =vista.findViewById(R.id.lblNombre);
        progresoMusica = vista.findViewById(R.id.progresoMusica);
        lblDuracion = vista.findViewById(R.id.lblDuracion);
        lblPosicionActual =vista.findViewById(R.id.lblPosicionActual);
        progresoMusica = vista.findViewById(R.id.progresoMusica);
      //  progresoMusica.setOnSeekBarChangeListener(this);
        lblDuracion = vista.findViewById(R.id.lblDuracion);

        Bundle reproducir = this.getActivity().getIntent().getExtras();
        if(reproducir != null){
            String musica = getActivity().getIntent().getExtras().getString("musica");
            String nombre = getActivity().getIntent().getExtras().getString("nombre");
            try {
                playAudio(musica);
                lblNombre.setText(nombre);
                progresoMusica.setProgress(0);
                progresoMusica.setMax(100);
                actualizarProgreso();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public void doClic(View v){
        switch (v.getId()){
            case R.id.btnPlay:
                try{
                    if(mediaplayer==null) {
                        playLocalAudio();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btnPause:
                if(mediaplayer!=null && mediaplayer.isPlaying()){
                    playbackPosition = mediaplayer.getCurrentPosition();
                    mediaplayer.pause();
                }
                break;
            case R.id.btnReset:
                if(mediaplayer!=null && !mediaplayer.isPlaying()){
                    mediaplayer.seekTo(playbackPosition);
                    mediaplayer.start();
                }
                break;
            case R.id.btnStop:
                if(mediaplayer!=null){
                    mediaplayer.stop();
                    playbackPosition = 0;
                    mediaplayer=null;
                }
                break;

        }
        Volumen();
    }
    private void killMediaPlayer(){
        if(mediaplayer!=null){
            try{
                mediaplayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void playAudio(String url) throws Exception{
        killMediaPlayer();
        String filePath = url;
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(inputStream.getFD());
        inputStream.close();
        mediaplayer.prepare();
        mediaplayer.start();
    }

    private Runnable hiloActualizarProgreso = new Runnable() {
        public void run() {
            try {
                long totalDuration = mediaplayer.getDuration();
                long currentDuration = mediaplayer.getCurrentPosition();

                // Displaying Total Duration time
                lblDuracion.setText("" + Utilidades.milliSecondsToTimer(totalDuration));
                // Displaying time completed playing
                lblPosicionActual.setText("" + Utilidades.milliSecondsToTimer(currentDuration));

                // Updating progress bar
                int progress = Utilidades.getProgressPercentage(currentDuration, totalDuration);
                progresoMusica.setProgress(progress);

                // Running this thread after 1000 milliseconds
                mHandler.postDelayed(this, 1000);
            }catch (Exception e){

            }
        }
    };
    public void playLocalAudio() throws Exception{
        killMediaPlayer();
        mediaplayer = MediaPlayer.create(getActivity(),R.raw.audio);
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaplayer.start();
    }


    public void actualizarProgreso() {
        mHandler.postDelayed(hiloActualizarProgreso, 1000);
    }
    private void Volumen() {

        try {
            sk_volumen = (SeekBar)getView().findViewById(R.id.SeekbarVolumen);
            audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
            sk_volumen.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            sk_volumen.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            sk_volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override            public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void verLista(View v){
        Intent cargarLista = new Intent(getActivity(), musicas.class);
        startActivity(cargarLista);
    }
}
