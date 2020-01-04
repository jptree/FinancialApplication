package com.example.financialapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.ActionEntity;

import java.util.Calendar;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    IncompleteActionsAdapter incompleteActionsAdapter = new IncompleteActionsAdapter();
    IncompleteActionsAdapter completedActionsAdapter = new IncompleteActionsAdapter(); // Using incomplete adapter...

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RecyclerView rvCompleted = root.findViewById(R.id.recycler_view_completed_actions);
        RecyclerView rvIncomplete = root.findViewById(R.id.recycler_view_incomplete_actions);



        rvCompleted.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCompleted.setHasFixedSize(true);// Using Incomplete adapter!
        rvCompleted.setAdapter(completedActionsAdapter);
        notificationsViewModel.getAllCompletedActions().observe(this, new Observer<List<ActionEntity>>() {
            @Override
            public void onChanged(List<ActionEntity> actionEntities) {
                completedActionsAdapter.setActionEntityList(actionEntities);
            }
        });

        rvIncomplete.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIncomplete.setHasFixedSize(true);
        rvIncomplete.setAdapter(incompleteActionsAdapter);
        notificationsViewModel.getAllIncompleteActions().observe(this, new Observer<List<ActionEntity>>() {
            @Override
            public void onChanged(List<ActionEntity> actionEntities) {
                incompleteActionsAdapter.setActionEntityList(actionEntities);
            }
        });

//        String directions = "Let's implement this later...%/%Somewhere you should be able to write in this..."; // Separated by %/%
//        Calendar calendar = Calendar.getInstance();
//
//
//        ActionEntity actionEntity = new ActionEntity("Specify First Name", "Let's get acquainted!", directions, true);
//        actionEntity.setDoASAP("Do this as soon as possible!");
//        actionEntity.setDoByDate(calendar);
//        actionEntity.setCompleted(false);
//        notificationsViewModel.insertAction(actionEntity);
//
//        actionEntity = new ActionEntity("Specify Age", "I need to know a little about you.", directions, true);
//        actionEntity.setDoASAP("Do this as soon as possible!");
//        actionEntity.setDoByDate(calendar);
//        actionEntity.setCompleted(false);
//        notificationsViewModel.insertAction(actionEntity);
//
//        actionEntity = new ActionEntity("Add Transaction Data", "I want to help plan your life.", directions, true);
//        actionEntity.setDoASAP("Do this as soon as possible!");
//        actionEntity.setCompleted(false);
//        actionEntity.setDoByDate(calendar);
//        notificationsViewModel.insertAction(actionEntity);
//
//        actionEntity = new ActionEntity("Specify Income Range", "This will help customize your experience.", directions, true);
//        actionEntity.setDoASAP("Do this as soon as possible!");
//        actionEntity.setCompleted(false);
//        actionEntity.setDoByDate(calendar);
//        actionEntity.setInputDataType("String");
//        notificationsViewModel.insertAction(actionEntity);
//
//        actionEntity = new ActionEntity("Download the App!", "Hopefully this will prove worth it!", directions, false);
//        actionEntity.setDoByDate(calendar);
//        actionEntity.setCompleted(true);
//        notificationsViewModel.insertAction(actionEntity);





//        Calendar calendar = Calendar.getInstance();
//        ActionEntity actionEntity;
//        actionEntity = new ActionEntity("Specify Income Range", "This will help customize your experience.", "Hi", true);
//        actionEntity.setDoASAP("Do this as soon as possible!");
////            actionEntity.setCompleted(false);
//        actionEntity.setDoByDate(calendar);
//        actionEntity.setInputDataType("String");
//        notificationsViewModel.insertAction(actionEntity);

        return root;
    }
}