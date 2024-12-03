package com.example.financialapplication.db.dao;

import com.example.financialapplication.db.entity.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Query("DELETE FROM user_information_table")
    void deleteAllUsers();

    @Query("DELETE FROM user_information_table WHERE id = 0")
    void deleteUserById();

    @Query("SELECT * FROM user_information_table")
    LiveData<List<UserEntity>> getAllUsers();

}


