package com.example.pelt.studentportal;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddPortalActivity extends AppCompatActivity {

    private Button mAddPortalButton;
    private EditText mEditUrl;
    private EditText mEditTitle;

    private List<Portal> mPortals;
    private PortalAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        mAddPortalButton = findViewById(R.id.addPortalButton);
        mEditUrl = findViewById(R.id.editUrl);
        mEditTitle = findViewById(R.id.editTitle);

        mPortals = MainActivity.getmPortals();
        mAdapter = MainActivity.getmAdapter();



        mAddPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the user text from the textfield
                String url = mEditUrl.getText().toString();
                String title = mEditTitle.getText().toString();
                Portal newPortal = new Portal(url, title);

                //Check if some text has been added
                if (!(TextUtils.isEmpty(url)) || !(TextUtils.isEmpty(title))) {
                    //Add the text to the list (datamodel)
                    mPortals.add(newPortal);

                    //Tell the adapter that the data set has been modified: the screen will be refreshed.
                    mAdapter.notifyDataSetChanged();

                    //Initialize the EditText for the next item
                    mEditUrl.setText("http://");
                    mEditTitle.setText("");
                    finish();
                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}
