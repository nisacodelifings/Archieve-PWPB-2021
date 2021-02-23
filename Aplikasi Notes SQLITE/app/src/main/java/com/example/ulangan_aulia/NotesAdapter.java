package com.example.ulangan_aulia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    Context context;
    OnUserClickListener listener;
    List<Note> noteList = new ArrayList<>();

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    public NotesAdapter(Context context, List<Note> noteList, OnUserClickListener listener) {
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
    }

    public interface OnUserClickListener{
        void onUserClick(Note currentPerson, String action);
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vw = LayoutInflater.from(context).inflate(R.layout.note_item, parent,false);
        NotesViewHolder nvh = new NotesViewHolder(vw);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.tvTgl.setText(noteList.get(position).getTgl());
        holder.tvJudul.setText(noteList.get(position).getName());
        holder.tvIsi.setText(noteList.get(position).getIsi());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, AddNote.class);
                i.putExtra(AddNote.EXTRA_ID, noteList.get(position).getId());
                i.putExtra(AddNote.EXTRA_NAME, noteList.get(position).getName());
                i.putExtra(AddNote.EXTRA_ISI, noteList.get(position).getIsi());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTgl, tvJudul, tvIsi;
        ConstraintLayout item;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTgl = itemView.findViewById(R.id.tvNoteTgl);
            tvJudul = itemView.findViewById(R.id.tvNoteJudul);
            tvIsi = itemView.findViewById(R.id.tvNoteIsi);
            item = itemView.findViewById(R.id.itemNote);
        }
    }
}
