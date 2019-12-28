package com.example.financialapplication.ui.expenses.expense_details.details_first_tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentTransactions extends RecyclerView.Adapter<RecentTransactions.TransactionHolder>{

    private List<TransactionEntity> transactionEntityList = new ArrayList<>();
    private Context context;
    private Drawable mImageDrawable;

    @NonNull
    @Override
    public RecentTransactions.TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_transaction, parent, false);

        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        TransactionEntity mTransactionEntity = transactionEntityList.get(position);

//        mImageDrawable = context.getResources().getDrawable(mTransactionEntity.getImageId());
        holder.textViewMerchantName.setText(mTransactionEntity.getMerchantName());
        holder.textViewDate.setText((String) mTransactionEntity.getTransactionDate().toString());
        holder.textViewAmount.setText(String.valueOf(mTransactionEntity.getTransactionAmount()));
    }

    @Override
    public int getItemCount() {
        return transactionEntityList.size();
    }

    public void setTransactionEntityList(List<TransactionEntity> transactionEntityList) {
        this.transactionEntityList = transactionEntityList;
        notifyDataSetChanged();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private TextView textViewAmount;
        private TextView textViewMerchantName;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewTransactionDate);
            textViewAmount = itemView.findViewById(R.id.textViewTransactionAmount);
            textViewMerchantName = itemView.findViewById(R.id.textViewTransactionMerchantName);

        }
    }
}
