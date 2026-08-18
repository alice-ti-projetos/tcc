package com.example.listaparaocarlos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.managers.TarefaManager;
import com.example.listaparaocarlos.models.Tarefa;

public class AdicionarEditarActivity extends AppCompatActivity {

    EditText tituloTxt;
    EditText descTxt;
    Button salveBtn;

    Intent intent;
    TarefaManager tarefaManager;


    //depois vamos adicionar pelo banco o id e se foi concluida.
    int id = 0;
    boolean concluida = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        tituloTxt = findViewById(R.id.editTextTaskTitle);
        descTxt = findViewById(R.id.editTextTaskDescription);
        salveBtn = findViewById(R.id.buttonSaveTask);

        tarefaManager = TarefaManager.getInstance();

        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            Tarefa tarefa = tarefaManager.getTarefaById(id);
            if (tarefa != null) {
                concluida = tarefa.isConcluida();
                tituloTxt.setText(tarefa.getTitulo());
                descTxt.setText(tarefa.getDescricao());
            }
        }

        salveBtn.setOnClickListener(v -> editarOuSalvar());
    }

    private void editarOuSalvar(){
        if (getIntent().hasExtra("id")) {
            editarTarefa();
        }
        else{
            salvarTarefa();
        }

    }

    private void editarTarefa(){
        tarefaManager.atualizarTarefa(
                id,
                tituloTxt.getText().toString(),
                descTxt.getText().toString(),
                concluida
        );
        intent = new Intent(AdicionarEditarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    private void salvarTarefa(){
        tarefaManager.adicionarTarefa(
                tituloTxt.getText().toString(),
                descTxt.getText().toString(),
                concluida);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
