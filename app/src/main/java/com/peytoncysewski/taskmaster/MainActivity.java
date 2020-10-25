package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

        ImageButton settingsButton = MainActivity.this.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
            MainActivity.this.startActivity(goToSettings);
        });


        // Hardcoded tasks on the home page
        TextView task1 = findViewById(R.id.task_1);
        task1.setOnClickListener(view -> {
            Intent goToTaskDetails = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToTaskDetails.putExtra("taskName", task1.getText());
            MainActivity.this.startActivity(goToTaskDetails);
        });
        TextView task2 = findViewById(R.id.task_2);
        task2.setOnClickListener(view -> {
            Intent goToTaskDetails = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToTaskDetails.putExtra("taskName", task2.getText());
            MainActivity.this.startActivity(goToTaskDetails);
        });
        TextView task3 = findViewById(R.id.task_3);
        task3.setOnClickListener(view -> {
            Intent goToTaskDetails = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToTaskDetails.putExtra("taskName", task3.getText());
            MainActivity.this.startActivity(goToTaskDetails);
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
    }
}