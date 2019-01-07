package com.example.pelt.pokemon;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class AddPokemonActivity extends AppCompatActivity {

    private Button mAddPokemonButton;
    private EditText mPokemonName;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        mAddPokemonButton = findViewById(R.id.addPokemonButton);
        mPokemonName = findViewById(R.id.editPokemon);

        mMainViewModel = new MainViewModel(getApplicationContext());

        mAddPokemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the user text from the textfield
                String text = mPokemonName.getText().toString();
                if (!(TextUtils.isEmpty(text))) {
                    requestData(text);
                    finish();
                } else {
                    showToast("Please enter some text in the textfield");
                }
            }
        });
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public interface PokemonApiService {
        String BASE_URL = "https://pokeapi.co/api/v2/";
        /**
         * Create a retrofit client.
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * The string in the GET annotation is added to the BASE_URL.
         * It simply represents the designed layout of the URLs of the numbersapi.com website,
         * refer to it in a browser and try. This request will deliver a json stream based on month and
         * day of month. It will be put in a DayQuoteTime object by Retrofit.
         */
        @GET("pokemon/{pokemon}/")
        /**
         * "DayQuoteTime" is the name of the helper class just defined, defining the datamodel, and given as argument.
         * "getTodaysQuote" is the name of the symbol get method. It can be chosen at wish, as long as it is invoked
         * with the same name.
         */
        Call<PokemonAPI> getPokemon(@Path("pokemon") String namePokemon);
    }


    private boolean requestData(String pokemonNameInput) {
        PokemonApiService service = PokemonApiService.retrofit.create(PokemonApiService.class);
        Call<PokemonAPI> call = service.getPokemon(pokemonNameInput);
        call.enqueue(new Callback<PokemonAPI>() {

            @Override
            public void onResponse(Call<PokemonAPI> call, Response<PokemonAPI> response) {
                if (response.code() == 200) {
                    PokemonAPI pokemon = response.body();

                    Pokemon newPokemon = new Pokemon(pokemon.getName(), pokemon.getId(), pokemon.getWeight());
                    mMainViewModel.insert(newPokemon);
                    mPokemonName.setText("");
                } else {
                    showToast("Something went wrong. Please try to enter a valid name");
                }
            }

            @Override
            public void onFailure(Call<PokemonAPI> call, Throwable t) {
                showToast("Connection failure. Check your connection");
                Log.d("error", t.toString());
            }
        });
        return true;
    }
}
