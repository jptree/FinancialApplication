package com.example.financialapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.financialapplication.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CustomDrawableView customDrawableView;

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

        final View scroll = root.findViewById(R.id.scroll_view_drawable);
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.linear_layout_drawable);

        customDrawableView = new CustomDrawableView(getContext());
        layout.addView(customDrawableView);

//        return customDrawableView;
        return root;
    }
}