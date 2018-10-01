package com.example.pelt.gamebacklog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> mGames;

    public GameAdapter(List<Game> games, GameClickListener mGameClickListener) {
        mGames = games;
        this.mGameClickListener = mGameClickListener;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        GameAdapter.GameViewHolder viewHolder = new GameAdapter.GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        final Game game = mGames.get(position);
        holder.mCardName.setText(game.getGameText());
        holder.mCardPlatform.setText(game.getGamePlatform());
        holder.mCardDate.setText(game.getGameDate());
        holder.mCardStatus.setText(game.getGameStatus());
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mCardName, mCardPlatform, mCardDate, mCardStatus;

        public GameViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCardStatus = itemView.findViewById(R.id.cardStatus);
            mCardName = itemView.findViewById(R.id.cardName);
            mCardPlatform = itemView.findViewById(R.id.cardPlatform);
            mCardDate = itemView.findViewById(R.id.cardDate);
            mCardStatus = itemView.findViewById(R.id.cardStatus);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mGameClickListener.gameOnClick(clickedPosition);
        }
    }

    final private GameClickListener mGameClickListener;

    public interface GameClickListener {
        void gameOnClick(int i);
    }


    @Override
    public int getItemCount() {
        return mGames.size();
    }


    public void swapList(List<Game> newList) {

        mGames = newList;
        if (newList != null) {

            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}

