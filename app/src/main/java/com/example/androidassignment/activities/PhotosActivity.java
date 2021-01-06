package com.example.androidassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.androidassignment.R;
import com.example.androidassignment.adapters.AlbumAdapter;
import com.example.androidassignment.adapters.PhotosAdapter;
import com.example.androidassignment.managers.ApiCallManagers;
import com.example.androidassignment.models.User;
import com.example.androidassignment.models.UserAlbum;
import com.example.androidassignment.models.UserAlbumPhotos;
import com.example.androidassignment.models.UserAlbumPhotosUpdateEvent;
import com.example.androidassignment.models.UserAlbumUpdateEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity {
    public static final String TAG = PhotosActivity.class.getSimpleName();
    RecyclerView photosRecyclerView;
    private String albumId;
    private String albumName;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private TextView toolbarTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        EventBus.getDefault().register(this);

        bindingViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        albumId = getIntent().getStringExtra(UserAlbum.ID);
        albumName = getIntent().getStringExtra(UserAlbum.TITLE);
        toolbarTextView.setText("PHOTO'S IN " + albumName);

        ApiCallManagers apiCallManagers = new ApiCallManagers(this);
        if (apiCallManagers.hasInternetAccess()) {
            apiCallManagers.getAlbumPhotos(albumId);
        } else {
            Toast.makeText(this, "No internet", Toast.LENGTH_LONG).show();
        }
    }

    private void bindingViews() {
        toolbar = findViewById(R.id.toolbar);
        photosRecyclerView = findViewById(R.id.photos_recycler);
        photosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress_circular);
        toolbarTextView = findViewById(R.id.toolbar_text_view);
    }


    @Subscribe
    public void onAlbumPhotosUpdate(UserAlbumPhotosUpdateEvent event) {
        progressBar.setVisibility(View.GONE);
        ArrayList<UserAlbumPhotos> photos = new ArrayList<>();
        photos = event.getPhotos();
        PhotosAdapter photosAdapter = new PhotosAdapter(this, photos);
        photosRecyclerView.setAdapter(photosAdapter);
        Log.i(TAG, String.valueOf(photos.size()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}