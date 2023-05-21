package com.example.robotarmh25_remote;

import static org.junit.Assert.assertEquals;

import com.example.robotarmh25_remote.RepositoryScenario.Scenario;
import com.example.robotarmh25_remote.utilities.TypeMovement;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ScenarioTest {

    /**
     * checks that the getTasks() method returns the tasks correctly.
     */
    @Test
    public void testGetTasks() {
        ArrayList<TypeMovement> tasks = new ArrayList<>(Arrays.asList(TypeMovement.ANTI_CLOCKWISE_ROTATION, TypeMovement.CLOCKWISE_ROTATION));
        Scenario scenario = new Scenario(tasks);
        ArrayList<TypeMovement> result = scenario.getTasks();
        assertEquals(tasks, result);
    }

    /**
     *  tests adding a single task to a scenario and checks if it is added correctly.
     */
    @Test
    public void testAddTask() {
        Scenario scenario = new Scenario();
        scenario.addTask(TypeMovement.LIFT);
        assertEquals(1, scenario.nbTasks());
        assertEquals(TypeMovement.LIFT, scenario.getTask(0));
    }

    /**
     *
     * checks the addition of a task list to a scenario and confirms if they are all added correctly.
     */
    @Test
    public void testAddListTask() {
        Scenario scenario = new Scenario();
        ArrayList<TypeMovement> tasks = new ArrayList<>(Arrays.asList(TypeMovement.OPEN, TypeMovement.CLOSE));
        scenario.addListTask(tasks);
        assertEquals(2, scenario.nbTasks());
        assertEquals(TypeMovement.OPEN, scenario.getTask(0));
        assertEquals(TypeMovement.CLOSE, scenario.getTask(1));
    }

    /**
     * tests the number of tasks in a scenario after adding multiple tasks.
     */
    @Test
    public void testNbTasks() {
        Scenario scenario = new Scenario();
        scenario.addTask(TypeMovement.ANTI_CLOCKWISE_ROTATION);
        scenario.addTask(TypeMovement.CLOCKWISE_ROTATION);
        assertEquals(2, scenario.nbTasks());
    }

    /**
     * checks if the getTasksToString method returns the string representation of tasks correctly.
     */
    @Test
    public void testGetTasksToString() {
        ArrayList<TypeMovement> tasks = new ArrayList<>(Arrays.asList(TypeMovement.ANTI_CLOCKWISE_ROTATION, TypeMovement.CLOCKWISE_ROTATION));
        Scenario scenario = new Scenario(tasks);
        String result = scenario.getTasksToString();
        assertEquals("rotation Antihoraire, rotation Horaire", result);
    }


}