package com.example.nikola_lenivetsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 10;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private View fragmentView;
    private MapView mapView;
    private GoogleMap mMap;


    public GoogleMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_google_map, container, false);

        return  fragmentView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = fragmentView.findViewById(R.id.mapView);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getActivity());
        mMap = googleMap;
        googleMap.setMapType(mMap.MAP_TYPE_NORMAL);

        LatLng nikolaLenivets = new LatLng(54.7483499, 35.5985527);
        LatLng bobur = new LatLng(54.74985217306839,35.624973111095976);
        LatLng whiteGates = new LatLng(54.75443988646724,35.62302799697841);
        LatLng bernaskoniArch = new LatLng(54.75573750634654,35.60841222131371);
        LatLng lazyZiggurat = new LatLng(54.76310418925081,35.60723335325925);
        LatLng rotunda = new LatLng(54.75852305472213,35.605273857692964);
        LatLng theUniversalMind = new LatLng(54.750151441009216,35.60776328584234);


        CameraPosition cameraPosition = CameraPosition.builder()
                .target(nikolaLenivets)
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 10000, null);

        googleMap.addMarker(new MarkerOptions().position(bobur).title("Бобур"));
        googleMap.addMarker(new MarkerOptions().position(whiteGates).title("Белые ворота"));
        googleMap.addMarker(new MarkerOptions().position(bernaskoniArch).title("Арка Бернаскони"));
        googleMap.addMarker(new MarkerOptions().position(lazyZiggurat).title("Ленивый зиккурат"));
        googleMap.addMarker(new MarkerOptions().position(rotunda).title("Ротонда"));
        googleMap.addMarker(new MarkerOptions().position(theUniversalMind).title("Вселенский разум"));


        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(bobur, whiteGates, bernaskoniArch, lazyZiggurat, rotunda, theUniversalMind));

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);

    }


}
