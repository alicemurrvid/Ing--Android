package com.example.robotarmh25_remote.ui.remote;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.robotarmh25_remote.utilities.BluetoothConnection;
import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.ui.connect.ConnectFragment;
import com.example.robotarmh25_remote.utilities.TypeMovement;

public class RemoteFragment extends Fragment {

    private RemoteViewModel remoteViewModel;
    private BluetoothConnection bluetoothConnection;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        remoteViewModel = new ViewModelProvider(this).get(RemoteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_remote, container, false);
        context = this.getActivity();
        // Get the BluetoothConnection instance created in the ConnectFragment.
        bluetoothConnection= ConnectFragment.bluetoothConnection;

        // Initialize Switch UI components.
        final Switch switchAntiClockwiseRotation = (Switch) root.findViewById(R.id.afficherRotationAntiHoraire);
        final Switch switchClockwiseRotation = (Switch) root.findViewById(R.id.afficherRotationHoraire);
        final Switch switchLift  = (Switch) root.findViewById(R.id.afficherLever);
        final Switch  switchLower = (Switch) root.findViewById(R.id.afficherBaisser);
        final Switch switchOpen = (Switch) root.findViewById(R.id.afficherOuvrir);
        final Switch switchClose  = (Switch) root.findViewById(R.id.afficherFermer);

        switchAntiClockwiseRotation .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchAntiClockwiseRotation .isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte) TypeMovement.LEFT.getTaskValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage rotation Antihoraire", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt rotation Antihoraire", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchClockwiseRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchClockwiseRotation.isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte) 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage rotation Horaire", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt rotation Horaire", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchLift .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchLift .isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte)5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage lever bras", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt lever bras", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( switchLower.isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte)7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage baisser bras", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt baisser bras", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchOpen.isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte)9);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage ouverture pince", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt ouverture pince", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchClose .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchClose .isChecked()) {
                    try {
                        bluetoothConnection.writeMessage((byte)11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Démarrage fermeture pince", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bluetoothConnection.writeMessage((byte) 12);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Arrêt fermeture pince", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}