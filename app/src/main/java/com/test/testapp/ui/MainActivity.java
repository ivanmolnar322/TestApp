package com.test.testapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.test.testapp.utils.NotificationUtils;
import com.test.testapp.R;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final String KEY_EXTRA = "currentNumber";
    public static final int RC_NAME = 1;
    public MainActivityViewModel viewModel;
    private AppCompatImageButton btnMinus;
    private ViewPager viewPager;
    private MyPageAdapter pagerAdapter;
    private NotificationUtils notificationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        notificationUtils = new NotificationUtils(this);

        btnMinus = findViewById(R.id.activity_main_btn_minus);
        AppCompatImageButton btnPlus = findViewById(R.id.activity_main_btn_plus);
        AppCompatButton btnNotification = findViewById(R.id.activity_main_btn_create_notify);

        viewPager = findViewById(R.id.main_activity_view_pager);
        pagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FragmentMain.newInstance(viewModel.currentNumber));
        viewPager.setAdapter(pagerAdapter);


        viewModel.fabMinusCheck();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onClickPlus();
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onClickMinus();
            }
        });
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onClickNotification();
            }
        });

        viewModel.minusBtnVisibility.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean needToHide) {
                btnMinus.setVisibility(needToHide ? View.INVISIBLE : View.VISIBLE);
            }
        });
        viewModel.btnPlusClickEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentNumber) {
                insertPage(currentNumber);
            }
        });
        viewModel.btnMinusClickEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentNumber) {
                notificationUtils.cancelNotif(currentNumber + 1);
                removePage(currentNumber);
            }
        });
        viewModel.btnNotifClickEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentNumber) {
                notificationUtils.createNotificationChannel(CHANNEL_ID);
                notificationUtils.sendNotification(currentNumber, KEY_EXTRA, RC_NAME, CHANNEL_ID);
            }
        });

    }

    private void insertPage(int currentNumber) {
        pagerAdapter.addFragment(FragmentMain.newInstance(currentNumber));
        viewPager.setCurrentItem(currentNumber);
    }

    private void removePage(int currentNumber) {
        pagerAdapter.removeFragment();
        viewPager.setCurrentItem(currentNumber);
    }
}



