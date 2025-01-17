package com.example.financialapplication.db.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.financialapplication.db.DateConverters;
import com.example.financialapplication.db.dao.LifeEventDao;
import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LifeEventEntity.class}, version = 3, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class LifeEventDatabase extends RoomDatabase {
    private static LifeEventDatabase instance;

    public abstract LifeEventDao lifeEventDao();

    public static synchronized LifeEventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LifeEventDatabase.class, "life_event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LifeEventDao lifeEventDao;
        private PopulateDbAsyncTask(LifeEventDatabase db) {
            lifeEventDao = db.lifeEventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019, 4, 10);
            lifeEventDao.insert(new LifeEventEntity("Purchase House!", 2131165316, calendar));
            return null;
        }
    }
}
