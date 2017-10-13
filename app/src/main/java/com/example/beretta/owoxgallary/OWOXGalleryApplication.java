package com.example.beretta.owoxgallary;

import android.app.Application;

import com.example.beretta.owoxgallary.network.ApiUnsplash;

/**
 * Created by beretta on 12.10.2017.
 */

public class OWOXGalleryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Create singleton
        ApiUnsplash.getInstance();
    }
}
