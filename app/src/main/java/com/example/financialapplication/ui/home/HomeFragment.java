package com.example.financialapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.financialapplication.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private PerformanceView performanceGraphic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.canvas_performance);

        performanceGraphic = new PerformanceView(getContext());
        layout.addView(performanceGraphic);

        Button buttonLifePictureBoard = (Button) root.findViewById(R.id.buttonLife);
        buttonLifePictureBoard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToLifePictureBoard();

                Navigation.findNavController(v).navigate(action);

//                notifyItemChanged(position);
                Log.d(TAG, "onClick: Hi");
            }
        });

//        return performanceGraphic;
        return root;
    }
}