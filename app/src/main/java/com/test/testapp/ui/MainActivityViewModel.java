package com.test.testapp.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<Boolean> minusBtnVisibility = new MutableLiveData<>();
    public MutableLiveData<Integer> btnMinusClickEvent = new MutableLiveData<>();
    public MutableLiveData<Integer> btnPlusClickEvent = new MutableLiveData<>();
    public MutableLiveData<Integer> btnNotifClickEvent = new MutableLiveData<>();
    public int currentNumber = 0;


    public void fabMinusCheck() {
        minusBtnVisibility.setValue(currentNumber < 2);

    }

    public void onClickPlus() {
        currentNumber++;
        fabMinusCheck();
        btnPlusClickEvent.setValue(currentNumber);

    }

    public void onClickMinus(){
        currentNumber--;
        fabMinusCheck();
        btnMinusClickEvent.setValue(currentNumber);
    }
    public void onClickNotification(){

        btnNotifClickEvent.setValue(currentNumber);
    }

}
