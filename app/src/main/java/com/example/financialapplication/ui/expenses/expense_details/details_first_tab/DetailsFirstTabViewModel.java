package com.example.financialapplication.ui.expenses.expense_details.details_first_tab;

import android.app.Application;

import com.example.financialapplication.db.entity.TransactionEntity;
import com.example.financialapplication.db.repository.TransactionRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DetailsFirstTabViewModel extends AndroidViewModel {
    private LiveData<List<TransactionEntity>> transactions;
    private TransactionRepository repository;

    public DetailsFirstTabViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return repository.getAllTransactions();
    }

    public void insertTransaction(TransactionEntity transactionEntity) {
        repository.insert(transactionEntity);
    }

    public void deleteTransaction(TransactionEntity transactionEntity) {
        repository.delete(transactionEntity);
    }

    public void deleteAllTransactions() {
        repository.deleteAllTransactions();
    }

    public void deleteTransactionById(int id) {
        repository.deleteTransactionById(id);
    }
}
