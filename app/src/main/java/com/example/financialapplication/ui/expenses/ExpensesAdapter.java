package com.example.financialapplication.ui.expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financialapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExpensesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> stringArrayList;

    public ExpensesAdapter(Context context, ArrayList<String> stringList) {
        this.stringArrayList = stringList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        switch(viewType) {
            case 1:
                v = inflater.inflate(R.layout.card_view_expense_double, parent, false);
                return new ExpenseHolderDouble(v, viewType, parent.getContext());
            
            case 2:
                v = inflater.inflate(R.layout.card_view_expense_singular, parent, false);
                return new ExpenseHolderSingular(v, viewType, parent.getContext());
            
            default:
                return null;
        }
        
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 1:


                ExpenseHolderDouble viewHolderDouble = (ExpenseHolderDouble) holder;
                viewHolderDouble.expenseCategoryTitleLeft.setText(stringArrayList.get(position));
                viewHolderDouble.expenseCategoryTitleRight.setText(stringArrayList.get(position + 1));
                break;


            case 2:
                ExpenseHolderSingular viewHolderSingular = (ExpenseHolderSingular) holder;
                viewHolderSingular.expenseCategoryTitle.setText(stringArrayList.get(position));
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemCount() {
        return stringArrayList.size() - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2  == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    class ExpenseHolderSingular extends RecyclerView.ViewHolder {
        private TextView expenseCategoryTitle;

        public ExpenseHolderSingular(View itemView, int viewType, Context context) {
            super(itemView);
            expenseCategoryTitle = itemView.findViewById(R.id.text_expense_name_singular);
        }
    }

    class ExpenseHolderDouble extends RecyclerView.ViewHolder {
        private TextView expenseCategoryTitleLeft;
        private TextView expenseCategoryTitleRight;

        public ExpenseHolderDouble(View itemView, int viewType, Context context) {
            super(itemView);
            expenseCategoryTitleLeft = itemView.findViewById(R.id.text_expense_name_left);
            expenseCategoryTitleRight = itemView.findViewById(R.id.text_expense_name_right);
        }
    }
}
