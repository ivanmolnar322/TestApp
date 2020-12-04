package com.test.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment {

    public static String KEY_NUMBER = "numFragment";
    int numFragment = 0;

    static FragmentMain newInstance(int numFragment) {
        FragmentMain pageFragment = new FragmentMain();
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_NUMBER, numFragment);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        if (getArguments() != null) {
            numFragment = getArguments().getInt(KEY_NUMBER);
        }
        AppCompatTextView tvNumFragment = view.findViewById(R.id.fragment_main_tv_num_fragment);
        tvNumFragment.setText(String.valueOf(numFragment));
        return view;
    }
}

