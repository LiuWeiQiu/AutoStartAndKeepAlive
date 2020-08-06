package com.example.keepalive;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class Keeper {
    private static final String TAG = "keep-alive";

    public static void start(Context context) {
        Log.i(TAG, "start: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.getApplicationContext()
                    .startForegroundService(new Intent(context, KeepAliveService.class));
        } else {
            context.getApplicationContext()
                    .startService(new Intent(context, KeepAliveService.class));
        }
    }
}
