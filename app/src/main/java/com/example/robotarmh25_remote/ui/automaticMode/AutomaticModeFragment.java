package com.example.robotarmh25_remote.ui.automaticMode;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robotarmh25_remote.R;

public class AutomaticModeFragment extends Fragment {

    private AutomaticModeViewModel mViewModel;

    public static AutomaticModeFragment newInstance() {
        return new AutomaticModeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_automatic_mode, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AutomaticModeViewModel.class);
        // TODO: Use the ViewModel
    }

}