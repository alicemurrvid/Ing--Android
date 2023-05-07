package com.example.robotarmh25_remote.ui.authenticate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.ui.authenticate.logIn.LogInFragment;
import com.example.robotarmh25_remote.ui.authenticate.userCreation.UserCreationFragment;
import com.example.robotarmh25_remote.ui.authenticate.userDeleting.UserDeletingFragment;
import com.example.robotarmh25_remote.user.User;

/**
 * Fragment to authenticate the user
 */
public class AuthenticateFragment extends Fragment {

    private View fragmentView;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // if the user isn't connected
        if (!User.getInstance().isConnected) {
            // we create the menu to connect/sign in the user
            this.fragmentView = inflater.inflate(R.layout.authenticate_layout, container, false);
            this.context = this.getActivity();

            final Button signInButton = fragmentView.findViewById(R.id.signInButton);
            final Button logInButton = fragmentView.findViewById(R.id.logInButton);

            // listener on the log in button
            // go to the log in menu
            logInButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    redirect(new LogInFragment());
                }
            });

            // listener on the sign in button
            // go to the userCreation menu
            signInButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    redirect(new UserCreationFragment());
                }
            });

            return this.fragmentView;
        }
        else {
            // if the user is connected
            // we create the menu to disconnect/sign out the user
            this.fragmentView = inflater.inflate(R.layout.authenticate_v2_layout, container, false);
            this.context = this.getActivity();

            final Button signOutButton = fragmentView.findViewById(R.id.signOutButton);
            final Button logOutButton = fragmentView.findViewById(R.id.logOutButton);

            signOutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    redirect(new UserDeletingFragment());
                }
            });

            logOutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    User.getInstance().setConnected(false);
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }
            });

            return this.fragmentView;
        }
    }

    /**
     * fonction to redirect the user to an another fragment
     * @param frag
     */
    public void redirect(Fragment frag) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
