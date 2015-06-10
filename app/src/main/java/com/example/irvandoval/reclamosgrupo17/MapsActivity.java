package com.example.irvandoval.reclamosgrupo17;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    private static final LatLng ELSALVADOR = new LatLng(13.802994, -88.9053364);
    private GoogleMap map;
    private Marker marker;
    LatLng newLocation;
    LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        map = ((SupportMapFragment)   getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        Intent ni = getIntent();
        Double lon = ni.getDoubleExtra("longitud",-1);
        Double lat = ni.getDoubleExtra("latitud",-1);
        if(lon !=-1 && lat !=-1){
            //Toast.makeText(this,"longitud" + Double.toString(lon), Toast.LENGTH_LONG).show();
            //Toast.makeText(this,"latitud" + Double.toString(lat), Toast.LENGTH_LONG).show();
            String direccion = ni.getStringExtra("direccion");
            String nombreSucursal = ni.getStringExtra("nombreSucursal");
            loc = new LatLng(lon,lat);
            marker = map.addMarker(new MarkerOptions().position(loc)
                    .title(nombreSucursal).snippet(direccion).draggable(true).visible(true));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));

            map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
        }else {

            marker = map.addMarker(new MarkerOptions().position(ELSALVADOR)
                    .title("El Salvador").snippet("La ciudad de las pupusas").draggable(true));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ELSALVADOR, 10));

            map.animateCamera(CameraUpdateFactory.zoomTo(7), 2000, null);

        }
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                newLocation = marker.getPosition();
                setResults();


            }

            @Override
            public void onMarkerDragStart(Marker marker) {
            }

        });



    }

    public void setResults(){
        Intent i = new Intent();
        i.putExtra("latitude", newLocation.latitude);
        i.putExtra("longitude", newLocation.longitude);
        setResult(RESULT_OK, i);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

}