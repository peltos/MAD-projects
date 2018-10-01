package com.example.pelt.gamebacklog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class UpdateGameActivity extends AppCompatActivity {
    private EditText mEditTitle;
    private EditText mEditPlatform;
    private EditText mEditDate;
    private Spinner mEditStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Init local variables
        mEditTitle = findViewById(R.id.editTitle);
        mEditPlatform = findViewById(R.id.editPlatform);
        mEditDate = findViewById(R.id.editDate);
        mEditStatus = findViewById(R.id.editSpinner);

        //Obtain the parameters provided by MainActivity
        final Game gameUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_GAME);
        mEditTitle.setText(gameUpdate.getGameText());
        mEditPlatform.setText(gameUpdate.getGamePlatform());
        mEditDate.setText(gameUpdate.getGameDate());


        FloatingActionButton fab_update = (FloatingActionButton) findViewById(R.id.fab_update);
        fab_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the user text from the textfield
                String title = mEditTitle.getText().toString();
                String platform = mEditPlatform.getText().toString();
                String date = mEditDate.getText().toString();
                String spinner = mEditStatus.getSelectedItem().toString();
                Game newGame = new Game(title, platform, date, spinner);

                //(gameUpdate.setmGameText(updatedGameText)));

                if (!TextUtils.isEmpty(title)) {

                    gameUpdate.setGameText(title);
                    gameUpdate.setGamePlatform(platform);
                    gameUpdate.setGameDate(date);
                    gameUpdate.setGameStatus(spinner);

                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_GAME, (Parcelable) gameUpdate);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
                }
            }
        });
    }
}
