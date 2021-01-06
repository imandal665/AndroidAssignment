package com.example.androidassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    public static String ID = "id";
    public static String NAME = "name";
    public static String USER_NAME = "username";
    public static String EMAIL = "email";
    public static String ADDRESS = "address";
    public static String PHONE = "phone";
    public static String WEBSITE = "website";
    public static String COMPANY = "company";

    String id;
    String name;
    String username;
    String email;
    String address;
    String phone;
    String website;
    String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    //    "id":1,
//            "name":"Leanne Graham",
//            "username":"Bret",
//            "email":"Sincere@april.biz",
//            "address":
//
//    {
//        "street":"Kulas Light",
//            "suite":"Apt. 556",
//            "city":"Gwenborough",
//            "zipcode":"92998-3874",
//            "geo":{
//        "lat":"-37.3159",
//                "lng":"81.1496"
//    }
//    },
//            "phone":"1-770-736-8031 x56442",
//            "website":"hildegard.org",
//            "company":
//
//    {
//        "name":"Romaguera-Crona",
//            "catchPhrase":"Multi-layered client-server neural-net",
//            "bs":"harness real-time e-markets"
//    }

}
