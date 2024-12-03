package com.example.financialapplication.db.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.financialapplication.db.DateConverters;
import com.example.financialapplication.db.dao.ActionDao;
import com.example.financialapplication.db.dao.UserDao;
import com.example.financialapplication.db.entity.ActionEntity;
import com.example.financialapplication.db.entity.UserEntity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class UserInformationDatabase extends RoomDatabase {
    private static UserInformationDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserInformationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserInformationDatabase.class, "user_information_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private PopulateDbAsyncTask(UserInformationDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            UserEntity userEntity = new UserEntity("Jason", "Petri");
            userDao.insert(userEntity);
            return null;
        }
    }
}
