package com.example.financialapplication.ui.expense_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExpenseDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExpenseDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
