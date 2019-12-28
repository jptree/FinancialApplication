package com.example.financialapplication.ui.life_timeline.life_timeline_selections;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.LifeEventEntity;
import com.example.financialapplication.ui.life_timeline.LifeTimelineViewModel;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class LifeEventDetailsFragment extends Fragment {
    LifeTimelineViewModel lifeTimelineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_life_event_details, container, false);
        ImageView lifeEventImage = (ImageView) root.findViewById(R.id.imageViewLifeEventDetails);
        int imageId = LifeEventDetailsFragmentArgs.fromBundle(getArguments()).getImageReference();
        lifeEventImage.setImageDrawable(ContextCompat.getDrawable(getContext(), imageId));

        lifeTimelineViewModel = ViewModelProviders.of(this).get(LifeTimelineViewModel.class);
        final EditText editTextLifeEventTitle = (EditText) root.findViewById(R.id.editTextLifeEventTitle);

        EditText editTextDate = (EditText) root.findViewById(R.id.editTextSetDate);
        editTextDate.setInputType(InputType.TYPE_NULL);

        Button buttonSave = (Button) root.findViewById(R.id.buttonSubmitNewLifeEvent);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newLifeEventText = editTextLifeEventTitle.getText().toString();
                LifeEventEntity newLifeEvent = new LifeEventEntity(newLifeEventText, imageId, new Date(editTextDate.getText().toString()));
                lifeTimelineViewModel.insertLifeEvent(newLifeEvent);


                NavDirections action = LifeEventDetailsFragmentDirections.actionLifeEventDetailsFragmentToLifeTimelineFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });
        DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePickerDialog[0] = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                        editTextDate.setText(year + "/" + month + 1 +"/" + day);
                    }
                        }, year, month, day);
                datePickerDialog[0].show();
            }
        });
        return root;
    }

}
