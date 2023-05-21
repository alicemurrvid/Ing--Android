package com.example.robotarmh25_remote.RepositoryScenario;

import android.util.Log;


import com.example.robotarmh25_remote.utilities.TypeMovement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scenario {

    private ArrayList<TypeMovement> tasks = new ArrayList<TypeMovement>();

    public Scenario(){}

    public Scenario(ArrayList<TypeMovement> tasks) {
        this.tasks = tasks;
    }

    public Scenario(ArrayList<String> stringTasks, int i) {
        ArrayList<TypeMovement> task = new ArrayList<TypeMovement>();
        for (String t: stringTasks) {
            if (!t.equals("NULL"))
                task.add(TypeMovement.valueOf(t.toUpperCase()));
        }
        this.tasks = task;
    }

    public Scenario(String stringTasks) {
        ArrayList<TypeMovement> task = new ArrayList<TypeMovement>();
        ArrayList<String> scenario = new ArrayList<String>(Arrays.asList(stringTasks.split(", ")));
        this.tasks = task;
    }

    public ArrayList<TypeMovement> getTasks(){return tasks;}
    public void setTasks(ArrayList<TypeMovement> tasks) {
        this.tasks = tasks;
    }
    public TypeMovement getTask(int index) {
        return tasks.get(index);
    }
    public void addTask(TypeMovement task){
        try {
            tasks.add(task);
        } catch (Exception e){
            Log.e("Bluetooth", e.getMessage());
        }
    }
    public void addListTask(List<TypeMovement> tasks){
        try {
            this.tasks.addAll(tasks);
        } catch (Exception e){
            Log.e("Bluetooth", e.getMessage());
        }
    }
    public int nbTasks() {
        return tasks.size();
    }
    public String getTasksToString() {
        String result = "";
        for (TypeMovement task : tasks) {
            result += task.getMessage()+", ";
        }
        return result.substring(0, result.length() - 2);
    }
}
