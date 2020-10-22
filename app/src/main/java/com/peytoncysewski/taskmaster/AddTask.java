package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

//        trying to add a Snackbar pop up message
//        Button addTaskButton = AddTask.this.findViewById(R.id.add_task_button);
//        addTaskButton.setOnClickListener(view -> {
//            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), "Task Added", BaseTransientBottomBar.LENGTH_SHORT);
//            snackbar.show();
//        });
    }
}