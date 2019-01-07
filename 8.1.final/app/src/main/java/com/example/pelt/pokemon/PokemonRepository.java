package com.example.pelt.pokemon;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonRepository {

    private AppDatabase mAppDatabase;
    private PokemonDao mPokemonDao;
    private LiveData<List<Pokemon>> mPokemons;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public PokemonRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mPokemonDao = mAppDatabase.pokemonDao();
        mPokemons = mPokemonDao.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return mPokemons;
    }

    public void insert(final Pokemon pokemon) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(mPokemons.toString());
                mPokemonDao.insertPokemons(pokemon);
            }
        });
    }

    public void update(final Pokemon pokemon) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPokemonDao.updatePokemons(pokemon);
            }
        });
    }

    public void delete(final Pokemon pokemon) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPokemonDao.deletePokemons(pokemon);
            }
        });
    }
}
