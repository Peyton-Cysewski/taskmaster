package com.peytoncysewski.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

public class UserSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferenceEditor = preferences.edit();

        EditText username = findViewById(R.id.edit_username);
        username.setText(preferences.getString("username",""));

        findViewById(R.id.save_settings).setOnClickListener(view -> {
            preferenceEditor.putString("username", username.getText().toString());
            preferenceEditor.apply();

            Context context = getApplicationContext();
            String text = "Username Updated!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        });
    }
}