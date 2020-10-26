package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnInteractWithTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button allTasksButton = MainActivity.this.findViewById(R.id.goto_all_tasks_button);
        allTasksButton.setOnClickListener(view -> {
            Intent goToAllTasks = new Intent(MainActivity.this, AllTasksActivity.class);
            MainActivity.this.startActivity(goToAllTasks);
        });

        Button addTaskButton = MainActivity.this.findViewById(R.id.goto_add_task_button);
        addTaskButton.setOnClickListener(view -> {
            Intent goToAddTask = new Intent(MainActivity.this, AddTaskActivity.class);
            MainActivity.this.startActivity(goToAddTask);
        });

        ImageButton settingsButton = MainActivity.this.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
            MainActivity.this.startActivity(goToSettings);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView usernameLabel = findViewById(R.id.username_label);
        String result = preferences.getString("username", "Your Tasks");
        result = result.equals("") ? "Your Tasks" : (result += "'s Tasks");
        usernameLabel.setText(result);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "This is task 1", TaskState.NEW));
        tasks.add(new Task("Task 2", "This is task 2", TaskState.NEW));
        tasks.add(new Task("Task 3", "This is task 3", TaskState.NEW));
        tasks.add(new Task("Task 4", "This is task 4", TaskState.NEW));
        tasks.add(new Task("Task 5", "This is task 5", TaskState.NEW));
        tasks.add(new Task("Task 6", "This is task 6", TaskState.NEW));
        tasks.add(new Task("Task 7", "This is task 7", TaskState.NEW));
        tasks.add(new Task("Task 8", "This is task 8", TaskState.NEW));
        tasks.add(new Task("Task 9", "This is task 9", TaskState.NEW));
        tasks.add(new Task("Task 10", "This is task 10", TaskState.NEW));

        RecyclerView taskList = findViewById(R.id.home_page_tasks);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(new TaskAdapter(tasks, this));
    }

    @Override
    public void taskListener(Task task) {
        Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
        intent.putExtra("title", task.getTitle());
        intent.putExtra("body", task.getBody());
        intent.putExtra("state", task.getState());
        this.startActivity(intent);
    }
}