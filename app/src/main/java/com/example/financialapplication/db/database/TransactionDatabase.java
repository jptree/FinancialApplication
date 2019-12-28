package com.example.financialapplication.db.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.financialapplication.db.DateConverters;
import com.example.financialapplication.db.dao.TransactionDao;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TransactionEntity.class}, version = 1, exportSchema = false)
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
            transactionDao.insert(new TransactionEntity(new Date(2019, 2, 19), (float) 12.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot", 1234));
            transactionDao.insert(new TransactionEntity(new Date(2019, 2, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot1", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2016, 2, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot2", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 1, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot3", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 7, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot4", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 4, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot5", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 2, 11), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot6", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2018, 5, 12), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot7", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 1, 22), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot8", 12341));
            transactionDao.insert(new TransactionEntity(new Date(2019, 9, 11), (float) 112.34, "expense", "Home/Utilities", "Home Improvement", "Home Depot9", 12341));

            return null;
        }
    }
}