package com.example.listaparaocarlos.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.adapters.TaskAdapter;
import com.example.listaparaocarlos.managers.TaskManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView recyclerViewTasks;
    TextView textViewEmpty;

    Intent intent;
    TaskManager taskManager;
    TaskAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVars();
        fabAdd.setOnClickListener(v -> startActivity(intent));
    }

    private void setUpVars(){

        intent = new Intent(MainActivity.this, AddAndEditTasksActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences("local_save_before_cloud", MODE_PRIVATE);

        taskManager = TaskManager.getInstance();
        taskManager.loadLocally(sharedPreferences);
        fabAdd = findViewById(R.id.fabAdd);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(taskManager);
        recyclerViewTasks.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyAll();
        boolean isEmpty = taskManager.getTasks().isEmpty();
        textViewEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerViewTasks.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
