package com.example.foodie.foodie.Helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.example.foodie.foodie.R;

public class NotificationHelper extends ContextWrapper {

    private static final String UTP_CHANNEL_ID = "com.example.foodie.foodie.UTP";
    private static final String UTP_CHANNEL_NAME = "UTP Foodie";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // only working if API is 26 or higher
            createChannel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel utpchannel = new NotificationChannel(UTP_CHANNEL_ID,
                UTP_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        utpchannel.enableLights(false);
        utpchannel.enableVibration(true);
        utpchannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(utpchannel);
    }

    public NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public android.app.Notification.Builder getFoodieChannelNotification(String title, String body, PendingIntent contentIntent,
                                                                         Uri soundUri){
        return new android.app.Notification.Builder(getApplicationContext(),UTP_CHANNEL_ID)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }
}
