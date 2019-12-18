package com.example.financialapplication.ui.life_picture_board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financialapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LifePictureBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> stringArrayList;
    private Context parentContext;

    public LifePictureBoardAdapter(Context context, ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
        this.parentContext = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_view_life_category, parent, false);
        return new LifeCategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LifeCategoryHolder viewHolder = (LifeCategoryHolder) holder;
        viewHolder.lifeCategoryTitle.setText(stringArrayList.get(position));

        final LifeElementsAdapter lifeElementsAdapter = new LifeElementsAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(parentContext, LinearLayoutManager.HORIZONTAL, false);
        viewHolder.lifeCategoryElements.setLayoutManager(llm);
        viewHolder.lifeCategoryElements.setAdapter(lifeElementsAdapter);

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    class LifeCategoryHolder extends RecyclerView.ViewHolder {
        private TextView lifeCategoryTitle;
        private RecyclerView lifeCategoryElements;

        public LifeCategoryHolder(View itemView) {
            super(itemView);
            lifeCategoryTitle = itemView.findViewById(R.id.textViewLifeCategoryTitle);
            lifeCategoryElements = itemView.findViewById(R.id.recycler_view_life_category_items);
        }
    }
}
