//package com.example.financialapplication.db.entity;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "transactions_table")
//public class SubcategoryEntity {
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//    private String subcategoryName;
//    private Float subcategorySum;
//
//    public SubcategoryEntity(String subcategoryName, Float subcategorySum) {
//        this.subcategoryName = subcategoryName;
//        this.subcategorySum = subcategorySum;
//    }
//
//    public Float getSubcategorySum() {
//        return subcategorySum;
//    }
//
//    public String getSubcategoryName() {
//        return subcategoryName;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//}
//
//
////@Entity(tableName = "life_events_table")
////public class LifeEventEntity {
////    @PrimaryKey(autoGenerate = true)
////    private int id;
////    private String eventName;
////    private int imageId;
////
////    private Calendar beginningDate;
////
////
////    public LifeEventEntity(String eventName, int imageId, Calendar beginningDate) {
////        this.eventName = eventName;
////        this.imageId = imageId;
////        this.beginningDate = beginningDate;
////    }
////
////    public void setId(int id) {
////        this.id = id;
////    }
////
////    public int getId() {
////        return id;
////    }
////
////    public String getEventName() {
////        return eventName;
////    }
////
////    public int getImageId() {
////        return imageId;
////    }
////
////    public Calendar getBeginningDate() {
////        return beginningDate;
////    }
////}