package com.example.listaparaocarlos.models;

public class Task {
    private int id = 0;
    private String title = "";
    private String desc = "";
    private boolean isDone = false;


    public Task(){

     }

     public Task(int id, String title, String desc, boolean isDone){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.isDone = isDone;
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
}