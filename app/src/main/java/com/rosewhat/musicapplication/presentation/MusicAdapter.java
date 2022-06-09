package com.rosewhat.musicapplication.presentation;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rosewhat.musicapplication.R;
import com.rosewhat.musicapplication.domain.Music;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private ArrayList<Music> musicList;
    private Context context;
    private OnNoteClickListener onNoteClickListener;
    private MediaPlayer player;

    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(context, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    public MusicAdapter(ArrayList<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
        notifyDataSetChanged();
    }

    interface OnNoteClickListener {
        void onNoteClick(int position);

        void onLongClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }


    @NonNull
    @NotNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MusicAdapter.MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.textViewTitle.setText(music.getTitle());
        holder.textViewYear.setText(music.getYear());
        holder.textViewLanguage.setText(music.getLanguage());
        holder.textViewDescription.setText(music.getDescription());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewLanguage;
        private TextView textViewYear;
        private ImageView imageViewPlay;
        private ImageView imageViewPause;
        private ImageView imageViewReset;

        public MusicViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitleMusic);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewLanguage = itemView.findViewById(R.id.textViewLanguage);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            imageViewPause = itemView.findViewById(R.id.imageViewPause);
            imageViewReset = itemView.findViewById(R.id.imageViewReset);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });

            imageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (player == null) {
                        player = MediaPlayer.create(context, musicList.get(getAdapterPosition()).getMusic());
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                stopPlayer();
                            }
                        });
                    }
                    player.start();
                }
            });

            imageViewPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (player != null) {
                        player.pause();
                    }
                }
            });
            imageViewReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        stopPlayer();
                }
            });
        }
    }
}
