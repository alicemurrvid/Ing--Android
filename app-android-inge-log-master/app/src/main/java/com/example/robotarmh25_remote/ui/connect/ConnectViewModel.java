package com.example.robotarmh25_remote.ui.connect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConnectViewModel extends ViewModel {
    private static final String DEFAULT_TEXT = "This is home fragment";
    private final MutableLiveData<String> textLiveData ;
    public ConnectViewModel() {
        textLiveData  = new MutableLiveData<>();
        textLiveData .setValue(DEFAULT_TEXT );
    }
    // Public method for getting the LiveData object containing the text value
    public LiveData<String> getText() {
        return textLiveData ;
    }
}