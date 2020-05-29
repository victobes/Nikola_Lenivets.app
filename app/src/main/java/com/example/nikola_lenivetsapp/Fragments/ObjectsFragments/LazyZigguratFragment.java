package com.example.nikola_lenivetsapp.Fragments.ObjectsFragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola_lenivetsapp.Fragments.OtherFragments.MapFragment;
import com.example.nikola_lenivetsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LazyZigguratFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private LazyZigguratFragment.OnFragmentInteractionListener mListener;
    private FloatingActionButton fab;
    private MediaPlayer sound;

    public LazyZigguratFragment() {

    }

    public static LazyZigguratFragment newInstance(String param1, String param2) {
        LazyZigguratFragment fragment = new LazyZigguratFragment();
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

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
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
        View fragmentView = inflater.inflate(R.layout.fragment_lazy_ziggurat, container, false);

        sound = MediaPlayer.create(getActivity(), R.raw.lazy_ziggurat_voice);

        fab = fragmentView.findViewById(R.id.fab);
        fab.setOnClickListener(firstFabListener);
        return fragmentView;
    }


    View.OnClickListener firstFabListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            soundPlay(sound);
            fab.setOnClickListener(secondFabListener);
        }
    };

    View.OnClickListener secondFabListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            soundStop(sound);
            fab.setOnClickListener(firstFabListener);
        }
    };


    private void soundPlay(MediaPlayer sound){
        sound.start();
    }
    private void soundStop(MediaPlayer sound){
        sound.pause();
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
