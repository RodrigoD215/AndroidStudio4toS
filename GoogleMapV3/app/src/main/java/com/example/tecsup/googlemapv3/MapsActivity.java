package com.example.tecsup.googlemapv3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
GoogleMap.OnMarkerClickListener,AdapterView.OnItemSelectedListener {

    private  final LatLng Plaza = new LatLng(-16.3988041,-71.5387461);
    private GoogleMap mMap;
    private String destino = "";
    Marker marcadorDestino;
    LatLng coordenada=new LatLng(0,0);
    private Spinner spnTipoMapa;
    private int tipos_mapas[] = {
            GoogleMap.MAP_TYPE_NONE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spnTipoMapa =  findViewById(R.id.spnTipoMapa);
        spnTipoMapa.setOnItemSelectedListener(this);

        Bundle recibidos = this.getIntent().getExtras();
        if(recibidos !=null) {
            destino = getIntent().getExtras().getString("destino");
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
        switch (destino){

            case "plaza de armas":
                coordenada = new LatLng(-16.3988031,-71.5374435);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "characato":
                coordenada = new LatLng(-16.4703686,-71.4985086);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "colca":
                coordenada = new LatLng(-15.6093241,-72.0918116);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "yura":
                coordenada = new LatLng(-16.2526068,-71.7019542);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "mirador sachaca":
                coordenada = new LatLng(-16.4262762,-71.5696303);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "mi casa":
                coordenada = new LatLng(-16.4153282,-71.5085615);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            default:
                Toast.makeText(this,"No se encontro destino turistico",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,15));
        // Eventos
        mMap.setOnMarkerClickListener(this);
    }



    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(marcadorDestino)) {
            Intent intent = new Intent(this, destinos.class);
            intent.putExtra("destino", destino);
            intent.putExtra("latitud", (marker.getPosition().latitude + ""));
            intent.putExtra("longitud", marker.getPosition().longitude + "");
            String mensaje="";
            String urlImagen ="";
            switch (destino){
                case "plaza de armas":
                    mensaje="La plaza Mayor o plaza de Armas de Arequipa, es uno de los principales espacios públicos de Arequipa y el lugar de fundación de la ciudad.";
                    urlImagen="https://img.peru21.pe/files/article_content_ec_fotos/uploads/2017/08/09/598b8610a9249.jpeg";
                    break;
                case "characato":
                    mensaje="El distrito de Characato es uno de los 29 distritos que conforman la provincia de Arequipa en el Departamento de Arequipa, bajo la administración del Gobierno regional de Arequipa, en el sur del Perú.";
                    urlImagen="http://2.bp.blogspot.com/-WEZhUtmLHeo/VXxfHgQRPGI/AAAAAAAAQKc/XFt_Aviwy74/s1600/CHARACATO.jpg";
                    break;
                    case "colca":
                    mensaje="El valle del Colca es uno de los mayores destinos turísticos del Perú. Está ubicado en la provincia de Caylloma. Colca proviene de las palabras Collaguas y Cabanas, dos etnias que habitaban a lo largo del río Colca. Este cañón tiene una profundidad de 4160 metros.";
                    urlImagen="https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Valley_of_Colca_River%2C_Peru.jpg/250px-Valley_of_Colca_River%2C_Peru.jpg";
                    break;
                case "yura":
                    mensaje="El distrito de Yura es uno de los 29 distritos que conforman la provincia de Arequipa en el Departamento de Arequipa, bajo la administración del Gobierno regional de Arequipa, en el sur del Perú.";
                    urlImagen="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Cementera%2C_Yura%2C_Perú%2C_2015-08-01%2C_DD_129-131_HDR.JPG/280px-Cementera%2C_Yura%2C_Perú%2C_2015-08-01%2C_DD_129-131_HDR.JPG";
                    break;
                case "mirador sachaca":
                    mensaje="Excelente lugar. Es un mirador hecho de concreto con una cruz y un Cristo en el medio de su estructura. Desde lo alto se ve la ciudad, el valle y elos tres volcanes que rodean a Arequipa: el Chachani, el Misti y el Pichu Pichu.";
                    urlImagen="https://media-cdn.tripadvisor.com/media/photo-s/01/16/98/94/el-mirador-de-sachaca.jpg";
                    break;
                case "mi casa":
                    mensaje="Donde vivo we <3";
                    urlImagen="https://i.pinimg.com/originals/11/2c/2d/112c2d46206b19be8ae926b2f7fdce19.jpg";
                    break;
            }



            intent.putExtra("url",urlImagen);
            intent.putExtra("info", mensaje);
            startActivity(intent);
            return true;
        }
        return false;
    }


    public void moverCamara(View view){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza,18));
    }

    public void agregarMarcador(View view) {
        mMap.addMarker(new MarkerOptions().position(
                new LatLng(mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude))
                .position(new LatLng(mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude))
                .title("Mi Ubicacion")
                .snippet("Lat:" + mMap.getCameraPosition().target.latitude +
                        " Lon:" + mMap.getCameraPosition().target.longitude)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ubi))
                .anchor(0.5f, 0.9f)
        );

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mMap.setMapType(tipos_mapas[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
