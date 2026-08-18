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

public class AdicionarEditarActivity extends AppCompatActivity {

    EditText tituloTxt;
    EditText descTxt;
    Button salveBtn;

    Intent intent;
    TarefaManager tarefaManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        tituloTxt = findViewById(R.id.editTextTaskTitle);
        descTxt = findViewById(R.id.editTextTaskDescription);
        salveBtn = findViewById(R.id.buttonSaveTask);

        tarefaManager = TarefaManager.getInstance();

        //depois vamos adicionar pelo banco o id e se foi concluida.
        int id = 0;
        boolean concluida = false;

        //adiciona tarefa
       salveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tarefaManager.adicionarTarefa(id,
                       tituloTxt.getText().toString(),
                       descTxt.getText().toString(),
                       concluida);
               intent = new Intent(AdicionarEditarActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       });

    }
}
