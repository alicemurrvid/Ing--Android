package com.example.robotarmh25_remote.ui.authenticate.logIn;

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

import com.example.robotarmh25_remote.utilities.DBHandler;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.ui.authenticate.AuthenticateFragment;
import com.example.robotarmh25_remote.user.User;

/**
 * Fragment maneging to user's log in
 */
public class LogInFragment extends Fragment {

    private View fragmentView;
    private Context context;
    private DBHandler db;
    private User user = User.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.login_layout, container, false);
        context = this.getActivity();
        db = new DBHandler(context);

        final Button logInConnexionButton = fragmentView.findViewById(R.id.logInConnexionButton);
        final Button backConnexionButton = fragmentView.findViewById(R.id.backConnexionButton);

        final TextView userName = fragmentView.findViewById(R.id.nameConnexionText);
        final TextView pwd = fragmentView.findViewById(R.id.pwdConnexionText);

        // Listener on the butto log in
        logInConnexionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    // If the user is known by the database
                    if (!db.getUserName(userName.getText().toString())) {//TODO error get
                        // If the password send by the user corresponding that of the database
                        if (db.getUserPwd(userName.getText().toString(), pwd.getText().toString())) {
                            Toast.makeText(context, "Connexion réussi", Toast.LENGTH_SHORT).show();
                            // The user is connected
                            user.setConnected(true);
                            user.setUsername(userName.getText().toString());
                            // We reload the activity
                            getActivity().finish();
                            startActivity(getActivity().getIntent());
                        } else {
                            Toast.makeText(context, "Erreur: mot de passe incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Erreur: utilisateur non reconnu.", Toast.LENGTH_SHORT).show();
                    }
                } catch ( Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });

        /**
         * go back the the authenticate menu
         */
        backConnexionButton.setOnClickListener(new View.OnClickListener() {
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
