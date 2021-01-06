package com.example.androidassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.androidassignment.R;
import com.example.androidassignment.adapters.AlbumAdapter;
import com.example.androidassignment.adapters.UserListAdapter;
import com.example.androidassignment.managers.ApiCallManagers;
import com.example.androidassignment.models.User;
import com.example.androidassignment.models.UserAlbum;
import com.example.androidassignment.models.UserAlbumUpdateEvent;
import com.example.androidassignment.models.UserListUpdatedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ApiCallManagers apiCallManagers;
    private RecyclerView recyclerView;
    private ArrayList<User> userArrayList;
    private UserListAdapter userListAdapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        bindingViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        apiCallManagers = new ApiCallManagers(this);
        apiCallManagers.getUsers();
    }

    private void bindingViews() {
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_circular);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<>();
    }


    @Subscribe
    public void onUserListUpdate(UserListUpdatedEvent event) {
        progressBar.setVisibility(View.GONE);
        userArrayList = new ArrayList<>();
        userArrayList = event.getUserArrayList();
        userListAdapter = new UserListAdapter(this, userArrayList);
        recyclerView.setAdapter(userListAdapter);
        Log.i(TAG, String.valueOf(userArrayList.size()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}