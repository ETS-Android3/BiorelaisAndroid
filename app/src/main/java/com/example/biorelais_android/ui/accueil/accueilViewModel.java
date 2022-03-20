package com.example.biorelais_android.ui.accueil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class accueilViewModel {


    private MutableLiveData<String> mText;

    public accueilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ceci est l'accueil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
