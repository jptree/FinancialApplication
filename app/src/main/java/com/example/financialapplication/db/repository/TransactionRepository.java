package com.example.financialapplication.db.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.financialapplication.db.dao.TransactionDao;
import com.example.financialapplication.db.database.TransactionDatabase;
//import com.example.financialapplication.db.entity.SubcategoryEntity;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TransactionRepository {

    private TransactionDao transactionDao;
    private LiveData<List<TransactionEntity>> allTransactions;
    private List<TransactionEntity> specifiedTransactions;

    public TransactionRepository(Application application) {
        TransactionDatabase database = TransactionDatabase.getInstance(application);
        transactionDao = database.transactionDao();
    }

    public void update(TransactionEntity transactionEntity) {
        new UpdateTransactionEntityAsyncTask(transactionDao).execute(transactionEntity);
    }

    public void delete(TransactionEntity transactionEntity) {
        new DeleteTransactionEntityAsyncTask(transactionDao).execute(transactionEntity);
    }

    public void deleteAllTransactions() {
        new DeleteAllTransactionEntityAsyncTask(transactionDao).execute();
    }

    public void deleteTransactionById(int id) {
        new DeleteTransactionEntityByIdAsyncTask(transactionDao, id).execute();
    }

    public void insert(TransactionEntity transactionEntity) {
        new InsertTransactionEntityAsyncTask(transactionDao).execute(transactionEntity);
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        allTransactions = transactionDao.getAllTransactions();
        return allTransactions;
    }

    public List<TransactionEntity> getSpecifiedTransactions(float minValue, float maxValue) {
        try {
            return new GetSpecificTransactionsAsyncTask(transactionDao).execute(minValue, maxValue).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class GetSpecificTransactionsAsyncTask extends AsyncTask<Float, Void, List<TransactionEntity>> {
        private TransactionDao transactionDao;

        private GetSpecificTransactionsAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }
        @Override
        protected List<TransactionEntity> doInBackground(Float... floats) {
            return transactionDao.getSpecificTransactions(floats[0], floats[1]);
        }
    }

    public List<TransactionDao.SubcategorySum> getSubcategorySum(float minValue, float maxValue) {
        try {
            return new GetSubcategorySumAsyncTask(transactionDao).execute(minValue, maxValue).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class GetSubcategorySumAsyncTask extends AsyncTask<Float, Void, List<TransactionDao.SubcategorySum>> {
        private TransactionDao transactionDao;

        private GetSubcategorySumAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }
        @Override
        protected List<TransactionDao.SubcategorySum> doInBackground(Float... floats) {
            return transactionDao.getSubcategorySum(floats[0], floats[1]);
        }
    }

    private static class InsertTransactionEntityAsyncTask extends AsyncTask<TransactionEntity, Void, Void> {
        private TransactionDao transactionDao;

        private InsertTransactionEntityAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactionEntities) {
            transactionDao.insert(transactionEntities[0]);
            return null;
        }
    }

    private static class DeleteTransactionEntityAsyncTask extends AsyncTask<TransactionEntity, Void, Void> {
        private TransactionDao transactionDao;

        private DeleteTransactionEntityAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactionEntities) {
            transactionDao.delete(transactionEntities[0]);
            return null;
        }
    }

    private static class DeleteTransactionEntityByIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionDao transactionDao;
        private int mImageId;

        private DeleteTransactionEntityByIdAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        private DeleteTransactionEntityByIdAsyncTask(TransactionDao transactionDao, int mImageId) {
            this.transactionDao = transactionDao;
            this.mImageId = mImageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    private static class UpdateTransactionEntityAsyncTask extends AsyncTask<TransactionEntity, Void, Void> {
        private TransactionDao transactionDao;

        private UpdateTransactionEntityAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactionEntities) {
            transactionDao.update(transactionEntities[0]);
            return null;
        }
    }

    private static class DeleteAllTransactionEntityAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionDao transactionDao;

        private DeleteAllTransactionEntityAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDao.deleteAllTransactions();
            return null;
        }
    }
}
