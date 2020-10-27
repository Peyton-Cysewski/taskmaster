package com.peytoncysewski.taskmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    long id;

    private String title;
    private String body;
    private Integer state;

    public Task(String title, String body, Integer state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public Integer getState() { return this.state; }
    public void setState(Integer state) { this.state = state; }

    public TaskState getStateEnum() { return TaskState.values()[state]; }
    public void setStateEnum(TaskState state) { this.state = state.ordinal(); }
}

enum TaskState {
    NEW,
    ASSIGNED,
    IN_PROGRESS,
    COMPLETE
}