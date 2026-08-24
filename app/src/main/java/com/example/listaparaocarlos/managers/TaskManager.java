package com.example.listaparaocarlos.managers;

import android.content.SharedPreferences;

import com.example.listaparaocarlos.models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static TaskManager instance;
    List<Task> tasks;
    private int nextId = 1;

    private final String TASKS_KEY = "tasks";

    private TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public static TaskManager getInstance(){
        if (instance == null){
            instance = new TaskManager();
        }
        return instance;
    }

    public void saveLocally(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tasks);
        editor.putString(TASKS_KEY, json);
        editor.apply();
    }

    public void loadLocally(SharedPreferences sharedPreferences){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TASKS_KEY, "[]");
        Type type = new TypeToken<List<Task>>(){}.getType();
        tasks = gson.fromJson(json, type);
    }

    public List<Task> getTasks() {
        return tasks;
    }



    public void updateTasks(int taskId, String title, String desc, boolean isDone){
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                t.setTitle(title);
                t.setDesc(desc);
                t.setDone(isDone);
                return;
            }
        }
    }

    public void updateTasks(int taskId, String title, String desc, boolean isDone, List<String> levelOfPriority){
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                t.setTitle(title);
                t.setDesc(desc);
                t.setDone(isDone);
                t.setLevelOfPriority(levelOfPriority);
                return;
            }
        }
    }


    public void removeTask(int id){
        tasks.removeIf(t -> t.getId() == id && id != 0);
    }

    public Task getTaskById(int id){
        for (Task t: tasks
             ) {

            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }


    public int addTasks(String title, String desc, boolean isDone){
        Task task = new Task(nextId, title, desc, isDone);
        tasks.add(task);
        return nextId++;
    }


    public int addTasks(String title, String desc, boolean isDone, List<String> levelOfPriority){
        Task task = new Task(nextId, title, desc, isDone, levelOfPriority);
        tasks.add(task);
        return nextId++;
    }

}
