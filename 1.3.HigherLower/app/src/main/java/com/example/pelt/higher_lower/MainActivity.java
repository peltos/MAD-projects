package com.example.pelt.higher_lower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int currentDice = 0;
    private int score = 0;
    private int highscore = 0;

    private int[] mImageNames;
    private ImageView mImageDices;

    private Button mButtonHigher;
    private Button mButtonLower;

    private ListView mListResults;
    private ArrayAdapter mAdapter;
    private ArrayList<String> mResults;

    private Random r = new Random();

    private TextView mTextScoreNumb;
    private TextView mTextHiscoreNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageDices = findViewById(R.id.imageDices);
        mListResults = findViewById(R.id.listResults);
        mTextScoreNumb = (TextView)findViewById(R.id.textScoreNumb);
        mTextHiscoreNumb = (TextView)findViewById(R.id.textHiscoreNumb);
        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        mResults = new ArrayList<>();

        int randomNumber = r.nextInt(6);
        mImageDices.setImageResource(mImageNames[randomNumber]);
        currentDice = randomNumber;
        updateUI();

        FloatingActionButton buttonLower = (FloatingActionButton) findViewById(R.id.buttonLower);
        buttonLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNumber = r.nextInt(6);
                mImageDices.setImageResource(mImageNames[randomNumber]);
                if(randomNumber <= currentDice){
                    score++;
                    mTextScoreNumb.setText(String.valueOf(score));
                    if(score > highscore){
                        highscore++;
                        mTextHiscoreNumb.setText(String.valueOf(highscore));
                    }
                    Snackbar.make(view, "Correct!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    score = 0;
                    mTextScoreNumb.setText(String.valueOf(score));
                    Snackbar.make(view, "Wrong...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                currentDice = randomNumber;
                addItem("Throw is " + (randomNumber + 1));
                scrollMyListViewToBottom();
                updateUI();
            }
        });

        FloatingActionButton buttonHigher = (FloatingActionButton) findViewById(R.id.buttonHigher);
        buttonHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNumber = r.nextInt(6);
                mImageDices.setImageResource(mImageNames[randomNumber]);
                if(randomNumber >= currentDice){
                    score++;
                    mTextScoreNumb.setText(String.valueOf(score));
                    if(score > highscore){
                        highscore++;
                        mTextHiscoreNumb.setText(String.valueOf(highscore));
                    }
                    Snackbar.make(view, "Correct!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    score = 0;
                    mTextScoreNumb.setText(String.valueOf(score));
                    Snackbar.make(view, "Wrong...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                currentDice = randomNumber;
                addItem("Throw is " + (randomNumber + 1));
                scrollMyListViewToBottom();
                updateUI();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mResults);
            mListResults.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void addItem(String item) {
        mResults.add(item);
        updateUI();
    }

    private void scrollMyListViewToBottom() {
        mListResults.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mListResults.setSelection(mAdapter.getCount() - 1);
            }
        });
    }
}
