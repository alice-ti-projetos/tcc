package com.example.listaparaocarlos.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.managers.TarefaManager;

public class AdicionarEditarActivity extends AppCompatActivity {

    EditText tituloTxt;
    EditText descTxt;
    Button salveBtn;

    TarefaManager tarefaManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        tituloTxt = findViewById(R.id.editTextTaskTitle);
        descTxt = findViewById(R.id.editTextTaskDescription);
        salveBtn = findViewById(R.id.buttonSaveTask);
        tarefaManager = new TarefaManager();
        //depois vamos adicionar pelo banco o id e se foi concluida.
        int id = 0;
        boolean concluida = false;

        //adiciona tarefa
        salveBtn.setOnClickListener(v -> tarefaManager.adicionarTarefa(id,
                tituloTxt.getText().toString(), descTxt.getText().toString(), concluida));

    }
}
