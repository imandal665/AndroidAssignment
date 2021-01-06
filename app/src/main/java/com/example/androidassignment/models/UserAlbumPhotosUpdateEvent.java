package com.example.androidassignment.models;

import java.util.ArrayList;

public class UserAlbumPhotosUpdateEvent {
    ArrayList<UserAlbumPhotos> photos = new ArrayList<>();

    public UserAlbumPhotosUpdateEvent(ArrayList<UserAlbumPhotos> photos) {
        this.photos = photos;
    }

    public ArrayList<UserAlbumPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<UserAlbumPhotos> photos) {
        this.photos = photos;
    }
}
