package com.example.listaparaocarlos.models;

public class Task {
    private int id = 0;
    private String tittle = "";
    private String desc = "";
    private boolean isDone = false;


    public Task(){

     }

     public Task(int id, String tittle, String desc, boolean isDone){
        this.id = id;
        this.tittle = tittle;
        this.desc = desc;
        this.isDone = isDone;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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