package com.example.pelt.pokemon;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM Pokemon")

    //public List<Pokemon> getAllPokemons();
    public LiveData<List<Pokemon>> getAllPokemons();

    @Insert
    public void insertPokemons(Pokemon pokemons);


    @Delete
    public void deletePokemons(Pokemon pokemons);

    @Update
    public void updatePokemons(Pokemon pokemons);


}