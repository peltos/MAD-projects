package com.example.pelt.numbertrivia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.ViewHolder>{

    private Boolean orientation = true;
    private List<FactResponse> numbers;

    public FactAdapter(List<FactResponse> responeNumbers) {
        this.numbers = responeNumbers;
    }

    @Override
    public FactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if(orientation){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.number_item, viewGroup, false);
            orientation = false;
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.number_item_reverse, viewGroup, false);
            orientation = true;
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(FactAdapter.ViewHolder viewHolder, int i) {
        FactResponse trivia = numbers.get(i);
        viewHolder.factNumber.setText(trivia.getNumber());
        viewHolder.factText.setText(trivia.getText());
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView factNumber, factText;

        public ViewHolder(View itemView) {
            super(itemView);
            factNumber = itemView.findViewById(R.id.factNumber);
            factText = itemView.findViewById(R.id.factText);
        }
    }
}