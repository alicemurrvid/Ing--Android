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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robotarmh25_remote.BluetoothConnection;
import com.example.robotarmh25_remote.DBHandler;
import com.example.robotarmh25_remote.MyCustomAdapter;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.RepositoryScenario.Scenario;
import com.example.robotarmh25_remote.ui.connect.ConnectFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class autonomousFragment extends Fragment {

    private BluetoothConnection btCon;
    private Context context;
    private View fragmentView;
    private Switch firstScenario,secondScenario;
    private Button enregistrer, supprimer;
    private TextView tasks1Text, tasks2Text;
    private HashMap<String, ArrayList<String>> scenarios;
    public static Scenario scenarioAJouer;
    private ListView liste;
    private DBHandler dbHandler;
    private MyCustomAdapter adapter;
    public static int clé=0;
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

        // Switches, texts, buttons and liste
        liste = fragmentView.findViewById(R.id.list_scenarios);
        enregistrer    = fragmentView.findViewById(R.id.addScenarioButton);
        supprimer    = fragmentView.findViewById(R.id.deleteScenarioButton);
        firstScenario  = fragmentView.findViewById(R.id.scenarioBase1);
        tasks1Text = fragmentView.findViewById(R.id.tasksScenario1);
        secondScenario = fragmentView.findViewById(R.id.scenarioBase2);
        tasks2Text = fragmentView.findViewById(R.id.tasksScenario2);

        //initializing the two basic scenarios
        ArrayList<Scenario.TypeTask> tasks1 = new ArrayList<>(Arrays.asList(Scenario.TypeTask.LIFT, Scenario.TypeTask.LEFT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN,
                Scenario.TypeTask.CLOSE, Scenario.TypeTask.LIFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN));
        Scenario scenarioBase1 = new Scenario(tasks1);
        ArrayList<Scenario.TypeTask> tasks2 =  new ArrayList<>(Arrays.asList(Scenario.TypeTask.LIFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN,
                Scenario.TypeTask.CLOSE, Scenario.TypeTask.LIFT, Scenario.TypeTask.LEFT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN));
        Scenario scenarioBase2 = new Scenario(tasks2);
        //setting the action text
        tasks1Text.setText(scenarioBase1.getTasksToString());
        tasks2Text.setText(scenarioBase2.getTasksToString());

        //displaying scenarios from the database
        scenarios = dbHandler.getData();
        ArrayList<String> s = new ArrayList<String>();
        int n=3;
        for(HashMap.Entry<String, ArrayList<String>> set : scenarios.entrySet()) {
               Scenario newScenario = new Scenario(set.getValue(), 1);
               s.add(n + ", " + set.getKey()+", "+newScenario.getTasksToString());
               n++;
        }
        adapter = new MyCustomAdapter(s,context);
        liste.setAdapter(adapter);

        enregistrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    for (Scenario.TypeTask task : scenarioAJouer.getTasks()) {
                        try {
                            btCon.writeMessage((byte)task.getTaskValue());
                            int termine = 0;
                            while (termine != 1) {
                                termine  = btCon.readMessage();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    scenarioAJouer = null;
                    Toast.makeText(context, "Scénario lancé", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (firstScenario.isChecked() || secondScenario.isChecked()) {
                        Toast.makeText(context, "Impossible de supprimer les scénarios de base", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dbHandler.deleteData(clé);
                        Toast.makeText(context, "Scénario supprimé", Toast.LENGTH_SHORT).show();
                    }
                    scenarioAJouer = null;
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });
        firstScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(scenarioAJouer != null) {
                    if (!firstScenario.isChecked()){
                        scenarioAJouer  = null;
                    }
                    else {
                        Toast.makeText(context, "Vous ne pouvez lancer qu'un scénario à la fois", Toast.LENGTH_SHORT).show();
                        firstScenario.setChecked(false);
                    }
                }
                else{
                        scenarioAJouer = scenarioBase1;
                }
            }
        });

        secondScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(scenarioAJouer != null) {
                    if (!secondScenario.isChecked()){
                        scenarioAJouer  = null;
                    }
                    else {
                        Toast.makeText(context, "Vous ne pouvez lancer qu'un scénario à la fois", Toast.LENGTH_SHORT).show();
                        secondScenario.setChecked(false);
                    }
                }
                else{
                        scenarioAJouer = scenarioBase2;
                }
            }
        });


        return fragmentView;
    }
}