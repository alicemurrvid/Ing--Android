package com.example.robotarmh25_remote.ui.remote;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.robotarmh25_remote.BluetoothConnection;
import com.example.robotarmh25_remote.DBHandler;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.RepositoryScenario.Scenario;
import com.example.robotarmh25_remote.ui.connect.ConnectFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class autonomousFragment extends Fragment {

    private Scenario scenario1;
    private Scenario scenario2;
    private BluetoothConnection btCon;
    private Context context;
    private View fragmentView;
    private Button firstScenario,secondScenario,thirdScenario,fourScenario,fiveScenario,enregistrer;
    private Scenario scenario;
    private ArrayList<String> affichage;
    private DBHandler dbHandler;
    private ArrayAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_autonomous, container, false);
        context = this.getActivity();

        //bluetooth connexion
        btCon = ConnectFragment.btCon;
        //database
        dbHandler = new DBHandler(context);

        // Buttons

        enregistrer    = fragmentView.findViewById(R.id.addScenarioButton);
        firstScenario  = fragmentView.findViewById(R.id.firstScenario);
        secondScenario = fragmentView.findViewById(R.id.secondScenario);
        thirdScenario  = fragmentView.findViewById(R.id.thirdScenario);
        fourScenario   = fragmentView.findViewById(R.id.fourScenario);
        fiveScenario   = fragmentView.findViewById(R.id.fiveScenario);

        //List off scenario

        scenario= new Scenario();
        affichage = new ArrayList<String>();
        List<Scenario.TypeTask> tasks1 = Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN);
        List<Scenario.TypeTask> tasks2 = Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN);
        List<Scenario.TypeTask> tasks3 = Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN);
        List<Scenario.TypeTask> tasks4 = Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN);
        List<Scenario.TypeTask> tasks5 = Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN);

        enregistrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    dbHandler.addNewScenario(scenario);
                    Toast.makeText(context, "Scénario lancé", Toast.LENGTH_SHORT).show();
                    affichage.clear();
                    adapter.notifyDataSetChanged();

                    //Verify the insert
                    /*HashMap<String, ArrayList<String>> test = new  HashMap<String, ArrayList<String>>();
                    test = dbHandler.getData();
                    Log.d("Base de données", test.toString());*/
                } catch (Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });
        firstScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                affichage.add("Rotation antihoraire");
                affichage.add("Rotation horaire");
                affichage.add("Baisser bras");
                affichage.add("Ouvrir pince");
                scenario.addListTask(tasks1);
            }
        });

        secondScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                affichage.add("Rotation horaire");
                adapter.notifyDataSetChanged();
                scenario.addListTask(tasks2);
            }
        });
        thirdScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                affichage.add("Rotation horaire");
                adapter.notifyDataSetChanged();
                scenario.addListTask(tasks3);
            }
        });
        fourScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                affichage.add("Rotation horaire");
                adapter.notifyDataSetChanged();
                scenario.addListTask(tasks4);
            }
        });
        fiveScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                affichage.add("Rotation horaire");
                adapter.notifyDataSetChanged();
                scenario.addListTask(tasks5);
            }
        });

        return fragmentView;
    }
}