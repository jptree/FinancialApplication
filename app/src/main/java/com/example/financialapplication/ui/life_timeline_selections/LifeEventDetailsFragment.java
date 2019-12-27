//package com.example.financialapplication.ui.life_timeline_selections;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.example.financialapplication.R;
//import com.example.financialapplication.db.entity.LifeEventEntity;
//import com.example.financialapplication.ui.life_timeline.LifeTimelineViewModel;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.navigation.NavController;
//import androidx.navigation.NavDirections;
//import androidx.navigation.Navigation;
//
//public class LifeEventDetailsFragment extends Fragment {
//    LifeTimelineViewModel lifeTimelineViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_life_event_details, container, false);
//        ImageView lifeEventImage = (ImageView) root.findViewById(R.id.imageViewLifeEventDetails);
//        int imageId = LifeEventDetailsFragmentArgs.fromBundle(getArguments()).getImageReference();
//        lifeEventImage.setImageDrawable(ContextCompat.getDrawable(getContext(), imageId));
//
//        lifeTimelineViewModel = ViewModelProviders.of(this).get(LifeTimelineViewModel.class);
//        final EditText editTextLifeEventTitle = (EditText) root.findViewById(R.id.editTextLifeEventTitle);
//
//        Button buttonSave = (Button) root.findViewById(R.id.buttonSubmitNewLifeEvent);
//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newLifeEventText = editTextLifeEventTitle.getText().toString();
//                LifeEventEntity newLifeEvent = new LifeEventEntity(newLifeEventText, imageId);
//                lifeTimelineViewModel.insertLifeEvent(newLifeEvent);
//
//
//                NavDirections action = LifeEventDetailsFragmentDirections.actionLifeEventDetailsFragmentToLifeTimelineFragment();
//                Navigation.findNavController(v).navigate(action);
//            }
//        });
//        return root;
//    }
//
//}
