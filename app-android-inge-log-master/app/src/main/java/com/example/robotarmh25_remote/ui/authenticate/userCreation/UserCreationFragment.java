package com.example.robotarmh25_remote.ui.authenticate.userCreation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.robotarmh25_remote.DBHandler;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.ui.authenticate.AuthenticateFragment;

/**
 * Fragment to create an user
 */
public class UserCreationFragment extends Fragment {
    private View fragmentView;
    private Context context;
    private DBHandler db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.user_creation_layout, container, false);
        context = this.getActivity();
        db = new DBHandler(context);

        final Button signInCreationButton = fragmentView.findViewById(R.id.signInCreationButton);
        final Button backCreationButton = fragmentView.findViewById(R.id.backCreationButton);

        final TextView userName = fragmentView.findViewById(R.id.nameCreationText);
        final TextView pwdCreationText = fragmentView.findViewById(R.id.pwdCreationText);
        final TextView pwdConfirmCreationText = fragmentView.findViewById(R.id.pwdConfirmCreationText);

        // Listener on the creation button
        signInCreationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if the password and confirmation password corresponds
                if (pwdCreationText.getText().toString().equals(pwdConfirmCreationText.getText().toString())){
                    try {
                        // If the user name is available
                        if (db.getUserName(userName.getText().toString())) {
                            // We create the user
                            db.createUser(userName.getText().toString(), pwdCreationText.getText().toString());
                            Toast.makeText(context, "Utilisateur créé", Toast.LENGTH_SHORT).show();
                            // Redirection the the authenticate menu
                            redirect(new AuthenticateFragment());
                        } else {
                            Toast.makeText(context, "Erreur: nom d'utilisateur déjà pris.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                        Log.e("Base de données", e.getMessage());
                    }
                } else{
                    Toast.makeText(context, "Erreur: mots de passe différents.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Go back to the authenticate menu
         */
        backCreationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redirect(new AuthenticateFragment());
            }
        });
        return this.fragmentView;
    }


    /**
     * fonction of redirection to a another fragment
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
