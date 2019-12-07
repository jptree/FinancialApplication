package com.example.financialapplication.ui.expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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
        View v = inflater.inflate(R.layout.card_view_expense, parent, false);




        return new ExpenseHolder(v);
        
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ExpenseHolder viewHolder = (ExpenseHolder) holder;
        viewHolder.expenseCategoryTitle.setText(stringArrayList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mString = stringArrayList.get(position);
                Toast.makeText(v.getContext(), stringArrayList.get(position), Toast.LENGTH_SHORT).show();

                ExpensesFragmentDirections.ActionNavigationExpensesToExpenseDetailsFragment action = ExpensesFragmentDirections.actionNavigationExpensesToExpenseDetailsFragment();
                action.setExpense(mString);


                Navigation.findNavController(v).navigate(action);

                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }


    class ExpenseHolder extends RecyclerView.ViewHolder {
        private TextView expenseCategoryTitle;

        public ExpenseHolder(View itemView) {
            super(itemView);
            expenseCategoryTitle = itemView.findViewById(R.id.text_expense_name);
        }
    }


}
