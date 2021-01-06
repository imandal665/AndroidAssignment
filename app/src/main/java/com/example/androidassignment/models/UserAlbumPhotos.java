package com.example.androidassignment.models;

public class UserAlbumPhotos {

    public static String ALBUM_ID = "albumId";
    public static String ID = "id";
    public static String TITLE = "title";
    public static String URL = "url";
    public static String THUMBNAIL_URL = "thumbnailUrl";

    String albumId;
    String id;
    String title;
    String url;
    String thumbnailUrl;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    //     "albumId": 1,
//             "id": 1,
//             "title": "accusamus beatae ad facilis cum similique qui sunt",
//             "url": "https://via.placeholder.com/600/92c952",
//             "thumbnailUrl": "https://via.placeholder.com/150/92c952"
}
