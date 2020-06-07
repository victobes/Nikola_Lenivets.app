package com.example.nikola_lenivetsapp.Fragments.ObjectsFragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola_lenivetsapp.R;

public class BernaskoniArchFragment extends ObjectFragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_object, container, false);

        objectSound = MediaPlayer.create(getActivity(), R.raw.bernaskoni_arch_voice);

        objectPhoto = fragmentView.findViewById(R.id.object_photo);
        objectPhoto.setImageResource(R.drawable.bernaskoni_arch_photo);

        objectText = fragmentView.findViewById(R.id.note_detail__text);
        objectText.setText(R.string.bernaskoni_arch_text);

        fab = fragmentView.findViewById(R.id.object_fab);
        fab.setOnClickListener(firstFabListener);

        return fragmentView;
    }

}
