package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AddTaskActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "peyton_task_database")
                .allowMainThreadQueries()
                .build();

        Button addTaskButton = AddTaskActivity.this.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(view -> {

            TextView title = findViewById(R.id.add_task_title);
            TextView body = findViewById(R.id.add_task_desc);
            Task task = new Task(title.getText().toString(), body.getText().toString(), TaskState.NEW.ordinal());

            database.taskDao().saveTask(task);

            Context context = getApplicationContext();
            String text = "Task Added!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        });
    }
}