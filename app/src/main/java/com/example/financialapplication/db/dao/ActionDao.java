package com.example.financialapplication.db.dao;

import com.example.financialapplication.db.entity.ActionEntity;
import com.example.financialapplication.db.entity.ActionEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ActionDao {
    @Insert
    void insert(ActionEntity actionEntity);

    @Update
    void update(ActionEntity actionEntity);

    @Delete
    void delete(ActionEntity actionEntity);

    @Query("DELETE FROM actions_table")
    void deleteAllActions();

    @Query("DELETE FROM actions_table WHERE imageId =:imageId")
    void deleteActionById(int imageId);

    @Query("SELECT * FROM actions_table WHERE completed = 1 ORDER BY doByDate ASC")
    LiveData<List<ActionEntity>> getAllCompletedActions();

    @Query("SELECT * FROM actions_table WHERE completed = 0 ORDER BY doByDate ASC")
    LiveData<List<ActionEntity>> getAllIncompleteActions();

}


