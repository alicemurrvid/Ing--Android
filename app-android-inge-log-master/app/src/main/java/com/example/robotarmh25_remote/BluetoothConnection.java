package com.example.robotarmh25_remote;

/**
 * From http://stackoverflow.com/questions/4969053/bluetooth-connection-between-android-and-lego-mindstorm-nxt
 * answered Feb 14 '11 at 23:05 by joen
 *
 * Modified to work with 1 EV3 brick and take a user-entered mac address
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;


import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import android.util.Log;
import android.widget.Toast;

public class BluetoothConnection {
    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket_ev3_1;
    private boolean success=false;
    private boolean bluetoothPermission = false;
    private boolean alertReplied=false;

    /**
     Setter method to set the Bluetooth permission.
     @param bluetoothPermission boolean value indicating if Bluetooth permission is granted or not
     */
    public void setBluetoothPermission(boolean bluetoothPermission) {
        this.bluetoothPermission = bluetoothPermission;
    }

    /**
     Setter method to set the alert replied flag.
     @param alertReplied boolean value indicating if alert has been replied or not
     */
    public void setAlertReplied(boolean alertReplied) {
        this.alertReplied = alertReplied;
    }
    /**
     Initializes the Bluetooth adapter and returns if Bluetooth is enabled.
     @return true if Bluetooth is enabled, false otherwise
     */
    public boolean initBluetooth(){
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }

    /**
     Attempts to connect to an EV3 device with the given MAC address.
     @param macAdd the MAC address of the EV3 device
     @return true if the connection is successful, false otherwise
     */
    public  boolean connectToEV3(String macAdd){
        //get the BluetoothDevice of the EV3
        //BluetoothDevice nxt_2 = bluetoothAdapter.getRemoteDevice(nxt2);
        BluetoothDevice ev3Device= bluetoothAdapter.getRemoteDevice(macAdd);
        //try to connect to the nxt
        try {
            socket_ev3_1 = ev3Device.createRfcommSocketToServiceRecord(UUID
                    .fromString(SPP_UUID));
            socket_ev3_1.connect();
            success = true;
        } catch (IOException e) {
            Log.d("Bluetooth","Err: Device not found or cannot connect " + macAdd);
            success=false;
        }
        return success;
    }

    /**
     Sends a message to the connected EV3 brick.
     @param msg a byte representing the message to be sent
     @throws InterruptedException if the thread is interrupted while waiting
     */
    public void writeMessage(byte msg) throws InterruptedException{

        if(socket_ev3_1!=null){
            try {
                OutputStreamWriter outputStreamWriter =new OutputStreamWriter(socket_ev3_1.getOutputStream());
                outputStreamWriter.write(msg);
                outputStreamWriter.flush();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Log.e("ok","peut pas écrire");
        }
    }
    /**
     Reads a message from the specified Bluetooth socket.
     @return The integer value of the read message, or -1 if an error occurred.
     */
    public int readMessage(){
        BluetoothSocket connSock;
        int n=0;
        connSock= socket_ev3_1;

        if(connSock!=null){
            try {
                InputStreamReader in=new InputStreamReader(connSock.getInputStream());
                n=in.read();
                return n;
            } catch (IOException e) {
                e.printStackTrace();
                return n;
            }
        }else{
            //Error
            return n;
        }
    }

}