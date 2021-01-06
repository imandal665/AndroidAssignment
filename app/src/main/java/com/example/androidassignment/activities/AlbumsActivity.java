package com.example.androidassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidassignment.R;
import com.example.androidassignment.adapters.AlbumAdapter;
import com.example.androidassignment.managers.ApiCallManagers;
import com.example.androidassignment.models.User;
import com.example.androidassignment.models.UserAlbum;
import com.example.androidassignment.models.UserAlbumUpdateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class AlbumsActivity extends AppCompatActivity {
    public static final String TAG = AlbumsActivity.class.getSimpleName();
    RecyclerView albumRecycler;
    private AlbumAdapter albumAdapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        EventBus.getDefault().register(this);

        bindingViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        String id = getIntent().getStringExtra(User.ID);
        String name = getIntent().getStringExtra(User.NAME);
        toolbarTitle.setText(name + "'s " + "ALBUM");

        ApiCallManagers managers = new ApiCallManagers(this);
        if (managers.hasInternetAccess()){
        managers.getUserAlbum(id);}else {
            Toast.makeText(this, "No internet", Toast.LENGTH_LONG).show();
        }
    }

    private void bindingViews() {
        albumRecycler = findViewById(R.id.album_recycler);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_circular);
        toolbarTitle = findViewById(R.id.toolbar_text_view);
        albumRecycler.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Subscribe
    public void onUserAlbunUpdate(UserAlbumUpdateEvent event) {
        progressBar.setVisibility(View.GONE);
        ArrayList<UserAlbum> albums = new ArrayList<>();
        albums = event.getUserAlbums();
        AlbumAdapter albumAdapter = new AlbumAdapter(this, albums);
        albumRecycler.setAdapter(albumAdapter);
        Log.i(TAG, String.valueOf(albums.size()));
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