package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();

        TextView taskTitle = findViewById(R.id.detail_name);
        TextView taskBody = findViewById(R.id.detail_body);
        TextView taskState = findViewById(R.id.detail_state);

        taskTitle.setText(intent.getExtras().getString("title"));
        taskBody.setText(intent.getExtras().getString("body"));
        taskState.setText(intent.getExtras().getString("state"));
    }
}