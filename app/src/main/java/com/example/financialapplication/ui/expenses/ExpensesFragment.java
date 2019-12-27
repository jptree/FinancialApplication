package com.example.financialapplication.ui.expenses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialapplication.R;
import com.example.financialapplication.ReadingCSV.CSVReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ExpensesFragment extends Fragment {

    private ExpensesViewModel expensesViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> myDataset;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        expensesViewModel =
                ViewModelProviders.of(this).get(ExpensesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_expenses, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        expensesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_expense_categories);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        myDataset = new ArrayList<>();
        myDataset.add("Home and Utilities");
        myDataset.add("Transportation");
        myDataset.add("Groceries");
        myDataset.add("Personal and Family Care");
        myDataset.add("Health");
        myDataset.add("Insurance");
        myDataset.add("Restaurants and Dining");
        myDataset.add("Shopping and Entertainment");
        myDataset.add("Travel");
        myDataset.add("Cash/Checks/Miscellaneous");
        myDataset.add("Giving");
        myDataset.add("Business Expenses");
        myDataset.add("Education");
        myDataset.add("Finance");
        myDataset.add("Uncategorized");

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        CSVReader yes = new CSVReader(inputStream);
        List<String[]> dataList = yes.readData();

//        for (int i = 0; i < dataList.size(); i++) {
//            Log.d(TAG, "onCreateView: " + Arrays.toString(dataList.get(i)));
//        }

        for (String[] gen:dataList) {
            Log.d(TAG, "onCreateView: " + gen[0]);
        }

        mAdapter = new ExpensesAdapter(getContext(), myDataset);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

}