package com.example.pelt.picturequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int currentImageIndex = 0;
    private int[] mImageNames;
    private ImageView mImageView;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheckButton;
    private RadioGroup mGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.quizPicture);
        mGroup = findViewById(R.id.quizRadioGroup);
        mNextButton = findViewById(R.id.quizButtonNext);
        mPrevButton = findViewById(R.id.quizButtonPrev);
        mCheckButton = findViewById(R.id.quizButtonCheck);
        mImageNames = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3};

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex++;
                if (currentImageIndex >= mImageNames.length) {
                    currentImageIndex = 0;
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex--;
                if (currentImageIndex < 0) {
                    currentImageIndex = (mImageNames.length - 1);
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
            }
        });

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = mGroup.getCheckedRadioButtonId();
                View radioButton = mGroup.findViewById(radioButtonID);
                int answerIndex = mGroup.indexOfChild(radioButton);

                if (currentImageIndex == answerIndex) {
                    Toast.makeText(getApplicationContext(),"Great!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Wrong...", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
