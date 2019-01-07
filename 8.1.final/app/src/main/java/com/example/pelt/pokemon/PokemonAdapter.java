package com.example.pelt.pokemon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> mPokemons;

    public PokemonAdapter(List<Pokemon> pokemons ) {
        mPokemons = pokemons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance
        PokemonAdapter.ViewHolder viewHolder = new PokemonAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon pokemon = mPokemons.get(position);
        holder.textView.setText(pokemon.toString());
    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    public interface PokemonClickListener {
        void pokemonOnClick(int i);
    }

    public void swapList(List<Pokemon> newList) {

        mPokemons = newList;
        if (newList != null) {

            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}

