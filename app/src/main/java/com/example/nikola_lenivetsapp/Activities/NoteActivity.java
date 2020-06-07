package com.example.nikola_lenivetsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nikola_lenivetsapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteActivity extends AppCompatActivity {


    private ImageView noteImage;
    private CircleImageView noteUserPhoto;
    private TextView noteDescription, noteTitle, noteUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteImage = findViewById(R.id.note_activity_image);
        noteUserPhoto = findViewById(R.id.note_activity_user_photo);
        noteDescription = findViewById(R.id.note_detail__text);
        noteTitle = findViewById(R.id.note_activity_title);
        noteUserName = findViewById(R.id.note_activity_username);

        String nImage = getIntent().getExtras().getString("noteImage");
        Glide.with(this).load(nImage).into(noteImage);

        String nTitle = getIntent().getExtras().getString("noteTitle");
        noteTitle.setText(nTitle);

        String nDescription = getIntent().getExtras().getString("noteDescription");
        noteDescription.setText(nDescription);

        String nUsername = getIntent().getExtras().getString("noteUserName");
        noteUserName.setText(nUsername);

        String nUserPhoto = getIntent().getExtras().getString("noteUserPhoto");
        Glide.with(this).load(nUserPhoto).into(noteUserPhoto);
    }
}
