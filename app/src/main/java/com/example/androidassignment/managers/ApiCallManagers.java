package com.example.androidassignment.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.androidassignment.models.PhotosUpdateEvent;
import com.example.androidassignment.models.User;
import com.example.androidassignment.models.UserAlbum;
import com.example.androidassignment.models.UserAlbumPhotos;
import com.example.androidassignment.models.UserAlbumPhotosUpdateEvent;
import com.example.androidassignment.models.UserAlbumUpdateEvent;
import com.example.androidassignment.models.UserListUpdatedEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class ApiCallManagers {
    public static final String TAG = ApiCallManagers.class.getSimpleName();
    private Context context;
    public static final String CURRENT_METHOD = "current_method";

    public ApiCallManagers(Context context) {
        this.context = context;
    }

    public void getUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        AndroidNetworking.get(ApiClient.GET_USERS)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int a = 0; a < response.length(); a++) {

                                JSONObject object = (JSONObject) response.get(a);
                                User user = new User();
                                if (object.has(User.ID)) {
                                    user.setId(object.getString(User.ID));
                                }
                                if (object.has(User.NAME)) {
                                    user.setName(object.getString(User.NAME));
                                }
                                if (object.has(User.USER_NAME)) {
                                    user.setUsername(object.getString(User.USER_NAME));
                                }
                                if (object.has(User.EMAIL)) {
                                    user.setEmail(object.getString(User.EMAIL));
                                }
                                if (object.has(User.ADDRESS)) {
                                    user.setAddress(object.getString(User.ADDRESS));
                                }
                                if (object.has(User.PHONE)) {
                                    user.setPhone(object.getString(User.PHONE));
                                }
                                if (object.has(User.WEBSITE)) {
                                    user.setWebsite(object.getString(User.WEBSITE));
                                }
                                if (object.has(User.COMPANY)) {
                                    user.setCompany(object.getString(User.COMPANY));
                                }
                                userArrayList.add(user);
                            }
                            EventBus.getDefault().post(new UserListUpdatedEvent(userArrayList));
                        } catch (Exception e) {
                        }
//                        Log.i(TAG, "succeded");
                    }


                    @Override
                    public void onError(ANError anError) {
//                        Log.i(TAG, "failed");
                    }
                });

    }

    public void getUserAlbum(String id) {
        ArrayList<UserAlbum> userAlbumArrayList = new ArrayList<>();
        AndroidNetworking.get(ApiClient.GET_USERS + "/" + id + "/albums")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int a = 0; a < response.length(); a++) {

                                JSONObject object = (JSONObject) response.get(a);
                                UserAlbum album = new UserAlbum();
                                if (object.has(UserAlbum.ID)) {
                                    album.setId(object.getString(UserAlbum.ID));
                                }
                                if (object.has(UserAlbum.USER_ID)) {
                                    album.setUserId(object.getString(UserAlbum.USER_ID));
                                }
                                if (object.has(UserAlbum.TITLE)) {
                                    album.setTitle(object.getString(UserAlbum.TITLE));
                                }

                                userAlbumArrayList.add(album);
                            }
                            EventBus.getDefault().post(new UserAlbumUpdateEvent(userAlbumArrayList));
                        } catch (Exception e) {

                        }
//                        Log.i(TAG, "succeded");
                    }


                    @Override
                    public void onError(ANError anError) {
                        Log.i(TAG, "failed");
                    }
                });
    }

    public void getAlbumPhotos(String albumId) {
        ArrayList<UserAlbumPhotos> albumPhotos = new ArrayList<>();
        AndroidNetworking.get(ApiClient.BASE_URL + "albums" + "/" + albumId + "/photos")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int a = 0; a < response.length(); a++) {
                                JSONObject object = (JSONObject) response.get(a);
                                UserAlbumPhotos photos = new UserAlbumPhotos();
                                if (object.has(UserAlbumPhotos.ID)) {
                                    photos.setId(object.getString(UserAlbumPhotos.ID));
                                }
                                if (object.has(UserAlbumPhotos.ALBUM_ID)) {
                                    photos.setAlbumId(object.getString(UserAlbumPhotos.ALBUM_ID));
                                }
                                if (object.has(UserAlbumPhotos.TITLE)) {
                                    photos.setTitle(object.getString(UserAlbumPhotos.TITLE));
                                }
                                if (object.has(UserAlbumPhotos.URL)) {
                                    photos.setUrl(object.getString(UserAlbumPhotos.URL));
                                }
                                if (object.has(UserAlbumPhotos.THUMBNAIL_URL)) {
                                    photos.setThumbnailUrl(object.getString(UserAlbumPhotos.THUMBNAIL_URL));
                                }
                                albumPhotos.add(photos);
                            }
                            EventBus.getDefault().post(new UserAlbumPhotosUpdateEvent(albumPhotos));
//                            Log.i(TAG, "photos list-" + albumPhotos);
                        } catch (Exception e) {

                        }
//                        Log.i(TAG, "succeded");
                    }


                    @Override
                    public void onError(ANError anError) {
                        Log.i(TAG, "failed");
                    }
                });

    }

    public boolean hasInternetAccess() {
        if (Build.FINGERPRINT.contains("generic")) {

            return hasNetwork();

        } else {
            if (hasNetwork()) {

                Runtime runtime = Runtime.getRuntime();
                try {

                    Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                    int exitValue = ipProcess.waitFor();
                    return (exitValue == 0);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public Boolean hasNetwork() {
        boolean result = false;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            result = true;
        }
        return result;
    }
}
