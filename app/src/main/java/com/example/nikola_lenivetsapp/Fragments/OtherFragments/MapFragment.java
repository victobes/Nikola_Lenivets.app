package com.example.nikola_lenivetsapp.Fragments.OtherFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.BernaskoniArchFragment;
import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.BoburFragment;
import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.LazyZigguratFragment;
import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.RotundaFragment;
import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.TheUniversalMindFragment;
import com.example.nikola_lenivetsapp.Fragments.ObjectsFragments.WhiteGatesFragment;
import com.example.nikola_lenivetsapp.R;



public class MapFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageButton bobur, whiteGates, bernaskoniArch, lazyZiggurat, rotunda, theUniversalMind;

    public MapFragment() {

    }


    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_map, container, false);

        //инициализация ImageButtons
        bobur = fragmentView.findViewById(R.id.bobur);
        bobur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getParentFragmentManager().beginTransaction().replace(R.id.container, new BoburFragment()).commit();
            }
        });

        whiteGates = fragmentView.findViewById(R.id.white_gates);
        whiteGates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, new WhiteGatesFragment()).commit();
            }
        });

        bernaskoniArch = fragmentView.findViewById(R.id.bernaskoni_arch);
        bernaskoniArch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, new BernaskoniArchFragment()).commit();
            }
        });

        lazyZiggurat = fragmentView.findViewById(R.id.lazy_ziggurat);
        lazyZiggurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, new LazyZigguratFragment()).commit();
            }
        });

        rotunda = fragmentView.findViewById(R.id.rotunda);
        rotunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, new RotundaFragment()).commit();
            }
        });

        theUniversalMind = fragmentView.findViewById(R.id.the_universal_mind);
        theUniversalMind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, new TheUniversalMindFragment()).commit();
            }
        });

        return fragmentView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
