package com.example.robotarmh25_remote.ui.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RemoteViewModel extends ViewModel {
    private MutableLiveData<String> textLiveData;
    /**
     * Constructor for the RemoteViewModel class. Initializes the MutableLiveData object and sets
     * its default value.
     */
    public RemoteViewModel() {
        textLiveData = new MutableLiveData<>();
        textLiveData.setValue("This is gallery fragment");
    }
    /**
     * Returns the LiveData object containing the text to be displayed in the fragment.
     * @return the LiveData object containing the text to be displayed in the fragment
     */
    public LiveData<String> getText() {
        return textLiveData;
    }
}