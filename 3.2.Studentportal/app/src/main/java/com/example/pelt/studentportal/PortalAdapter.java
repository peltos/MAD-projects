package com.example.pelt.studentportal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {

    private List<Portal> mPortal;

    public PortalAdapter(List<Portal> portals, PortalClickListener mPortalClickListener) {
        mPortal = portals;
        this.mPortalClickListener = mPortalClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance
        PortalAdapter.ViewHolder viewHolder = new PortalAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Portal portal =  mPortal.get(position);
        holder.textView.setText(portal.getmTitle());
    }

    @Override
    public int getItemCount() {
        return mPortal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=  itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mPortalClickListener.portalOnClick(clickedPosition);
        }
    }

    final private PortalClickListener mPortalClickListener;

    public interface PortalClickListener{
        void portalOnClick(int i);
    }
}

