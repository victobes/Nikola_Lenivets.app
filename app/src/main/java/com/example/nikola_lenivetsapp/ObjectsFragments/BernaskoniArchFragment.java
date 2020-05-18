package com.example.nikola_lenivetsapp.ObjectsFragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola_lenivetsapp.MapFragment;
import com.example.nikola_lenivetsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BernaskoniArchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BernaskoniArchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BernaskoniArchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BernaskoniArchFragment.OnFragmentInteractionListener mListener;
    private FloatingActionButton fab;
    private MediaPlayer sound;

    public BernaskoniArchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BernaskoniArchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BernaskoniArchFragment newInstance(String param1, String param2) {
        BernaskoniArchFragment fragment = new BernaskoniArchFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_bernaskoni_arch, container, false);

        sound = MediaPlayer.create(getActivity(), R.raw.bernaskoni_arch_voice);

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
