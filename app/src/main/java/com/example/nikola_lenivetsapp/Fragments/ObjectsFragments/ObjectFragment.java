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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikola_lenivetsapp.Fragments.OtherFragments.MapFragment;
import com.example.nikola_lenivetsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// Реализуются общие для всех фрагментов арт-объектов методы
// (обработка нажатия кнопки "назад", воспроизведение звуков)

public abstract  class ObjectFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    protected FloatingActionButton fab;

    protected MediaPlayer objectSound;

    protected ImageView objectPhoto;
    protected TextView objectText;

    public ObjectFragment() {

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

    View.OnClickListener firstFabListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            soundPlay(objectSound);
            fab.setOnClickListener(secondFabListener);
        }
    };

    View.OnClickListener secondFabListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            soundStop(objectSound);
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
