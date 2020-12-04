package com.test.testapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String CHANNEL_ID = "CHANNEL_ID";
    private FloatingActionButton fab_minus, fab_plus;
    private AppCompatButton btn_notification;
    private ViewPager viewPager;
    private MyPageAdapter pagerAdapter;
    private int currentNumber = 1;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_minus = findViewById(R.id.activity_main_btn_minus);
        fab_plus = findViewById(R.id.activity_main_btn_plus);
        btn_notification = findViewById(R.id.activity_main_btn_create_notify);

        viewPager = findViewById(R.id.main_activity_vp);
        pagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FragmentMain.newInstance(currentNumber));
        viewPager.setAdapter(pagerAdapter);
        notificationManager = NotificationManagerCompat.from(this);

        fabMinusCheck();

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumber++;
                Log.d(TAG, "onClick: " + currentNumber);
                fabMinusCheck();
                pagerAdapter.addFragment(FragmentMain.newInstance(currentNumber));
                viewPager.setCurrentItem(currentNumber);

            }
        });
        fab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.cancel(currentNumber);
                currentNumber--;
                fabMinusCheck();
                pagerAdapter.removeFragment();
                viewPager.setCurrentItem(currentNumber);

            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNotificationChannel();
                sendNotification();

            }
        });
        int notificationNum = getIntent().getIntExtra("currentNumber", 0);

        pagerAdapter.getItem(notificationNum);

    }

    private void fabMinusCheck() {
        if (currentNumber < 2) {
            fab_minus.setVisibility(View.INVISIBLE);
        } else
            fab_minus.setVisibility(View.VISIBLE);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    private void sendNotification() {
        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.putExtra("currentNumber", currentNumber);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, activityIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("title")
                .setContentText("fragment " + currentNumber)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(currentNumber, builder.build());
    }
}


