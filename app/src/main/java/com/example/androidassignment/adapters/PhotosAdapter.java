package com.example.androidassignment.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidassignment.R;
import com.example.androidassignment.models.UserAlbumPhotos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserAlbumPhotos> albumPhotos;

    public PhotosAdapter(Context context, ArrayList<UserAlbumPhotos> albumPhotos) {
//        this.context = context;
        this.albumPhotos = albumPhotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PhotosAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(albumPhotos.get(position).getTitle());
        String imageUrl = albumPhotos.get(position).getUrl();
        String thumbUrl = albumPhotos.get(position).getThumbnailUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get().load(imageUrl).into(holder.image);
        }
        if (!TextUtils.isEmpty(thumbUrl)) {
            Picasso.get().load(thumbUrl).into(holder.thumb);
        }

    }

    @Override
    public int getItemCount() {
        return albumPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image, thumb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image_view);
            thumb = itemView.findViewById(R.id.thumbnail_image_view);

        }
    }
}
