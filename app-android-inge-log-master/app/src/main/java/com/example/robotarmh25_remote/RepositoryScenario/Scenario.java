package com.example.robotarmh25_remote.RepositoryScenario;

import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scenario {

    private ArrayList<TypeTask> tasks = new ArrayList<TypeTask>();

    public enum TypeTask {
        LEFT ("Left",1), RIGHT ("Right",3), LIFT ("Lift",5), LOWER ("Lower",7),
        OPEN ("Open",9), CLOSE ("Close",11);
        private String stringValue;
        private int intValue;
        private TypeTask(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
        public int getTaskValue(){return intValue;}
    }
    public Scenario(){}

    public Scenario(ArrayList<TypeTask> tasks) {
        this.tasks = tasks;
    }

    public Scenario(ArrayList<String> stringTasks, int i) {
        ArrayList<TypeTask> task = new ArrayList<TypeTask>();
        for (String t: stringTasks) {
            if (!t.equals("NULL"))
                task.add(TypeTask.valueOf(t.toUpperCase()));
        }
        this.tasks = task;
    }

    public Scenario(String stringTasks) {
        ArrayList<TypeTask> task = new ArrayList<TypeTask>();
        ArrayList<String> scenario = new ArrayList<String>(Arrays.asList(stringTasks.split(", ")));
        this.tasks = task;
    }

    public ArrayList<TypeTask> getTasks(){return tasks;}
    public void setTasks(ArrayList<TypeTask> tasks) {
        this.tasks = tasks;
    }
    public TypeTask getTask(int index) {
        return tasks.get(index);
    }
    public void addTask(TypeTask task){
        try {
            tasks.add(task);
        } catch (Exception e){
            Log.e("Bluetooth", e.getMessage());
        }
    }
    public void addListTask(List<TypeTask> tasks){
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
        for (TypeTask task : tasks) {
            result += task.toString()+", ";
        }
        return result.substring(0, result.length() - 2);
    }
}
