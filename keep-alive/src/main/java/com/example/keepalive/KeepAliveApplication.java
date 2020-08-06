package com.example.keepalive;

import android.app.Application;

public class KeepAliveApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Keeper.start(this);
    }
}
