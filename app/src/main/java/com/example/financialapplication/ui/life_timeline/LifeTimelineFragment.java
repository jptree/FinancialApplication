package com.example.financialapplication.ui.life_timeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.financialapplication.R;
import com.example.financialapplication.ui.home.HomeFragmentDirections;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LifeTimelineFragment extends Fragment {

    private LifeTimelineViewModel lifeTimelineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        lifeTimelineViewModel =
//                ViewModelProviders.of(this).get(LifeTimelineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_life_timeline, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Button buttonLifeEventSelector = (Button) root.findViewById(R.id.buttonAddLifeEvent);
        buttonLifeEventSelector.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavDirections action = LifeTimelineFragmentDirections.actionLifeTimelineFragmentToLifeTimelineSelectorFragment();
                Navigation.findNavController(v).navigate(action);

//                notifyItemChanged(position);
                Log.d(TAG, "onClick: Hi");
            }
        });

//        return performanceGraphic;
        return root;
    }


}
