package com.example.financialapplication.db.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.financialapplication.db.DateConverters;
import com.example.financialapplication.db.dao.ActionDao;
import com.example.financialapplication.db.dao.LifeEventDao;
import com.example.financialapplication.db.entity.ActionEntity;
import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActionEntity.class}, version = 3, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class ActionDatabase extends RoomDatabase {
    private static ActionDatabase instance;

    public abstract ActionDao actionDao();

    public static synchronized ActionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ActionDatabase.class, "action_database")
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
        private ActionDao actionDao;
        private PopulateDbAsyncTask(ActionDatabase db) {
            actionDao = db.actionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String directions = "Let's implement this later...%/%Somewhere you should be able to write in this..."; // Separated by %/%
            Calendar calendar = Calendar.getInstance();

            ActionEntity actionEntity = new ActionEntity("Specify First Name", "Let's get acquainted!", directions, true);
            actionEntity.setDoASAP("Do this as soon as possible!");
            actionEntity.setDoByDate(calendar);
//            actionEntity.setCompleted(false);
            actionDao.insert(actionEntity);

            actionEntity = new ActionEntity("Specify Age", "I need to know a little about you.", directions, true);
            actionEntity.setDoASAP("Do this as soon as possible!");
            actionEntity.setDoByDate(calendar);
//            actionEntity.setCompleted(false);
            actionDao.insert(actionEntity);

            actionEntity = new ActionEntity("Add Transaction Data", "I want to help plan your life.", directions, true);
            actionEntity.setDoASAP("Do this as soon as possible!");
//            actionEntity.setCompleted(false);
            actionEntity.setDoByDate(calendar);
            actionDao.insert(actionEntity);

            actionEntity = new ActionEntity("Specify Income Range", "This will help customize your experience.", directions, true);
            actionEntity.setDoASAP("Do this as soon as possible!");
//            actionEntity.setCompleted(false);
            actionEntity.setDoByDate(calendar);
            actionEntity.setInputDataType("String");
            actionDao.insert(actionEntity);

            actionEntity = new ActionEntity("Download the App!", "Hopefully this will prove worth it!", directions, false);
            actionEntity.setDoByDate(calendar);
//            actionEntity.setCompleted(true);
            actionDao.insert(actionEntity);
            return null;
        }
    }
}
