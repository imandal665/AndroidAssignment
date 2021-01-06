package com.example.androidassignment.models;

import java.util.ArrayList;

public class PhotosUpdateEvent {
    ArrayList<UserAlbumPhotos> albumPhotos = new ArrayList<>();

    public PhotosUpdateEvent(ArrayList<UserAlbumPhotos> albumPhotos) {
        this.albumPhotos = albumPhotos;
    }

    public ArrayList<UserAlbumPhotos> getAlbumPhotos() {
        return albumPhotos;
    }

    public void setAlbumPhotos(ArrayList<UserAlbumPhotos> albumPhotos) {
        this.albumPhotos = albumPhotos;
    }
}

