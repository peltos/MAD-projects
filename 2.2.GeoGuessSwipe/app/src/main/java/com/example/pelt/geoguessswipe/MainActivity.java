package com.example.pelt.geoguessswipe;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<GeoObject> mGeoObjects = new ArrayList<>();
    private RecyclerView mGeoRecyclerView;
    private GeoObjectAdapter mAdapter;
    private ConstraintLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.View);

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i], GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i], GeoObject.PRE_DEFINED_GEO_OBJECT_ANSWERS[i]));
        }

        mGeoRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setLayoutManager(mLayoutManager);

        mGeoRecyclerView.setAdapter(mAdapter);


        /* Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
        An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
        and uses callbacks to signal when a user is performing these actions. */

        ItemTouchHelper.SimpleCallback leftSwipe = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Called when a user swipes left on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                final GeoObject geoObject = mGeoObjects.get(position);

                if(geoObject.getmGeoAnswer() ==  true){
                    Snackbar.make(mView, "Correct!", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
                }else{
                    Snackbar.make(mView, "Wrong...", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
                }

                mGeoObjects.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper.SimpleCallback rightSwipe = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Called when a user swipes right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                final GeoObject geoObject = mGeoObjects.get(position);

                if(geoObject.getmGeoAnswer() ==  false){
                    Snackbar.make(mView, "Correct!", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
                }else{
                    Snackbar.make(mView, "Wrong...", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
                }

                mGeoObjects.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelperLeftSwipe = new ItemTouchHelper(leftSwipe);
        itemTouchHelperLeftSwipe.attachToRecyclerView(mGeoRecyclerView);

        ItemTouchHelper itemTouchHelperRightSwipe = new ItemTouchHelper(rightSwipe);
        itemTouchHelperRightSwipe.attachToRecyclerView(mGeoRecyclerView);

    }


}
