package com.example.financialapplication.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "life_events_table")
public class LifeEventEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String eventName;
    private int imageId;

    public LifeEventEntity(String eventName, int imageId) {
        this.eventName = eventName;
        this.imageId = imageId;
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
}
