package com.peytoncysewski.taskmaster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "body";
    private static final String ARG_PARAM3 = "state";

    private String mTitle;
    private String mBody;
    private String mState;

    public TaskFragment(){}

    public static TaskFragment newInstance(String title, String body, TaskState state) {
        String stateString;
        switch (state) {
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

        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, body);
        args.putString(ARG_PARAM3, stateString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mBody = getArguments().getString(ARG_PARAM2);
            mState = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }
}
