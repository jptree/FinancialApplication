package com.example.financialapplication.db.dao;

import com.example.financialapplication.db.entity.ExpenseEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Update
    void update(ExpenseEntity expenseEntity);

    @Delete
    void delete(ExpenseEntity expenseEntity);

    @Query("DELETE FROM life_events_table")
    void deleteAllExpenses();

    @Query("DELETE FROM life_events_table WHERE imageId =:imageId")
    void deleteExpenseById(int imageId);

    @Query("SELECT * FROM life_events_table ORDER BY beginningDate ASC")
    LiveData<List<ExpenseEntity>> getAllExpenses();
}

