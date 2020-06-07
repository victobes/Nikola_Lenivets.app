package com.example.nikola_lenivetsapp.Fragments.OtherFragments;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nikola_lenivetsapp.Models.Note;
import com.example.nikola_lenivetsapp.Adapters.NoteAdapter;
import com.example.nikola_lenivetsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

// Предоставляется возможность просмотреть список заметок пользователей приложения
// и перейти на страницу определенной при нажатии на нее,
// а также пользователь может создать собственную заметку.

public class ForumFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUESTCODE = 2;

    private String mParam1;
    private String mParam2;

    private Dialog notes;
    private Button button, noteButton;

    private View fragmentView;
    private CircleImageView noteUserPhoto;
    private ImageView notePhoto;
    private EditText noteTitle, noteDescription;
    private FirebaseUser currentUser;
    private Uri pickedImageUri = null;

    private RecyclerView noteRecyclerView;
    private NoteAdapter noteAdapter;

    private DatabaseReference reference;

    private List<Note> noteList;

    private OnFragmentInteractionListener mListener;

    public ForumFragment() {

    }

    public static ForumFragment newInstance(String param1, String param2) {
        ForumFragment fragment = new ForumFragment();
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
        fragmentView = inflater.inflate(R.layout.fragment_forum, container, false);
        button = fragmentView.findViewById(R.id.add_note_button);

        initializationNote();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        if (currentUser.getPhotoUrl() != null) {
            Glide.with(this).load(currentUser.getPhotoUrl()).into(noteUserPhoto);
        } else {
            Glide.with(this).load(R.drawable.lenivets_add).into(noteUserPhoto);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.show();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Notes");

        noteRecyclerView = fragmentView.findViewById(R.id.note_recycler_view);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteRecyclerView.setHasFixedSize(true);

        return fragmentView;

    }

    @Override
    public void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                noteList = new ArrayList<>();

                for (DataSnapshot notes : dataSnapshot.getChildren()) {

                    Note note = notes.getValue(Note.class);
                    noteList.add(note);
                }

                noteAdapter = new NoteAdapter(getActivity(), noteList);
                noteRecyclerView.setAdapter(noteAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializationNote() {

        notes = new Dialog(getContext());
        notes.setContentView(R.layout.note_view);
        notes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notes.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        notes.getWindow().getAttributes().gravity = Gravity.TOP;

        noteUserPhoto = notes.findViewById(R.id.note_user_image);
        noteTitle = notes.findViewById(R.id.note_title);
        noteDescription = notes.findViewById(R.id.note_description);
        notePhoto = notes.findViewById(R.id.note_image);

        notePhoto.setImageResource(R.drawable.add_photo);

        noteButton = notes.findViewById(R.id.note_button);

        notePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!noteTitle.getText().toString().isEmpty()
                        && !noteDescription.getText().toString().isEmpty()
                        && pickedImageUri != null) {

                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("note_images");
                    final StorageReference imageFilePath = storageReference.child(pickedImageUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();

                                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                                            "://" + getResources().getResourcePackageName(R.drawable.lenivets_hello)
                                            + '/' + getResources().getResourceTypeName(R.drawable.lenivets_hello)
                                            + '/' + getResources().getResourceEntryName(R.drawable.lenivets_hello));

                                    Note note;

                                    if (currentUser.getPhotoUrl() != null) {
                                        note = new Note(noteTitle.getText().toString(),
                                                noteDescription.getText().toString(), currentUser.getPhotoUrl().toString(),
                                                imageDownlaodLink,
                                                currentUser.getUid(), currentUser.getDisplayName());
                                    } else {
                                        note = new Note(noteTitle.getText().toString(),
                                                noteDescription.getText().toString(), imageUri.toString(),
                                                imageDownlaodLink,
                                                currentUser.getUid(), currentUser.getDisplayName());
                                    }

                                    addNote(note);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                } else {
                    Toast.makeText(getContext(), R.string.toast_about_fields, Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    private void addNote(Note note) {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Notes").push();

        String key = db.getKey();
        note.setNoteKey(key);

        db.setValue(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getContext(), R.string.toast_note_success, Toast.LENGTH_SHORT).show();
                notes.dismiss();
            }
        });

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


    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESTCODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            pickedImageUri = data.getData();

            pickedImageUri = data.getData();
            notePhoto.setImageURI(pickedImageUri);
        }
    }

}
