package com.example.homepage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int img = intent.getIntExtra("img", -1);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
    //        Intent notificationIntent = new Intent(this, NotificationDetailActivity.class);
    //        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //        notificationIntent.putExtra("img", img);
    //        notificationIntent.putExtra("title", title);
    //        notificationIntent.putExtra("content", content);
    }
}
