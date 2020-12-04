package com.test.testapp.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.test.testapp.R;
import com.test.testapp.ui.MainActivity;

public class NotificationUtils {

    private Context context;
    private NotificationManagerCompat notificationManager;

    public NotificationUtils(Context context) {
        this.context = context;
        notificationManager = NotificationManagerCompat.from(context);
    }


    public void createNotificationChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void sendNotification(int currentNumber, String keyExtra, int rcName, String channelId) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.putExtra(keyExtra, currentNumber);
        PendingIntent contentIntent = PendingIntent.getActivity(context, rcName, activityIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_description) + currentNumber)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(currentNumber, builder.build());
    }
    public void cancelNotif(int currentNumber){
        notificationManager.cancel(currentNumber);

    }
}
