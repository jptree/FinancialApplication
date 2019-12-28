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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    private List<TransactionEntity> transactionEntities;

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

        final float[] totalSpent = {0};
        final TextView textViewTotalSpent = root.findViewById(R.id.textViewExpenseTotalSpent);

        SeekBar seekBar = root.findViewById(R.id.seekBarExpenseDetails);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        progressTextView = root.findViewById(R.id.textViewProgress);

        viewModel.getAllTransactions().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {

                adapter.setTransactionEntityList(transactionEntities);
                setTransactionEntities(transactionEntities);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
//            ViewPager2 viewPager = view.findViewById(R.id.pager);
    }

    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int progressValue = 0;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String progressText = "Date " + String.valueOf(progress);
            progressValue = progress;
            progressTextView.setText(progressText);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            HashMap<String, Float> subcategories = new HashMap<String, Float>();
            List<TransactionEntity> shownTransactions = new ArrayList<>();
            float totalSpent = 0;
            String subcategoryCheck;
            

            for (TransactionEntity entity : transactionEntities) {
                if (entity.getTransactionAmount() > progressValue) {
                    totalSpent = totalSpent + entity.getTransactionAmount();
                    subcategoryCheck = entity.getSubcategory();
                    if (!subcategories.containsKey(subcategoryCheck)) {
                        subcategories.put(subcategoryCheck, entity.getTransactionAmount());
                    } else {
                        subcategories.put(subcategoryCheck, subcategories.get(subcategoryCheck) + entity.getTransactionAmount());
                        Log.d(TAG, "onStopTrackingTouch: " + entity.getTransactionDate());
                        Log.d(TAG, "onStopTrackingTouch: " + new Date(2019, 3, 4));
                    }
                    shownTransactions.add(entity);
                }
            }
            adapter.setTransactionEntityList(shownTransactions);
            Log.d(TAG, "onStopTrackingTouch: " + subcategories.keySet());
            Log.d(TAG, "onStopTrackingTouch: " + subcategories.values());
        }
    };

}
