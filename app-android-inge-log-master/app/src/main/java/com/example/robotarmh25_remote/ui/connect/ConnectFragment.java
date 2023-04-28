package com.example.robotarmh25_remote.ui.connect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.robotarmh25_remote.BluetoothConnection;
import com.example.robotarmh25_remote.R;

public class ConnectFragment extends Fragment {

    public static BluetoothConnection btCon;
    private ConnectViewModel connectViewModel;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        connectViewModel = new ViewModelProvider(this).get(ConnectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_connect, container, false);

        context = this.getActivity();

        final SharedPreferences sp = context.getSharedPreferences(getString(R.string.MyPrefs), Context.MODE_PRIVATE);

        final Button button = (Button) root.findViewById(R.id.confirmConnectButton);
        final EditText macAddressText = (EditText) root.findViewById(R.id.textMacAddress);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!macAddressValid(macAddressText.getText().toString())) {
                    macAddressText.setError("Format invalide");
                } else {
                    SharedPreferences.Editor speditor = sp.edit();
                    speditor.putString(getString(R.string.EV3KEY), macAddressText.getText().toString());
                    speditor.commit();

                    Toast.makeText(context, "Adresse MAC "+macAddressText.getText().toString()+" enregistrée", Toast.LENGTH_SHORT).show();
                    btCon = new BluetoothConnection();
                    SharedPreferences sp = context.getSharedPreferences(getString(R.string.MyPrefs), Context.MODE_PRIVATE);
                    if(!btCon.initBluetooth()){
                        Toast.makeText(context, "Veuillez activer le bluetooth de votre téléphone", Toast.LENGTH_SHORT).show();
                    }

                    if(!btCon.connectToEV3(sp.getString(getString(R.string.EV3KEY), ""))){
                        Toast.makeText(context, "Veuillez vous connecter à votre EV3", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }

    // Validate the MAC address format
    private boolean macAddressValid(String macAddress) {
        String macPattern = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
        return macAddress.length() == 17 && macAddress.matches(macPattern);
    }
}