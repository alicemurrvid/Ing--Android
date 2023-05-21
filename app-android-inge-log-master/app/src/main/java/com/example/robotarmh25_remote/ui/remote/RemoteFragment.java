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
                performMovement(TypeMovement.ANTI_CLOCKWISE_ROTATION, switchAntiClockwiseRotation.isChecked());
            }
        });

        switchClockwiseRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performMovement(TypeMovement.CLOCKWISE_ROTATION, switchClockwiseRotation.isChecked());
            }
        });

        switchLift .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performMovement(TypeMovement.LIFT, switchLift.isChecked());
            }
        });

        switchLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performMovement(TypeMovement.LOWER, switchLower.isChecked());
            }
        });

        switchOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performMovement(TypeMovement.OPEN, switchOpen.isChecked());
            }
        });

        switchClose .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performMovement(TypeMovement.CLOSE, switchClose.isChecked());
            }
        });

        return root;
    }
    /**
     Perform a movement based on the given TypeMovement and switch status.
     @param typeMovement the TypeMovement to perform.
     @param isChecked the status of the switch associated with the movement.
     */
    private void performMovement(TypeMovement typeMovement, boolean isChecked) {
        byte taskValue = isChecked ? typeMovement.getTaskValue() : (byte)(typeMovement.getTaskValue() + 1);
        try {
            bluetoothConnection.writeMessage(taskValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String message = isChecked ? "Démarrage " : "Arrêt ";
        message += typeMovement.getMessage();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}