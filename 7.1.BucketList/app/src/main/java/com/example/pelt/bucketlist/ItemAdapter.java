package com.example.pelt.bucketlist;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    final private ItemClickListener ItemClickListener;

    public ItemAdapter(List<Item> items, ItemClickListener ItemClickListener1) {
        this.items = items;
        this.ItemClickListener = ItemClickListener1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = items.get(i);
        viewHolder.ItemTitle.setText(item.getTitle());
        viewHolder.Itemdescription.setText(item.getDescription());
        viewHolder.ItemCheck.setChecked(item.isChecked());
        viewHolder.checkCrossed(item.isChecked());
    }

    public void swapList(List<Item> newList) {
        items = newList;
        if (newList != null) {
            this.notifyDataSetChanged();
        }
    }

    public interface ItemClickListener {
        void ItemOnClick(int i, boolean check);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ItemTitle, Itemdescription;
        public CheckBox ItemCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            ItemTitle = itemView.findViewById(R.id.ItemTitle);
            Itemdescription = itemView.findViewById(R.id.ItemDesc);
            ItemCheck = itemView.findViewById(R.id.ItemCheckBox);

            ItemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkCrossed(isChecked);
                }
            });
            ItemCheck.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            boolean check = ((CheckBox) v).isChecked();
            ItemClickListener.ItemOnClick(clickedPosition, check);
        }

        public void checkCrossed(boolean check) {
            if (check) {
                ItemTitle.setPaintFlags(ItemTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                Itemdescription.setPaintFlags(Itemdescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                ItemTitle.setPaintFlags(ItemTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                Itemdescription.setPaintFlags(Itemdescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }


    }
}