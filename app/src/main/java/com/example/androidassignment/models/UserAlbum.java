package com.example.androidassignment.models;

public class UserAlbum {

    public static String USER_ID = "userId";
    public static String ID = "id";
    public static String TITLE = "title";

    String userId;
    String id;
    String title;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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


//    "userId": 1,
//            "id": 2,
//            "title": "sunt qui excepturi placeat culpa"
}
