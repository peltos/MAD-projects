package com.example.pelt.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    public EditText titleField;
    public EditText descriptionField;
    public Button addButton;
    public static String ITEM = "item";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        titleField = findViewById(R.id.ItemTitle);
        descriptionField = findViewById(R.id.ItemDesc);
        addButton = findViewById(R.id.buttonAdd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String title = titleField.getText().toString();
                    String description = descriptionField.getText().toString();
                    Item item = new Item(title, description);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(ITEM, item);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

//                }
            }
        });

    }
}
