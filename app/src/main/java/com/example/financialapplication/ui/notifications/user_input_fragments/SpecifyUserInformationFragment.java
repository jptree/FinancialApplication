package com.example.financialapplication.ui.notifications.user_input_fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.UserEntity;
import com.example.financialapplication.db.repository.UserRepository;
import com.example.financialapplication.ui.TextValidator;
import com.example.financialapplication.ui.home.HomeFragmentArgs;
import com.example.financialapplication.ui.notifications.NotificationsViewModel;
//import com.example.financialapplication.ui.notifications.SpecifyUserInformationFragmentArgs;
//import com.example.financialapplication.ui.notifications.SpecifyUserInformationFragmentDirections;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecifyUserInformationFragment extends Fragment {

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_specify_user_information, container,false);
        context = getContext();

        LinearLayout layout = root.findViewById(R.id.linear_layout_specify_user_information);

        String layoutType = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getLayoutType();

        switch (layoutType) {
            case "asdaksdasdasdSpecify User Information":
                String specify = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getSpecify()[0];
                TextView tv = new TextView(context);
                tv.setText(specify);
//                String s = .fromBundle(getArguments()).getSpecify();
                TextInputEditText editText = new TextInputEditText(context);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
//                editText.addTextChangedListener(new TextValidator(editText) {
//                    @Override
//                    public void validate(TextView textView, String text) {
//                        Log.d(TAG, "validate: text " + text);
//                        if (text.matches("\\d")) {
//
//                            Log.d(TAG, "validate: contains a number!");
//                        }
//                    }
//                });


                layout.addView(tv);
                layout.addView(editText);
                break;

            case "Specify User Information":
                int layoutIteration = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getLayoutIteration();
                NotificationsViewModel notificationsViewModel;
                notificationsViewModel =
                        ViewModelProviders.of(this).get(NotificationsViewModel.class);
                UserEntity userEntity = new UserEntity("Jason", "Petri");

                switch (layoutIteration) {
                    case 0:
                        // Specify first and last names.
                        Button proceed = new Button(context);
                        proceed.setText("Proceed! -->");

                        tv = new TextView(context);
                        tv.setText("What is your first name?");
                        editText = new TextInputEditText(context);
                        editText.setInputType(InputType.TYPE_CLASS_TEXT);

                        TextView tv2 = new TextView(context);
                        tv2.setText("What is your last name?");
                        TextInputEditText editText2 = new TextInputEditText(context);
                        editText2.setInputType(InputType.TYPE_CLASS_TEXT);

                        layout.addView(tv);
                        layout.addView(editText);
                        layout.addView(tv2);
                        layout.addView(editText2);
                        layout.addView(proceed);

                        proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String[] input = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getSpecify();
                                input[0] = editText.getText().toString();
                                input[1] = editText2.getText().toString();

                                NavDirections action = SpecifyUserInformationFragmentDirections.actionSpecifyUserInformationFragmentSelf(input, "Specify User Information")
                                        .setLayoutIteration(1);
                                Navigation.findNavController(root).navigate(action);

//                                userEntity.setFirstName(tv.getText().toString());
//                                userEntity.setLastName(tv2.getText().toString());
                            }
                        });
                        break;

                    case 1:
                        // Specify Age
                        proceed = new Button(context);
                        proceed.setText("Proceed! -->");

                        Calendar selectedDate = Calendar.getInstance();
                        EditText editTextDate = new EditText(context);
                        editTextDate.setInputType(InputType.TYPE_NULL);
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
                                                editTextDate.setText(year + "/" + (month + 1) + "/" + day);
                                                selectedDate.set(year, month, day);
                                            }
                                        }, year, month, day);
                                datePickerDialog[0].show();
                            }
                        });

                        tv = new TextView(context);
                        tv.setText("When were you born?");

                        layout.addView(tv);
                        layout.addView(editTextDate);
                        layout.addView(proceed);



                        proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String[] input = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getSpecify();
                                input[2] = editTextDate.getText().toString();
                                NavDirections action = SpecifyUserInformationFragmentDirections.actionSpecifyUserInformationFragmentSelf(input, "Specify User Information")
                                        .setLayoutIteration(2);
                                Navigation.findNavController(root).navigate(action);
                                userEntity.setDayOfBirth(selectedDate);
                            }
                        });

                        break;

                    case 2:

                        // Set text of first entry title
                        tv = new TextView(context);
                        tv.setText("Do you pay for the residence you live in?");

                        // Create editText for user to input data
                        editText = new TextInputEditText(context);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);


                        proceed = new Button(context);
                        proceed.setText("Proceed! -->");
                        proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                String[] input = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getSpecify();
//                                NavController navController = Navigation.findNavController(root);
//                                navController.popBackStack(R.id.navigation_notifications, true);
//                                navController.navigate(R.id.navigation_notifications);
//                                Log.d(TAG, "onClick: " + input);


                                String[] input = SpecifyUserInformationFragmentArgs.fromBundle(getArguments()).getSpecify();
                                input[2] = editText.getText().toString();
                                NavDirections action = SpecifyUserInformationFragmentDirections.actionSpecifyUserInformationFragmentSelf(input, "Specify User Information")
                                        .setLayoutIteration(2);
                                Navigation.findNavController(root).navigate(action);

                            }
                        });


                        // Add the views to the layout.
                        layout.addView(tv);
                        layout.addView(editText);
                        layout.addView(proceed);
                        break;

                }


                break;


        }

        return root;
    }
}
