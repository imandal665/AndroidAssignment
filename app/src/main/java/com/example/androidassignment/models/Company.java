package com.example.androidassignment.models;

public class Company {

    public static String NAME = "name";
    public static String CATCH_PHRASE = "catchPhrase";
    public static String BS = "bs";

    String name;
    String catchPhrase;
    String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    //    "company": {
//        "name": "Romaguera-Crona",
//                "catchPhrase": "Multi-layered client-server neural-net",
//                "bs": "harness real-time e-markets"
//    }
}
