package com.example.homepage.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.homepage.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Uri imgurl = remoteMessage.getNotification().getImageUrl();
            Log.d(TAG, "Message Notification Title: " + title);
            Log.d(TAG, "Message Notification Body: " + body);
            try {
                sendNotification(title, body, imgurl);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendNotification(String messageTitle, String messageBody, Uri imgurl) throws IOException {
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        intent.putExtra("title", messageTitle);
        intent.putExtra("content", messageBody);
        intent.putExtra("img", imgurl.toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "NotificationChannel")
                        .setSmallIcon(R.drawable.logo)
                        .setLargeIcon(Picasso.with(getApplicationContext()).load(imgurl).get())
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(Picasso.with(getApplicationContext()).load(imgurl).get())
                                .bigLargeIcon(null))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("NotificationChannel", "Push notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
