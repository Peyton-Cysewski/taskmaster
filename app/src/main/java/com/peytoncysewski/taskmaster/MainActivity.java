package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnInteractWithTaskListener {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Amplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Amplify", "Could not initialize Amplify", error);
        }

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

//        manuallyCreate3Teams();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView usernameLabel = findViewById(R.id.username_label);
        String result = preferences.getString("username", "Your Tasks");
        result = result.equals("") ? "Your Tasks" : (result += "'s Tasks");
        usernameLabel.setText(result);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "peyton_task_database")
                .allowMainThreadQueries()
                .build();

        // Getting info from Rooms
//        ArrayList<Task> tasks = (ArrayList<Task>) database.taskDao().getAllTasks();

        String teamPref = preferences.getString("team", "N/A");


        // Getting info from Amplify DynamoDB
        ArrayList<Task> tasks = new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class, com.amplifyframework.datastore.generated.model.Task.BELONGS_TO.),
                response -> {
                    for(com.amplifyframework.datastore.generated.model.Task currTask : response.getData()) {
                        Task convTask = new Task(currTask.getTitle(), currTask.getBody(), currTask.getState());
                        tasks.add(convTask);
                    }
                    Log.i("Amplify", "Queried Tasks from DynamoDB");
                },
                error -> Log.e("Amplify", "Unable to query Tasks from DynamoDB")
        );



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

    // ==================================================================================================
    // Utility Methods (not Lifecycle methods)

    private void manuallyCreate3Teams() {
        Team redTeam = com.amplifyframework.datastore.generated.model.Team.builder().name("Red Team").build();
        Team greenTeam = com.amplifyframework.datastore.generated.model.Team.builder().name("Green Team").build();
        Team blueTeam = com.amplifyframework.datastore.generated.model.Team.builder().name("Blue Team").build();

        Amplify.API.mutate( // Red Team
                ModelMutation.create(redTeam),
                response -> Log.i("Amplify.createTeam", "Red team successfully created"),
                error -> Log.e("Amplify.createTeam", "Red team creation unsuccessful")
        );
        Amplify.API.mutate( // Green Team
                ModelMutation.create(greenTeam),
                response -> Log.i("Amplify.createTeam", "Green team successfully created"),
                error -> Log.e("Amplify.createTeam", "Green team creation unsuccessful")
        );
        Amplify.API.mutate( // Blue Team
                ModelMutation.create(blueTeam),
                response -> Log.i("Amplify.createTeam", "Blue team successfully created"),
                error -> Log.e("Amplify.createTeam", "Blue team creation unsuccessful")
        );
    }
}