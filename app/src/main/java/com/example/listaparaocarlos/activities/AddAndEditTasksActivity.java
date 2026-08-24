package com.example.listaparaocarlos.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.managers.TaskManager;
import com.example.listaparaocarlos.models.Task;

import java.util.ArrayList;
import java.util.List;

public class AddAndEditTasksActivity extends AppCompatActivity {

    EditText titleTxt;
    EditText descTxt;
    Button saveBtn;

    TaskManager taskManager;
    SharedPreferences sharedPreferences;
    Task task;

    ArrayAdapter<String> adapter;

    Spinner spinner;

    int id = 0;
    boolean isDone = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        sharedPreferences = getSharedPreferences("local_save_before_cloud", MODE_PRIVATE);

        titleTxt = findViewById(R.id.editTextTaskTitle);
        descTxt = findViewById(R.id.editTextTaskDescription);
        saveBtn = findViewById(R.id.buttonSaveTask);
        spinner = findViewById(R.id.LevelOfPrioritySpn);
        taskManager = TaskManager.getInstance();
        adapter = new ArrayAdapter<>(this, R.layout.add_edit_activity, task.getLevelOfPriority());
        adapter.setDropDownViewResource(R.layout.add_edit_activity);
        spinner.setAdapter(adapter);

        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            task = taskManager.getTaskById(id);
            if (task != null) {
                titleTxt.setText(task.getTitle());
                descTxt.setText(task.getDesc());
                isDone = task.isDone();
            }
        }

        saveBtn.setOnClickListener(v -> editOrSave());
    }

    private void editOrSave() {
        String selectedPriority = spinner.getSelectedItem().toString();
        if (getIntent().hasExtra("id")) {
            editTask(selectedPriority);
        } else {
            saveTask(selectedPriority);
        }
    }

    private void saveTask(String levelOfPriority) {
        if (spinner.isActivated()) {
            List<String> priorityList = new ArrayList<>();
            priorityList.add(levelOfPriority);
            taskManager.addTasks(
                    titleTxt.getText().toString(),
                    descTxt.getText().toString(),
                    isDone,
                    priorityList
            );
        }
        taskManager.addTasks(
                titleTxt.getText().toString(),
                descTxt.getText().toString(),
                isDone
        );

        taskManager.saveLocally(sharedPreferences);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void editTask(String levelOfPriority) {
        int id = getIntent().getIntExtra("id", 0);
        if (task != null) {
            if (spinner.isActivated()){
                List<String> priorityList = new ArrayList<>();
                priorityList.add(levelOfPriority);
                taskManager.updateTasks(
                        id,
                        titleTxt.getText().toString(),
                        descTxt.getText().toString(),
                        isDone,
                        priorityList
                );
            }
            taskManager.updateTasks(
                    id,
                    titleTxt.getText().toString(),
                    descTxt.getText().toString(),
                    isDone
            );

            taskManager.saveLocally(sharedPreferences);
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
