package com.example.androidassignment.models;

public class Address {
    public static String STREET="street";
    public static String SUITE="suite";
    public static String CITY="city";
    public static String ZIP_CODE="zipcode";
    public static String GEO="geo";

    String street;
    String suite;
    String city;
    String zipcode;
    String geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }
//     "street": "Kulas Light",
//             "suite": "Apt. 556",
//             "city": "Gwenborough",
//             "zipcode": "92998-3874",
//             "geo": {
//        "lat": "-37.3159",
//                "lng": "81.1496"
//    }
}
