package com.example.financialapplication.db.entity;

import java.util.Calendar;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "life_events_table")
public class LifeEventEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String eventName;
    private int imageId;

    private Calendar beginningDate;


    public LifeEventEntity(String eventName, int imageId, Calendar beginningDate) {
        this.eventName = eventName;
        this.imageId = imageId;
        this.beginningDate = beginningDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public int getImageId() {
        return imageId;
    }

    public Calendar getBeginningDate() {
        return beginningDate;
    }
}
