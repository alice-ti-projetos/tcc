package com.example.listaparaocarlos.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.managers.TaskManager;
import com.example.listaparaocarlos.models.Task;

public class AddAndEditTasksActivity extends AppCompatActivity {

    EditText tittleTxt;
    EditText descTxt;
    Button saveBtn;

    TaskManager taskManager;
    SharedPreferences sharedPreferences;
    Task task;

    int id = 0;
    boolean isDone = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        sharedPreferences = getSharedPreferences("local_save_before_cloud", MODE_PRIVATE);

        tittleTxt = findViewById(R.id.editTextTaskTitle);
        descTxt = findViewById(R.id.editTextTaskDescription);
        saveBtn = findViewById(R.id.buttonSaveTask);
        taskManager = TaskManager.getInstance();

        // ✅ Load existing task if editing
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            task = taskManager.getTaskById(id);
            if (task != null) {
                tittleTxt.setText(task.getTittle());
                descTxt.setText(task.getDesc());
                isDone = task.isDone();
            }
        }

        saveBtn.setOnClickListener(v -> editOrSave());
    }

    private void editOrSave() {
        if (getIntent().hasExtra("id")) {
            editTask();
        } else {
            saveTask();
        }
    }

    private void saveTask() {
        taskManager.addTasks(
                tittleTxt.getText().toString(),
                descTxt.getText().toString(),
                isDone
        );

        taskManager.saveLocally(sharedPreferences);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void editTask() {
        int id = getIntent().getIntExtra("id", 0);
        if (task != null) {
            taskManager.updateTasks(
                    id,
                    tittleTxt.getText().toString(),
                    descTxt.getText().toString(),
                    isDone
            );

            taskManager.saveLocally(sharedPreferences);
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
