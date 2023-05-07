package com.example.robotarmh25_remote.ui.authenticate.userDeleting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.robotarmh25_remote.utilities.DBHandler;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.ui.authenticate.AuthenticateFragment;
import com.example.robotarmh25_remote.user.User;
import com.example.robotarmh25_remote.utilities.RedirectionService;

/**
 * Fragment to delete an user
 */
public class UserDeletingFragment extends Fragment {
    private View fragmentView;
    private Context context;
    private DBHandler db;
    private User user = User.getInstance();
    private RedirectionService redirectionService = new RedirectionService();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.signout_layout, container, false);
        context = this.getActivity();
        db = new DBHandler(context);

        final Button noButton = fragmentView.findViewById(R.id.noButton);
        final Button yesButton = fragmentView.findViewById(R.id.yesButton);

        // listener on the deleting button
        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    // we disconnect the user
                    user.setConnected(false);
                    // we delete the user of the database
                    db.deleteUser(user.username);
                    user.setUsername("");
                    Toast.makeText(context, "Utilisateur supprimé.", Toast.LENGTH_SHORT).show();
                    // we get back the user to the authenticate menu
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                } catch (Exception e){
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }

            }
        });

        // redirection on the authenticate menu
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                redirectionService.redirect(new AuthenticateFragment(),fragmentTransaction);
            }
        });
        return this.fragmentView;
    }
}
