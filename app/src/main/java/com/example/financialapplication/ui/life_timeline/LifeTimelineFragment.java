package com.example.financialapplication.ui.life_timeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.LifeEventEntity;
import com.example.financialapplication.ui.expense_details.ExpenseDrawableView;
import com.example.financialapplication.ui.home.HomeFragmentDirections;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LifeTimelineFragment extends Fragment {

    private LifeTimelineViewModel lifeTimelineViewModel;
    private LifeTimelineDrawableView lifeTimelineDrawableView;
    private LinearLayout layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifeTimelineViewModel =
                ViewModelProviders.of(this).get(LifeTimelineViewModel.class);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_life_timeline, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);

        layout = (LinearLayout) root.findViewById(R.id.life_timeline_drawable);
        lifeTimelineDrawableView = new LifeTimelineDrawableView(getContext());
//        layout.view

        lifeTimelineViewModel.getAllLifeEvents().observe(getViewLifecycleOwner(), new Observer<List<LifeEventEntity>>() {
            @Override
            public void onChanged(List<LifeEventEntity> lifeEvents) {
                layout.removeView(lifeTimelineDrawableView);
                lifeTimelineDrawableView.setLifeEvents(lifeEvents);
                layout.addView(lifeTimelineDrawableView);
            }
        });
//

//        Log.d(TAG, "onCreateView: " + lifeTimelineViewModel.getAllLifeEventsStatic());
//        lifeTimelineDrawableView.setLifeEvents(lifeTimelineViewModel.getAllLifeEventsStatic());




        Button buttonLifeEventSelector = (Button) root.findViewById(R.id.buttonAddLifeEvent);
        buttonLifeEventSelector.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavDirections action = LifeTimelineFragmentDirections.actionLifeTimelineFragmentToLifeTimelineSelectorFragment();
                Navigation.findNavController(v).navigate(action);

//                notifyItemChanged(position);
                Log.d(TAG, "onClick: Hi");
            }
        });

        Button buttonDeleteLifeEvent = (Button) root.findViewById(R.id.buttonDeleteLifeEvent);
        buttonDeleteLifeEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lifeTimelineViewModel.deleteLifeEventById(2131165329);
                Log.d(TAG, "onClick: " + "Clicked yeet");
            }

        });

//        return performanceGraphic;
        return root;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        ((ViewGroup) lifeTimelineDrawableView.getParent()).removeAllViews();
//
//    }


}
