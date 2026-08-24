package com.example.listaparaocarlos.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaparaocarlos.R;
import com.example.listaparaocarlos.activities.AddAndEditTasksActivity;
import com.example.listaparaocarlos.managers.TaskManager;
import com.example.listaparaocarlos.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final TaskManager taskManager;

    public TaskAdapter(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public int getItemCount(){
        List<Task> taskList = taskManager.getTasks();
        return taskList != null ? taskList.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        List<Task> taskList = taskManager.getTasks();

        if (taskList == null || taskList.isEmpty() || position >= taskList.size()) {
            return;
        }

        Task task = taskList.get(position);
        holder.checkBoxDone.setOnCheckedChangeListener(null);
        holder.checkBoxDone.setChecked(task.isDone());
        holder.textViewTaskTitle.setText(task.getTitle());
        holder.textViewTaskDesc.setText(task.getDesc());

        holder.checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            taskManager.updateTasks(
                    task.getId(),
                    task.getTitle(),
                    task.getDesc(),
                    isChecked
            );
            task.setDone(isChecked);
            taskManager.saveLocally(holder.itemView.getContext()
                    .getSharedPreferences("local_save_before_cloud", 0));
        });

        holder.buttonDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos >= 0 && pos < taskList.size()) {
                taskManager.removeTask(task.getId());
                notifyItemRemoved(pos);
            }
        });

        holder.textViewTaskTitle.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddAndEditTasksActivity.class)
                    .putExtra("id", task.getId());
            v.getContext().startActivity(intent);
        });
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskTitle;
        TextView textViewTaskDesc;
        CheckBox checkBoxDone;
        ImageButton buttonDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTaskTitle);
            textViewTaskDesc = itemView.findViewById(R.id.textViewTaskDesc);
            checkBoxDone = itemView.findViewById(R.id.checkBoxDone);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }


}
