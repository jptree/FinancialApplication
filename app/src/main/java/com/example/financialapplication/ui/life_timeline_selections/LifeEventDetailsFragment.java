package com.example.financialapplication.ui.life_timeline_selections;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.financialapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class LifeEventDetailsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_life_event_details, container, false);
        ImageView lifeEventImage = (ImageView) root.findViewById(R.id.imageViewLifeEventDetails);
        int imageId = LifeEventDetailsFragmentArgs.fromBundle(getArguments()).getImageReference();
        lifeEventImage.setImageDrawable(ContextCompat.getDrawable(getContext(), imageId));

        Button buttonSave = (Button) root.findViewById(R.id.buttonSubmitNewLifeEvent);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = LifeEventDetailsFragmentDirections.actionLifeEventDetailsFragmentToLifeTimelineFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });
        return root;
    }

}
