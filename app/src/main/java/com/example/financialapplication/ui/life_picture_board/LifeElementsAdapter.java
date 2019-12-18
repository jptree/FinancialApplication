package com.example.financialapplication.ui.life_picture_board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financialapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LifeElementsAdapter extends RecyclerView.Adapter<LifeElementsAdapter.LifeElementsHolder> {
    @NonNull
    @Override
    public LifeElementsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_life_category_canvas, parent, false);

        return new LifeElementsHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull LifeElementsHolder holder, int position) {
        String mItemName = "asd";
        holder.itemTitle.setText(mItemName);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class LifeElementsHolder extends RecyclerView.ViewHolder {
        private TextView itemTitle;

        public LifeElementsHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.textViewLifeItemName);
        }
    }
}
