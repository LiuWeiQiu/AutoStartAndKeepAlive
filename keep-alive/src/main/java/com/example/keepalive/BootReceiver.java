package com.example.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "keep-alive";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "BootReceiver onReceive: ");
        Keeper.start(context);
    }
}
