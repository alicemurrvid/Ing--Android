package com.example.robotarmh25_remote.ui.remote;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.robotarmh25_remote.utilities.BluetoothConnection;
import com.example.robotarmh25_remote.utilities.DBHandler;
import com.example.robotarmh25_remote.ui.adapters.MyCustomAdapter;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.RepositoryScenario.Scenario;
import com.example.robotarmh25_remote.ui.connect.ConnectFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class autonomousFragment extends Fragment {
    private BluetoothConnection bluetoothConnection;
    private Context context;
    private View fragmentView;
    private Switch firstScenario, secondScenario;
    private Button addScenarioButton, deleteScenarioButton;
    private TextView tasks1Text, tasks2Text;
    private HashMap<String, ArrayList<String>> scenarios;
    public static Scenario scenarioToPlay;
    private ListView scenarioListView;
    private DBHandler dbHandler;
    private MyCustomAdapter adapter;
    public static int key= 0;

    /**
     * Initializes the fragment
     * @param inflater The LayoutInflater object that can be used to inflate views in the fragment
     * @param container The parent view that the fragment's UI should be attached to
     * @param savedInstanceState The saved state of the fragment
     * @return Returns the root view of the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_autonomous, container, false);
        context = this.getActivity();

        // Get the BluetoothConnection object from the ConnectFragment
        bluetoothConnection = ConnectFragment.bluetoothConnection;

        // Initialize the DBHandler object for handling the SQLite database
        dbHandler = new DBHandler(context);

        // Initialize the UI components
        scenarioListView = fragmentView.findViewById(R.id.list_scenarios);
        addScenarioButton   = fragmentView.findViewById(R.id.addScenarioButton);
        deleteScenarioButton   = fragmentView.findViewById(R.id.deleteScenarioButton);
        firstScenario  = fragmentView.findViewById(R.id.scenarioBase1);
        tasks1Text = fragmentView.findViewById(R.id.tasksScenario1);
        secondScenario = fragmentView.findViewById(R.id.scenarioBase2);
        tasks2Text = fragmentView.findViewById(R.id.tasksScenario2);

        // Initialize the two basic scenarios
        ArrayList<Scenario.TypeTask> tasks1 = new ArrayList<>(Arrays.asList(Scenario.TypeTask.LIFT, Scenario.TypeTask.LEFT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN,
                Scenario.TypeTask.CLOSE, Scenario.TypeTask.LIFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN));
        Scenario scenarioBase1 = new Scenario(tasks1);
        ArrayList<Scenario.TypeTask> tasks2 =  new ArrayList<>(Arrays.asList(Scenario.TypeTask.LIFT, Scenario.TypeTask.RIGHT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN,
                Scenario.TypeTask.CLOSE, Scenario.TypeTask.LIFT, Scenario.TypeTask.LEFT, Scenario.TypeTask.LOWER, Scenario.TypeTask.OPEN));
        Scenario scenarioBase2 = new Scenario(tasks2);

        // Set the text for the two basic scenarios
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
        scenarioListView.setAdapter(adapter);

        addScenarioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    for (Scenario.TypeTask task : scenarioToPlay .getTasks()) {
                        try {
                            bluetoothConnection.writeMessage((byte)task.getTaskValue());
                            int termine = 0;
                            while (termine != 1) {
                                termine  = bluetoothConnection.readMessage();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    scenarioToPlay = null;
                    Toast.makeText(context, "Scénario lancé", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });
        deleteScenarioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (firstScenario.isChecked() || secondScenario.isChecked()) {
                        Toast.makeText(context, "Impossible de supprimer les scénarios de base", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dbHandler.deleteData(key);
                        Toast.makeText(context, "Scénario supprimé", Toast.LENGTH_SHORT).show();
                    }
                    scenarioToPlay = null;
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(context, "Problème rencontré: veuillez re-commencer", Toast.LENGTH_SHORT).show();
                    Log.e("Base de données", e.getMessage());
                }
            }
        });
        firstScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(scenarioToPlay != null) {
                    if (!firstScenario.isChecked()){
                        scenarioToPlay   = null;
                    }
                    else {
                        Toast.makeText(context, "Vous ne pouvez lancer qu'un scénario à la fois", Toast.LENGTH_SHORT).show();
                        firstScenario.setChecked(false);
                    }
                }
                else{
                    scenarioToPlay = scenarioBase1;
                }
            }
        });

        secondScenario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(scenarioToPlay != null) {
                    if (!secondScenario.isChecked()){
                        scenarioToPlay  = null;
                    }
                    else {
                        Toast.makeText(context, "Vous ne pouvez lancer qu'un scénario à la fois", Toast.LENGTH_SHORT).show();
                        secondScenario.setChecked(false);
                    }
                }
                else{
                    scenarioToPlay  = scenarioBase2;
                }
            }
        });

        return fragmentView;
    }
}