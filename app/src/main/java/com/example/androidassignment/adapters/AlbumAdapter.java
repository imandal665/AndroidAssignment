package com.example.androidassignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.R;
import com.example.androidassignment.activities.PhotosActivity;
import com.example.androidassignment.models.UserAlbum;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserAlbum> userAlbums;

    public AlbumAdapter(Context context, ArrayList<UserAlbum> albums) {
        this.context = context;
        this.userAlbums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(userAlbums.get(position).getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity.class);
                intent.putExtra(UserAlbum.ID, userAlbums.get(position).getId());
                intent.putExtra(UserAlbum.TITLE, userAlbums.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userAlbums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            title = itemView.findViewById(R.id.user_name);
        }
    }
}
