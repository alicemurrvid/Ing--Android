package com.example.robotarmh25_remote;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.robotarmh25_remote.user.User;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * The main activity of the app
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static User user = User.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setContentView(R.layout.activity_main_connexion);// TODO corriger lINTENTseconde activité display lapremière

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_connexion);
        NavigationView navigationView = findViewById(R.id.nav_view_connexion);

        // if the user isn't connected
        if (!user.isConnected) {
            // we create a limited menu to restrict the users action
            Menu m = navigationView.getMenu();
            MenuItem mac = m.findItem(R.id.nav_connect);
            MenuItem rem = m.findItem(R.id.nav_remote);
            MenuItem conf = m.findItem(R.id.nav_config);
            MenuItem aut = m.findItem(R.id.nav_auto);
            mac.setVisible(false);
            rem.setVisible(false);
            conf.setVisible(false);
            aut.setVisible(false);
        }
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_authentication,R.id.nav_connect, R.id.nav_remote, R.id.nav_config,R.id.nav_auto)
                .setDrawerLayout(drawer)
                .build();
        NavController navController;
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}