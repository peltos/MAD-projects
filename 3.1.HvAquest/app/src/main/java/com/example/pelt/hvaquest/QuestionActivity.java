package com.example.pelt.hvaquest;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {


    private Button mConfirm;
    private TextView mTextQuestion;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private int questionNumber;
    private String[] items;
    private String correctAnswer;
    private int selectedId;
    private String answer;

    public static final String questionKey = "questionKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionNumber = 1;

        mConfirm = findViewById(R.id.confirmButton);
        mTextQuestion = findViewById(R.id.textQuestion);
        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioButton1 = findViewById(R.id.radioButton1);
        mRadioButton2 = findViewById(R.id.radioButton2);
        mRadioButton3 = findViewById(R.id.radioButton3);

        populateQuestion(questionNumber);


    }

    public void populateQuestion(final int questionNumber) {

        //   populate question
        String question = "question" + questionNumber;
        int holderint1 = getResources().getIdentifier(question, "string", this.getPackageName()); // You had used "name"
        String questionScreen = getResources().getString(holderint1);

        mTextQuestion.setText(questionScreen);

        // populate answer
        answer = "answer" + questionNumber;
        int holderint = getResources().getIdentifier(answer, "array",
                this.getPackageName()); // You had used "name"
        items = getResources().getStringArray(holderint);
        mRadioButton1.setText(items[1]);
        mRadioButton2.setText(items[2]);
        mRadioButton3.setText(items[3]);
        correctAnswer = items[0];

        mConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                if (selectedId == -1)
                    Snackbar.make(view, getApplicationContext().getString(R.string.choose_answer), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                else {
                    RadioButton currentAnswer = findViewById(selectedId);
                    if (currentAnswer.getText().equals(correctAnswer)) {
                        Intent intent = new Intent(QuestionActivity.this, LocationActivity.class);
                        intent.putExtra(questionKey, questionNumber);
                        startActivityForResult(intent,1234);
                    } else {
                        Snackbar.make(view, getApplicationContext().getString(R.string.try_again), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            //Check if the request code is correct
            if (requestCode == 1234) {
                questionNumber = data.getIntExtra(LocationActivity.clueKey,-1);
                populateQuestion(questionNumber);
                mRadioGroup.clearCheck();
            }
        }
    }
}
