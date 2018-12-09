package com.example.juancarlos.usofragmento;

import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.v4.app.Fragment;
        import android.support.v4.content.ContextCompat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.SeekBar;
        import android.widget.TextView;

        import java.io.File;
        import java.io.FileInputStream;
        import java.util.ArrayList;
        import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class MusicaFragmento extends Fragment {
    static final String AUDIO_PATH=
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/audio.mp3";
    private MediaPlayer mediaplayer;
    private int playbackPosition=0;
    private ListView lstMusicas;
    private List<String> item=null;
    private String ruta=Environment.getExternalStorageDirectory()+"/Download/";
    private String ruta_memoria_sd="/Xperia E5/C@RL0S/Música";
    private TextView lblnombre;
    private AudioManager audioManager;
    private SeekBar sk_volumen;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;
    private TextView tp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musica_fragmento,container,false);
        String state=Environment.getExternalStorageState();


        item=new ArrayList<String>();
        final List<String> item=new ArrayList<String>();
        lblnombre=(TextView)view.findViewById(R.id.nMusica);
        //  lblnombre.setText(f.toString());
        final int READ_ETERNAL_STORAGE_PERMISSION_CODE=123;


        File f =new File(ruta);
        File []files=f.listFiles();
        for(int i=0; i<files.length;i++){
            File filex=files[i];
            if(filex.isDirectory()){
                item.add(filex.getName()+"/");
            }
            else {
                if(filex.getName().toString().contains(".mp3")||filex.getName().toString().contains(".m4a"))
                    item.add(filex.getName());
            }
        }

        final ListView lstMusicas=(ListView)view.findViewById(R.id.lista);
        ArrayAdapter<String>filelist=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,item);
        lstMusicas.setAdapter(filelist);
        lstMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue=(String)lstMusicas.getItemAtPosition(position);
                try{
                    playAudio(ruta+itemValue);
                    lblnombre.setText(itemValue);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;
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
    public void playLocalAudio() throws Exception{
        killMediaPlayer();
        mediaplayer=MediaPlayer.create(getContext(),R.raw.audio);
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaplayer.start();
    }
    public void playAudio(String url) throws Exception{
        killMediaPlayer();
        String filePath=url;
        File file= new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
        mediaplayer= new MediaPlayer();
        mediaplayer.setDataSource(inputStream.getFD());
        inputStream.close();
        mediaplayer.prepare();
        mediaplayer.start();
    }


}
