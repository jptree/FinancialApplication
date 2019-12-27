package com.example.financialapplication.ui.life_timeline.life_timeline_selections;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.financialapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LifeTimelineSelectorFragment extends Fragment {
//    private LifeTimelineSelectorViewModel lifeTimelineSelectorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        lifeTimelineSelectorViewModel =
//                ViewModelProviders.of(this).get(LifeTimelineSelectorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_life_timeline_selector, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_life_timeline_selector);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<Integer> lifeEvents = new ArrayList<>(9);
        lifeEvents.add(R.drawable.ic_home_black_24dp);
        lifeEvents.add(R.drawable.ic_notifications_black_24dp);
        lifeEvents.add(R.drawable.ic_dashboard_black_24dp);
        lifeEvents.add(R.drawable.ic_home_black_24dp);
        lifeEvents.add(R.drawable.ic_notifications_black_24dp);
        lifeEvents.add(R.drawable.ic_dashboard_black_24dp);
        lifeEvents.add(R.drawable.ic_home_black_24dp);
        lifeEvents.add(R.drawable.ic_notifications_black_24dp);
        lifeEvents.add(R.drawable.ic_dashboard_black_24dp);
        recyclerView.setAdapter(new LifeEventsAdapter(lifeEvents, getContext()));

        return root;
    }

    private void onLifeEventSelected(int lifeEventId) {
        Log.d(TAG, "onLifeEventSelected: " + lifeEventId);

    }

    class LifeEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final List<Integer> lifeEventIds;
        private final Context context;
        private final LayoutInflater layoutInflater;
//        private final View fragment;
//        private final NavController navController;

        LifeEventsAdapter(@NonNull List<Integer> lifeEventIds, @NonNull Context context) {
//        LifeEventsAdapter(@NonNull List<Integer> lifeEventIds, @NonNull Context context, View fragment) {
            this.lifeEventIds = lifeEventIds;
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
//            this.fragment = fragment;
//            this.navController = navController;

        }

        @Override
        public LifeEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LifeEventsViewHolder(layoutInflater.inflate(R.layout.item_life_event_image, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            LifeEventsViewHolder viewHolder = (LifeEventsViewHolder) holder;
            viewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(context, getItem(position)));
        }

        @Override
        public int getItemCount() {
            return lifeEventIds.size();
        }

        private int getItem(int position) {
            return lifeEventIds.get(position);
        }

        class LifeEventsViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            LifeEventsViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageViewLifeEvent);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public  void onClick(View view) {
                        int pos = getAdapterPosition();
                        if (pos >= 0) {
                            onLifeEventSelected(getItem(pos));
                        }

                        NavDirections action = LifeTimelineSelectorFragmentDirections.actionLifeTimelineSelectorFragmentToLifeEventDetailsFragment(getItem(pos));
//                        action.setImageReference(getItem(pos));
                        Navigation.findNavController(view).navigate(action);
                    }
                });
            }
        }
    }
}
