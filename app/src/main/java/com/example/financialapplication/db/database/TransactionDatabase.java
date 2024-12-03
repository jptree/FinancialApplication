package com.example.financialapplication.db.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.financialapplication.db.DateConverters;
import com.example.financialapplication.db.dao.TransactionDao;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TransactionEntity.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class TransactionDatabase extends RoomDatabase {
    private static TransactionDatabase instance;
    public abstract TransactionDao transactionDao();

    public static synchronized TransactionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionDatabase.class, "transaction_database")
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
        private TransactionDao transactionDao;

        private PopulateDbAsyncTask(TransactionDatabase db) {
            transactionDao = db.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019, 2, 30);
            transactionDao.insert(new TransactionEntity(calendar, (float) 12.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot", 1234));

            return null;
        }
    }
}