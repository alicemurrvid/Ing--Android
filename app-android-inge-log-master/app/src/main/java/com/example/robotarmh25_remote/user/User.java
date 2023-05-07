package com.example.robotarmh25_remote.user;


// use the singleton pattern

/**
 * Creation the user instance
 */
public class User {
    private static User userInstance = null;

    public boolean isConnected = false;
    public String username  = "";

    protected User(){}

    public static synchronized User getInstance(){
        if(null == userInstance){
            userInstance = new User();
        }
        return userInstance;
    }

    /**
     * set the status of user's connexion (connect or disconnect)
     * @param connected
     */
    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * set the username of the current user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
