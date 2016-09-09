package com.example.driving_instructors_android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.internal.CameraUpdateParcelable;


/*AIzaSyD9x1y7MOTxgGsM0pPeZ-oGJmINJ9OKRh4*/
public class MainActivity extends Activity implements OnMapReadyCallback{
    GoogleMap googleMap;
    boolean mapReady=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  createMapView();
        addMarker();*/
        Button btnMap= (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        Button btnSatellite= (Button)findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        Button btnHybrid= (Button)findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        Button btnSeattle= (Button)findViewById(R.id.btnSeattle);
        btnSeattle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LatLng seattle= new LatLng(47.6204,-122.3491);
                LatLng dublin= new LatLng(53.3478,6.259);
                CameraPosition target2= CameraPosition.builder().target(seattle).zoom(7).build();
                CameraPosition target3= CameraPosition.builder().target(dublin).zoom(7).build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target2), 2000, null);
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target3));
            }
        });
        MapFragment mapFragment= (MapFragment)getFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap map){
        mapReady=true;
        googleMap=map;
        LatLng ny= new LatLng(40.7484,-73.9857);

        CameraPosition target= CameraPosition.builder().target(ny).zoom(7).build();




        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));



    }
    private void addMarker(){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(0, 0))
                    .title("Marker")
                    .draggable(true)
            );
        }
    }
    /**
     * Initialises the mapview
     */
    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }
}
