package com.example.financialapplication.ui.life_timeline.life_timeline_selections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financialapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LifeTimelineSelectorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.fragment_life_timeline_selector, parent, false);
        return new LifeTimelineSelectorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LifeTimelineSelectorHolder extends RecyclerView.ViewHolder {
        private RecyclerView lifeTimelineSelector;

        public LifeTimelineSelectorHolder(View itemView) {
            super(itemView);
            lifeTimelineSelector = itemView.findViewById(R.id.recycler_view_life_timeline_selector);
        }
    }
}
