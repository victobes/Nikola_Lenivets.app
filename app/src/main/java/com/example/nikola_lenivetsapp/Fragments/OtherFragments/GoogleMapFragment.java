package com.example.nikola_lenivetsapp.Fragments.OtherFragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola_lenivetsapp.Models.MapMarker;
import com.example.nikola_lenivetsapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;

// Предоставляется информации о расположении арт-объектов на Google Maps.
// Объекты, про которые содержится информация в приложении, отмечены маркерами  и соединены между собой.
// Координаты хранятся в Cloud Firestore.

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 10;

    private String mParam1;
    private String mParam2;

    private View fragmentView;
    private MapView mapView;
    private GoogleMap mMap;

    public GoogleMapFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapFragment()).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_google_map, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = fragmentView.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapsInitializer.initialize(getActivity());
        mMap = googleMap;
        googleMap.setMapType(mMap.MAP_TYPE_NORMAL);


        Locale.setDefault(Locale.US);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final PolylineOptions options = new PolylineOptions()
                .color(COLOR_BLACK_ARGB)
                .width(POLYLINE_STROKE_WIDTH_PX)
                .jointType(JointType.ROUND)
                .clickable(true);

        db.collection("objects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                MapMarker marker = document.toObject(MapMarker.class);

                                if (marker.getTitle().equals(getString(R.string.marker_title))) {
                                    CameraPosition cameraPosition = CameraPosition.builder()
                                            .target(new LatLng(Double.parseDouble(marker.getV()), Double.parseDouble(marker.getV1())))
                                            .zoom(15)
                                            .bearing(0)
                                            .tilt(45)
                                            .build();
                                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 10000, null);

                                } else {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(marker.getV()), Double.parseDouble(marker.getV1()))).title(marker.getTitle()));
                                    options.add(new LatLng(Double.parseDouble(marker.getV()), Double.parseDouble(marker.getV1())));
                                    googleMap.addPolyline(options);
                                }
                            }

                        } else {

                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}
