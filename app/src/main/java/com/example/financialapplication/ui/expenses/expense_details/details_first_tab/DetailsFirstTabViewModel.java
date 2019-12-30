package com.example.financialapplication.ui.expenses.expense_details.details_first_tab;

import android.app.Application;
import android.util.Log;

import com.example.financialapplication.db.entity.TransactionEntity;
import com.example.financialapplication.db.repository.TransactionRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailsFirstTabViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private MutableLiveData<List<TransactionEntity>> transactionsList = new MutableLiveData<>();

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

    public void getSpecifiedTransactions(float minValue, float maxValue) {
        transactionsList.postValue(repository.getSpecifiedTransactions(minValue, maxValue));
    }

    public MutableLiveData<List<TransactionEntity>> getTransactionsList() {
        return transactionsList;
    }

//    public LiveData<List<TransactionEntity>> getTest(float value) {
//        return repository.getTest(value);
//    }
}
