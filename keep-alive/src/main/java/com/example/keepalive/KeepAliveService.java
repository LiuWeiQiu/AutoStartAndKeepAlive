package com.example.keepalive;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class KeepAliveService extends Service {
    private static final String TAG = "keep-alive";

    private static final String CHANNEL_ID = "com.example.keep.alive";
    private static final String CHANNEL_NAME = "Keep Alive Service";

    private static int i = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground();
    }

    private void startForeground() {
        Log.i(TAG, "KeepAliveService startForeground: ");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager == null) {
                Log.e(TAG, "getSystemService for NotificationManager failed.");
                return;
            }

            manager.createNotificationChannel(notificationChannel);
            startForeground(1, new NotificationCompat.Builder(this, CHANNEL_ID).build());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "" + i++);
                }
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
