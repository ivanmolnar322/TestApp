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

    AppCompatTextView tvNumFragment;
    int numFragment = 0;

    static FragmentMain newInstance(int numFragment) {
        FragmentMain pageFragment = new FragmentMain();
        Bundle arguments = new Bundle();
        arguments.putInt("numFragment", numFragment);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        numFragment = getArguments().getInt("numFragment");
        tvNumFragment = view.findViewById(R.id.fragment_main_tv_num_fragment);
        tvNumFragment.setText(String.valueOf(numFragment));
        return view;

    }
}

