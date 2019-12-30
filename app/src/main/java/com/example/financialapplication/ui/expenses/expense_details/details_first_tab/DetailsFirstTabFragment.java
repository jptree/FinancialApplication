package com.example.financialapplication.ui.expenses.expense_details.details_first_tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailsFirstTabFragment extends Fragment {

    public static final String ARG_OBJECT = "Test";
    private DetailsFirstTabViewModel viewModel;
    private RecyclerView recentTransactions;
    private TextView progressTextView;
    final RecentTransactions adapter = new RecentTransactions();
    private List<TransactionEntity> transactionEntitiesList;
    List<Long[]> dateRanges = new ArrayList<>();
    private TextView testing;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel =
                ViewModelProviders.of(this).get(DetailsFirstTabViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_first_page_expense_details, container, false);

        recentTransactions = root.findViewById(R.id.recycler_view_recent_transactions);
        recentTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        recentTransactions.setHasFixedSize(true);

        recentTransactions.setAdapter(adapter);

        dateRanges = createDateRange();

        final float[] totalSpent = {0};
        final TextView textViewTotalSpent = root.findViewById(R.id.textViewExpenseTotalSpent);

        SeekBar seekBar = root.findViewById(R.id.seekBarExpenseDetails);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        progressTextView = root.findViewById(R.id.textViewProgress);

        Log.d(TAG, "onCreateView: " + viewModel.getAllTransactions().getValue());

        testing = root.findViewById(R.id.textView3);


        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 30);
        viewModel.insertTransaction(new TransactionEntity(calendar, (float) 122.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot", 1234));


        viewModel.getSpecifiedTransactions(1577549491871L, 1580227891871L);

        viewModel.getTransactionsList().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {
                if (transactionEntities != null) {
                    adapter.setTransactionEntityList(transactionEntities);
                    transactionEntitiesList = transactionEntities;
                } // if else, show nothing statement
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
    }

    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int progressValue = 0;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Date date = new Date((long) getDateRange(progress)[0] * 1000);
            DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
            String formattedDate = dateFormat.format(date);
            progressValue = progress;
            progressTextView.setText("Historic Month of " + formattedDate);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {


            HashMap<String, Float> subcategories = new HashMap<String, Float>();
            float totalSpent = 0;
            String subcategoryCheck;


            for (TransactionEntity entity : transactionEntitiesList) {
                if (entity.getTransactionAmount() > progressValue) {
                    totalSpent = totalSpent + entity.getTransactionAmount();
                    subcategoryCheck = entity.getSubcategory();
                    if (!subcategories.containsKey(subcategoryCheck)) {
                        subcategories.put(subcategoryCheck, entity.getTransactionAmount());
                    } else {
                        subcategories.put(subcategoryCheck, subcategories.get(subcategoryCheck) + entity.getTransactionAmount());
                    }
                }
            }
//            testing.setText(subcategories.values().toString());
            Log.d(TAG, "onStopTrackingTouch: " + subcategories.keySet());
            Log.d(TAG, "onStopTrackingTouch: " + subcategories.values());

            viewModel.getSpecifiedTransactions(getDateRange(progressValue)[0], getDateRange(progressValue)[1]);
            Log.d(TAG, "onStopTrackingTouch: " + getDateRange(progressValue)[0]);
            Log.d(TAG, "onStopTrackingTouch: " + getDateRange(progressValue)[1]);
        }
    };

    private Long[] getDateRange(int progress) {
        int progressMod = (progress - progress % 5)/5;
        Long range = dateRanges.get(progressMod)[0] / 1000;
        Long range2 = dateRanges.get(progressMod)[1] / 1000;
        Long[] ranges = new Long[2];
        ranges[0] = range;
        ranges[1] = range2;
        return ranges;
    }

    private List<Long[]> createDateRange() {
        List<Long[]> dateRanges = new ArrayList<Long[]>();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, 1);
        today.add(Calendar.MONTH, -15);
        for (int i = 0; i < 21; i++) {
            Long[] subRange = new Long[2];
            if (i < 15) {
                today.add(Calendar.MONTH, 1);
                int endingDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
                int month = today.get(Calendar.MONTH);
                int year = today.get(Calendar.YEAR);

                Calendar endingDate = Calendar.getInstance();
                endingDate.set(year, month, endingDay);

                subRange[0] = today.getTimeInMillis();
                subRange[1] = endingDate.getTimeInMillis();
                dateRanges.add(subRange);
            } else {
                subRange[0] = 0L;
                subRange[1] = 200000000000L;

                dateRanges.add(subRange);
            }
        }

        return dateRanges;
    }

}
