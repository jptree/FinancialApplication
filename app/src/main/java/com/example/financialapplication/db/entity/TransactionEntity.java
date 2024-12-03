package com.example.financialapplication.db.entity;

import java.util.Calendar;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions_table")
public class TransactionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Calendar transactionDate;
    private float transactionAmount;
    private String classification;
    private String category;
    private String subcategory;
    private String merchantName;
    private int merchantCode;

    public TransactionEntity(Calendar transactionDate, float transactionAmount, String classification,
                             String category, String subcategory, String merchantName, int merchantCode) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.classification = classification;
        this.category = category;
        this.subcategory = subcategory;
        this.merchantName = merchantName;
        this.merchantCode = merchantCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getClassification() {
        return classification;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public int getMerchantCode() {
        return merchantCode;
    }
}