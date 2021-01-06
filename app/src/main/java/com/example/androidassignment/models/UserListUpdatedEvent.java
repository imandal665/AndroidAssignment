package com.example.androidassignment.models;

import java.util.ArrayList;

public class UserListUpdatedEvent {
    ArrayList<User> userArrayList = new ArrayList<>();

    public UserListUpdatedEvent(ArrayList<User> arrayList) {
        this.userArrayList = arrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
