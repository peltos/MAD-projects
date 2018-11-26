package com.example.pelt.numbertrivia;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecView;
    private FactAdapter mAdapter;
    private List<FactResponse> numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RecView = findViewById(R.id.factView);
        RecView.setLayoutManager(new GridLayoutManager(this, LinearLayoutManager.VERTICAL));
        numberList = new ArrayList<>();
        updateUI();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int n = rand.nextInt(1000) + 1;
                requestData(n);
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new FactAdapter(numberList);
            RecView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
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

    public interface NumbersApiService {

        String BASE_URL = "http://numbersapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        @GET("/{number}/trivia?json")
        Call<FactResponse> getTodaysQuote(@Path("number") int number);

    }

    private void requestData(int n) {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        Call<FactResponse> call = service.getTodaysQuote(n);
        call.enqueue(new Callback<FactResponse>() {

            @Override
            public void onResponse(Call<FactResponse> call, Response<FactResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    FactResponse newNumber = new FactResponse(
                            response.body().text,
                            response.body().number,
                            response.body().found,
                            response.body().type);
                    numberList.add(newNumber);
                    updateUI();
                    RecView.smoothScrollToPosition(mAdapter.getItemCount());
                } else {
                    showToast("Failed to sucessfully connect with the api");
                }
            }

            @Override
            public void onFailure(Call<FactResponse> call, Throwable t) {
                Log.d("error: ", t.toString());
                showToast("Failed to sucessfully connect with the api");
            }

        });

    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}