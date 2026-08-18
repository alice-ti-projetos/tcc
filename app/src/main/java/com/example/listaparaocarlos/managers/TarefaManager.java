package com.example.listaparaocarlos.managers;

import com.example.listaparaocarlos.models.Tarefa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.collections.EmptyList;

public class TarefaManager {

    private static TarefaManager instance;
    List<Tarefa> tarefas;
    private int nextId = 1;

    private TarefaManager(){
        this.tarefas = new ArrayList<Tarefa>();
    }

    public static TarefaManager getInstance(){
        if (instance == null){
            instance = new TarefaManager();
        }
        return instance;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void adicionarTarefa(int id,
                                String titulo,
                                String descricao,
                                boolean concluida){
       Tarefa tarefa = new Tarefa(
                id,
                titulo,
                descricao,
                concluida
        );


        tarefas.add(tarefa);

    }

    public void listarTarefas(){
        for (Tarefa tarefa:tarefas
             ) {
            System.out.print(tarefa.getTitulo());
        }
    }

    public void atualizarTarefa(int id, String titulo, String descricao, boolean concluida){
        for (Tarefa t: tarefas
             ) {
            if (t.getId() == id){
                t.setTitulo(titulo);
                t.setDescricao(descricao);
                t.setConcluida(concluida);
                break;
            }
        }
    }

    public void removerTarefa(int id){
        tarefas.removeIf(t -> t.getId() == id);
    }

    public Tarefa getTarefaById(int id){
        for (Tarefa t: tarefas
             ) {

            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }


    public int adicionarTarefa(String titulo, String descricao, boolean concluida){
        Tarefa tarefa = new Tarefa(nextId, titulo, descricao, concluida);
        tarefas.add(tarefa);
        return nextId++;
    }
}
