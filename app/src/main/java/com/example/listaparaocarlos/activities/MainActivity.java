package com.example.listaparaocarlos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.adapters.TarefaAdapter;
import com.example.listaparaocarlos.managers.TarefaManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView recyclerViewTasks;
    TextView textViewEmpty;

    Intent intent;
    TarefaManager tarefaManager;
    TarefaAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escreveTarefa();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        boolean isEmpty = tarefaManager.getTarefas().isEmpty();
        textViewEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerViewTasks.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }

    private void escreveTarefa(){

        intent = new Intent(MainActivity.this, AdicionarEditarActivity.class);

        tarefaManager = TarefaManager.getInstance();

        fabAdd = findViewById(R.id.fabAdd);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        textViewEmpty = findViewById(R.id.textViewEmpty);

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TarefaAdapter(tarefaManager.getTarefas(), tarefaManager);
        recyclerViewTasks.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}