package com.example.nikola_lenivetsapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nikola_lenivetsapp.Activities.NoteActivity;
import com.example.nikola_lenivetsapp.Models.Note;
import com.example.nikola_lenivetsapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

// Адаптер заметок пользователей.

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context mContext;
    List<Note> noteList;

    public NoteAdapter(Context mContext, List<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.noteViewTitle.setText(noteList.get(position).getTitle());
        Glide.with(mContext).load(noteList.get(position).getPicture()).into(holder.noteViewImage);
        Glide.with(mContext).load(noteList.get(position).getUserPhoto()).into(holder.noteViewProfileImage);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView noteViewTitle;
        ImageView noteViewImage;
        CircleImageView noteViewProfileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            noteViewTitle = itemView.findViewById(R.id.note_view_title);
            noteViewImage = itemView.findViewById(R.id.note_view_image);
            noteViewProfileImage = itemView.findViewById(R.id.note_view_profile_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NoteActivity.class);
                    int position = getAdapterPosition();

                    intent.putExtra("noteTitle", noteList.get(position).getTitle());
                    intent.putExtra("noteImage", noteList.get(position).getPicture());
                    intent.putExtra("noteDescription", noteList.get(position).getDescription());
                    intent.putExtra("noteKey", noteList.get(position).getNoteKey());
                    intent.putExtra("noteUserPhoto", noteList.get(position).getUserPhoto());
                    intent.putExtra("noteUserName", noteList.get(position).getUserName());

                    mContext.startActivity(intent);

                }
            });
        }
    }
}