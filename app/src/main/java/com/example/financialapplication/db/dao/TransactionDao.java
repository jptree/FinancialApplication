package com.example.financialapplication.db.dao;

import com.example.financialapplication.db.entity.TransactionEntity;

import java.math.BigInteger;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TransactionDao {
    @Insert
    void insert(TransactionEntity transactionEntity);

    @Update
    void update(TransactionEntity transactionEntity);

    @Delete
    void delete(TransactionEntity transactionEntity);

    @Query("DELETE FROM transactions_table")
    void deleteAllTransactions();

    @Query("SELECT * FROM transactions_table ORDER BY transactionDate ASC")
    LiveData<List<TransactionEntity>> getAllTransactions();

    @Query("SELECT * FROM transactions_table WHERE transactionDate BETWEEN :minValue AND :maxValue ORDER BY transactionDate ASC")
    List<TransactionEntity> getSpecificTransactions(float minValue, float maxValue);

    @Query("SELECT SUM(transactionAmount) AS sum, subcategory AS subcategoryName FROM transactions_table WHERE transactionDate BETWEEN :minValue AND :maxValue GROUP BY subcategory")
    List<SubcategorySum> getSubcategorySum(float minValue, float maxValue);

    class SubcategorySum {
        String subcategoryName;
        Float sum;

        public void setSubcategoryName(String subcategoryName) {
            this.subcategoryName = subcategoryName;
        }

        public void setSum(Float sum) {
            this.sum = sum;
        }

        public Float getSum() {
            return sum;
        }

        public String getSubcategoryName() {
            return subcategoryName;
        }
    }
}