package com.example.robotarmh25_remote.utilities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.robotarmh25_remote.R;

public class RedirectionService {

    public RedirectionService (){}
    /**
     * fonction to redirect the user to an another fragment
     * @param frag
     * @param fragmentTransaction
     */
    public void redirect(Fragment frag, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.nav_host_fragment, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
