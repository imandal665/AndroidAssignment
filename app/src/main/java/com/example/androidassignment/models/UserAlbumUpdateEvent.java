package com.example.androidassignment.models;

import java.util.ArrayList;

public class UserAlbumUpdateEvent {
    ArrayList<UserAlbum> userAlbums = new ArrayList<>();

    public UserAlbumUpdateEvent(ArrayList<UserAlbum> userAlbums) {
        this.userAlbums = userAlbums;
    }

    public ArrayList<UserAlbum> getUserAlbums() {
        return userAlbums;
    }

    public void setUserAlbums(ArrayList<UserAlbum> userAlbums) {
        this.userAlbums = userAlbums;
    }
}
