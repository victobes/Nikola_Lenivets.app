package com.example.nikola_lenivetsapp.Fragments.ObjectsFragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola_lenivetsapp.R;

// Предоставляется информация о Ленивом зиккурате
// (звуковое сообщение, текст, изображение)

public class LazyZigguratFragment extends ObjectFragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_object, container, false);

        objectSound = MediaPlayer.create(getActivity(), R.raw.lazy_ziggurat_voice);

        objectPhoto = fragmentView.findViewById(R.id.object_photo);
        objectPhoto.setImageResource(R.drawable.lazy_ziggurat_photo);

        objectText = fragmentView.findViewById(R.id.note_detail__text);
        objectText.setText(R.string.lazy_ziggurat_text);

        fab = fragmentView.findViewById(R.id.object_fab);
        fab.setOnClickListener(firstFabListener);

        return fragmentView;
    }

}
