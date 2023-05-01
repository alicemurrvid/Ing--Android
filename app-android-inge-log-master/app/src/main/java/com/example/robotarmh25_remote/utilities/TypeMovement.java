package com.example.robotarmh25_remote.utilities;

public enum TypeMovement {

    ANTI_CLOCKWISE_ROTATION((byte)1, "rotation Antihoraire"),
    CLOCKWISE_ROTATION((byte)3, "rotation Horaire"),
    LIFT((byte)5, "lever bras"),
    LOWER((byte)7, "baisser bras"),
    OPEN((byte)9, "ouverture pince"),
    CLOSE((byte)11, "fermeture pince");


    private String stringValue;
    private final byte taskValue;
    private final String message;

    TypeMovement(byte taskValue, String message) {
        this.taskValue = taskValue;
        this.message = message;
    }

    public byte getTaskValue() {
        return taskValue;
    }
    public String getMessage() {
        return message;
    }
}