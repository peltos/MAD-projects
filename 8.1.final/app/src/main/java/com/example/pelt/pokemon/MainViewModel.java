package com.example.pelt.pokemon;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {


    private PokemonRepository mRepository;
    private LiveData<List<Pokemon>> mPokemons;

    public MainViewModel(Context context) {
        mRepository = new PokemonRepository(context);
        mPokemons = mRepository.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return mPokemons;
    }

    public void insert(Pokemon pokemon) {
        mRepository.insert(pokemon);
    }

    public void update(Pokemon pokemon) {
        mRepository.update(pokemon);
    }

    public void delete(Pokemon pokemon) {
        mRepository.delete(pokemon);
    }
}