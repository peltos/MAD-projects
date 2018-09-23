package com.example.pelt.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener {

    //Local variables

    private static List<Portal> mPortals;
    private static PortalAdapter mAdapter;
    private static RecyclerView mRecyclerView;

    public static List<Portal> getmPortals() {
        return mPortals;
    }

    public void setmPortals(List<Portal> mPortals) {
        this.mPortals = mPortals;
    }

    public static PortalAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(PortalAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public static RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    //Constants used when calling the update activity
    public static final String EXTRA_PORTAL = "Portal";
    public static final int REQUESTCODE = 1234;
    private static int mModifyPosition;

    public static int getmModifyPosition() {
        return mModifyPosition;
    }

    public static void setmModifyPosition(int mModifyPosition) {
        MainActivity.mModifyPosition = mModifyPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the local variables
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mPortals = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter);

        // Create a grid where items can position horizontally, its now set on 2 columns
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        Portal newPortal = new Portal("http://google.com", "Google");
        mPortals.add(newPortal);

        newPortal = new Portal("http://vlo.informatica.hva.nl/", "Vlo HvA");
        mPortals.add(newPortal);

        updateUI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPortalActivity.class);
                startActivity(intent);
            }
        });


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = (viewHolder.getAdapterPosition());
                mPortals.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
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

    private void updateUI() {

        if (mAdapter == null) {
            mAdapter = new PortalAdapter( mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, PortalActivity.class);
        mModifyPosition = i;
        intent.putExtra(EXTRA_PORTAL, mPortals.get(i));
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Portal updatedPortal = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
                // New timestamp: timestamp of update
                mPortals.set(mModifyPosition, updatedPortal);
                updateUI();
            }
        }
    }
}
