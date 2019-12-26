package com.example.financialapplication.db.dao;

import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LifeEventDao {
    @Insert
    void insert(LifeEventEntity lifeEventEntity);

    @Update
    void update(LifeEventEntity lifeEventEntity);

    @Delete
    void delete(LifeEventEntity lifeEventEntity);

    @Query("DELETE FROM life_events_table")
    void deleteAllLifeEvents();

    @Query("DELETE FROM life_events_table WHERE imageId =:imageId")
    void deleteLifeEventById(int imageId);

    @Query("SELECT * FROM life_events_table ORDER BY beginningDate ASC")
    LiveData<List<LifeEventEntity>> getAllLifeEvents();

//    @Query("SELECT * FROM life_events_table ORDER BY eventName DESC")
//    List<LifeEventEntity> getAllLifeEventsStatic();
}


