package com.example.robotarmh25_remote.utilities;

public enum TypeMovement {
    LEFT ("Left",1), RIGHT ("Right",3), LIFT ("Lift",5), LOWER ("Lower",7),
    OPEN ("Open",9), CLOSE ("Close",11);
    private String stringValue;
    private int intValue;
    private TypeMovement(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
    public int getTaskValue(){return intValue;}
}