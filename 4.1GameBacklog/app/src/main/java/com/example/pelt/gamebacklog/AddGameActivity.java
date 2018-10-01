package com.example.pelt.gamebacklog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class AddGameActivity extends MainActivity {

    private EditText mAddTitle;
    private EditText mAddPlatform;
    private EditText mAddDate;
    private Spinner mAddStatus;

    private List<Game> mGames;
    private GameAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        mAddTitle = findViewById(R.id.addTitle);
        mAddPlatform = findViewById(R.id.addPlatform);
        mAddDate = findViewById(R.id.addDate);
        mAddStatus = findViewById(R.id.addSpinner);

        mGames = MainActivity.getmGames();
        mAdapter = MainActivity.getmAdapter();

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the user text from the textfield
                String title = mAddTitle.getText().toString();
                String platform = mAddPlatform.getText().toString();
                String date = mAddDate.getText().toString();
                String spinner = mAddStatus.getSelectedItem().toString();
                Game newGame = new Game(title, platform, date, spinner);

                //Check if some text has been added
                if (!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(platform)) && !(TextUtils.isEmpty(date)) && !(TextUtils.isEmpty(spinner))) {
                    //Add the text to the list (datamodel)
                    new GameAsyncTask(TASK_INSERT_GAME).execute(newGame);

                    //Tell the adapter that the data set has been modified: the screen will be refreshed.
                    mAdapter.notifyDataSetChanged();

                    //Initialize the EditText for the next item
                    mAddTitle.setText("");
                    mAddPlatform.setText("");
                    mAddDate.setText("");
                    finish();
                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}
