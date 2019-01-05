package com.example.pelt.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    public static int ADD_KEY = 1234;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private List<Item> mItemsList;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, ADD_KEY);
            }
        });
        mRecyclerView = findViewById(R.id.ItemListView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, LinearLayoutManager.VERTICAL));
        mMainViewModel = new MainViewModel(this);
        initializeItems();
    }

    public void initializeItems() {
        mMainViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> itemsList) {
                mItemsList = itemsList;
                updateUI();
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ItemAdapter(mItemsList, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mItemsList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_KEY) {
            if (resultCode == RESULT_OK) {
                Item item = data.getParcelableExtra(AddActivity.ITEM);
                mMainViewModel.insert(item);
            }
        }
    }

    @Override
    public void ItemOnClick(int i, boolean check) {
        Item item = mItemsList.get(i);
        item.setChecked(check);
        mMainViewModel.update(item);
    }
}
