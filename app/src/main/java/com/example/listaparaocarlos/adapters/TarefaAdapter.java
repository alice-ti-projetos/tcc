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
import com.example.listaparaocarlos.activities.AdicionarEditarActivity;
import com.example.listaparaocarlos.activities.MainActivity;
import com.example.listaparaocarlos.managers.TarefaManager;
import com.example.listaparaocarlos.models.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> tarefas;
    private TarefaManager tarefaManager;
    public TarefaAdapter(List<Tarefa> tarefas, TarefaManager tarefaManager) {
        this.tarefas = tarefas;
        this.tarefaManager = tarefaManager;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);

        holder.checkBoxDone.setOnCheckedChangeListener(null); // evita disparo indevido no reuso da view
        holder.checkBoxDone.setChecked(tarefa.isConcluida());
        holder.textViewTaskTitle.setText(tarefa.getTitulo());
        holder.textViewTaskDesc.setText(tarefa.getDescricao());



        holder.checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tarefaManager.atualizarTarefa(
                    tarefa.getId(),
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    isChecked
            );
            tarefa.setConcluida(isChecked);
        });

        holder.buttonDelete.setOnClickListener(v -> {
            tarefaManager.removerTarefa(tarefa.getId());
            int pos = holder.getAdapterPosition();
            tarefas.remove(pos);
            notifyItemRemoved(pos);
        });

        holder.textViewTaskTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AdicionarEditarActivity.class).putExtra("id", tarefa.getId());
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskTitle;

        TextView textViewTaskDesc;
        CheckBox checkBoxDone;
        ImageButton buttonDelete;



        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTaskTitle);
            textViewTaskDesc = itemView.findViewById(R.id.textViewTaskDesc);
            checkBoxDone = itemView.findViewById(R.id.checkBoxDone);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            
        }
    }

}