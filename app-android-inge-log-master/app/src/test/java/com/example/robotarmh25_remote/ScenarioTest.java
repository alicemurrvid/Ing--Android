package com.example.robotarmh25_remote;

import static org.junit.Assert.assertEquals;

import com.example.robotarmh25_remote.RepositoryScenario.Scenario;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ScenarioTest {

    /**
     * checks that the getTasks() method returns the tasks correctly.
     */
    @Test
    public void testGetTasks() {
        ArrayList<Scenario.TypeTask> tasks = new ArrayList<>(Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT));
        Scenario scenario = new Scenario(tasks);
        ArrayList<Scenario.TypeTask> result = scenario.getTasks();
        assertEquals(tasks, result);
    }

    /**
     *  tests adding a single task to a scenario and checks if it is added correctly.
     */
    @Test
    public void testAddTask() {
        Scenario scenario = new Scenario();
        scenario.addTask(Scenario.TypeTask.LIFT);
        assertEquals(1, scenario.nbTasks());
        assertEquals(Scenario.TypeTask.LIFT, scenario.getTask(0));
    }

    /**
     *
     * checks the addition of a task list to a scenario and confirms if they are all added correctly.
     */
    @Test
    public void testAddListTask() {
        Scenario scenario = new Scenario();
        ArrayList<Scenario.TypeTask> tasks = new ArrayList<>(Arrays.asList(Scenario.TypeTask.OPEN, Scenario.TypeTask.CLOSE));
        scenario.addListTask(tasks);
        assertEquals(2, scenario.nbTasks());
        assertEquals(Scenario.TypeTask.OPEN, scenario.getTask(0));
        assertEquals(Scenario.TypeTask.CLOSE, scenario.getTask(1));
    }

    /**
     * tests the number of tasks in a scenario after adding multiple tasks.
     */
    @Test
    public void testNbTasks() {
        Scenario scenario = new Scenario();
        scenario.addTask(Scenario.TypeTask.LEFT);
        scenario.addTask(Scenario.TypeTask.RIGHT);
        assertEquals(2, scenario.nbTasks());
    }

    /**
     * checks if the getTasksToString method returns the string representation of tasks correctly.
     */
    @Test
    public void testGetTasksToString() {
        ArrayList<Scenario.TypeTask> tasks = new ArrayList<>(Arrays.asList(Scenario.TypeTask.LEFT, Scenario.TypeTask.RIGHT));
        Scenario scenario = new Scenario(tasks);
        String result = scenario.getTasksToString();
        assertEquals("Left, Right", result);
    }


}