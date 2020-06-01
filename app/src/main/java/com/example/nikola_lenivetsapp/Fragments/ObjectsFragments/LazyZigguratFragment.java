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

public class LazyZigguratFragment extends ObjectFragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_object, container, false);

        objectSound = MediaPlayer.create(getActivity(), R.raw.lazy_ziggurat_voice);

        objectPhoto = fragmentView.findViewById(R.id.object_photo);
        objectPhoto.setImageResource(R.drawable.lazy_ziggurat_photo);

        objectText = fragmentView.findViewById(R.id.object_text);
        objectText.setText(R.string.lazy_ziggurat_text);

        fab = fragmentView.findViewById(R.id.object_fab);
        fab.setOnClickListener(firstFabListener);

        return fragmentView;
    }

}
