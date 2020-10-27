package com.peytoncysewski.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public ArrayList<Task> tasks;
    public OnInteractWithTaskListener listener;

    public TaskAdapter(ArrayList<Task> tasks, OnInteractWithTaskListener listener){
        this.tasks = tasks;
        this.listener = listener;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);

        TaskViewHolder viewHolder = new TaskViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.taskListener(viewHolder.task);
            }
        });

        return viewHolder;
    }

    public static interface OnInteractWithTaskListener {
        public void taskListener(Task task);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = tasks.get(position);

        String stateString;
        switch (holder.task.getStateEnum()) {
            case NEW:
                stateString = "New";
                break;
            case ASSIGNED:
                stateString = "Assigned";
                break;
            case IN_PROGRESS:
                stateString = "In Progress";
                break;
            case COMPLETE:
                stateString = "Complete";
                break;
            default:
                stateString = "N/A";
                break;
        }

        TextView taskNameTextView = holder.itemView.findViewById(R.id.task_title);
        TextView taskBodyTextView = holder.itemView.findViewById(R.id.task_body);
        TextView taskStateTextView = holder.itemView.findViewById(R.id.task_state);
        taskNameTextView.setText(holder.task.getTitle());
        taskBodyTextView.setText(holder.task.getBody());
        taskStateTextView.setText(stateString);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}