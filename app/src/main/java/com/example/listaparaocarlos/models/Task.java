package com.example.listaparaocarlos.models;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id = 0;
    private String title = "";
    private String desc = "";
    private boolean isDone = false;
    private List<String> levelOfPriority = new ArrayList<String>();


    public Task(){

     }

     public Task(int id, String title, String desc, boolean isDone){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.isDone = isDone;
     }


    public Task(int id, String title, String desc, boolean isDone, List<String> levelOfPriority){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.isDone = isDone;
        this.levelOfPriority = levelOfPriority;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public List<String> getLevelOfPriority() {
        return levelOfPriority;
    }

    public void setLevelOfPriority(List<String> levelOfPriority) {
        this.levelOfPriority = levelOfPriority;
    }
}