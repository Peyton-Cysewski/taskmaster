package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }
}