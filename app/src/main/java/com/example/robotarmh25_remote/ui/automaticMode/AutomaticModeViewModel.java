package com.example.robotarmh25_remote.ui.automaticMode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AutomaticModeViewModel extends ViewModel {
    private MutableLiveData<String> textLiveData;
    /**
     * Constructor for the AutomaticModeViewModel class. Initializes the MutableLiveData object and sets
     * its default value.
     */
    public AutomaticModeViewModel() {
        textLiveData = new MutableLiveData<>();
        textLiveData.setValue("This is autonomous mode fragment");
    }
    /**
     * Returns the LiveData object containing the text to be displayed in the fragment.
     * @return the LiveData object containing the text to be displayed in the fragment
     */
    public LiveData<String> getText() {
        return textLiveData;
    }
}