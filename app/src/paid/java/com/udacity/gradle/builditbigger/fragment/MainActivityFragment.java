package com.udacity.gradle.builditbigger.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alvaro.jokedisplay.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, EndpointsAsyncTask.TaskCallback{

    public MainActivityFragment() {
    }

    private ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        spinner = (ProgressBar) root.findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        Button btnTellJoke = (Button) root.findViewById(R.id.tell_joke_btn);
        btnTellJoke.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tell_joke_btn:
                spinner.setVisibility(View.VISIBLE);
                tellJoke(v);
                break;

        }
    }

    public void tellJoke(View view){
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void onTaskCompleted(String joke) {
        spinner.setVisibility(View.GONE);
        Intent displayIntent = new Intent(getActivity(),JokeDisplayActivity.class);
        displayIntent.putExtra(Intent.EXTRA_TEXT, joke);
        startActivity(displayIntent);
    }
}
