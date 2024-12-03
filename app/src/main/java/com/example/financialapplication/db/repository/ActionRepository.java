package com.example.financialapplication.db.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.financialapplication.db.dao.ActionDao;
import com.example.financialapplication.db.dao.TransactionDao;
import com.example.financialapplication.db.database.ActionDatabase;
import com.example.financialapplication.db.entity.ActionEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ActionRepository {
    private ActionDao actionDao;
    private LiveData<List<ActionEntity>> allCompletedActions;
    private LiveData<List<ActionEntity>> allIncompleteActions;

    public ActionRepository(Application application) {
        ActionDatabase database = ActionDatabase.getInstance(application);
        actionDao = database.actionDao();
        allCompletedActions = actionDao.getAllCompletedActions();
        allIncompleteActions = actionDao.getAllIncompleteActions();
    }

    public void update(ActionEntity actionEntity) {
        new UpdateActionEntityAsyncTask(actionDao).execute(actionEntity);
    }

    public void delete(ActionEntity actionEntity) {
        new DeleteActionEntityAsyncTask(actionDao).execute(actionEntity);
    }

    public void deleteAllActions() {
        new DeleteAllActionEntityAsyncTask(actionDao).execute();
    }

    public void deleteActionById(int id) {
        new DeleteActionEntityByIdAsyncTask(actionDao, id).execute();
    }

    public void insert(ActionEntity actionEntity) {
        new InsertActionEntityAsyncTask(actionDao).execute(actionEntity);
    }

    public LiveData<List<ActionEntity>> getAllCompletedActions() {
        return allCompletedActions;
    }

    public LiveData<List<ActionEntity>> getAllIncompleteActions() {
        return allIncompleteActions;
    }



    private static class InsertActionEntityAsyncTask extends AsyncTask<ActionEntity, Void, Void> {
        private ActionDao actionDao;

        private InsertActionEntityAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(ActionEntity... actionEntities) {
            actionDao.insert(actionEntities[0]);
            return null;
        }
    }

    private static class DeleteActionEntityAsyncTask extends AsyncTask<ActionEntity, Void, Void> {
        private ActionDao actionDao;

        private DeleteActionEntityAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(ActionEntity... actionEntities) {
            actionDao.delete(actionEntities[0]);
            return null;
        }
    }

    private static class DeleteActionEntityByIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private ActionDao actionDao;
        private int mImageId;

        private DeleteActionEntityByIdAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        private DeleteActionEntityByIdAsyncTask(ActionDao actionDao, int mImageId) {
            this.actionDao = actionDao;
            this.mImageId = mImageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: " + "deleting...");
            actionDao.deleteActionById(mImageId);
            return null;
        }
    }

    private static class UpdateActionEntityAsyncTask extends AsyncTask<ActionEntity, Void, Void> {
        private ActionDao actionDao;

        private UpdateActionEntityAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(ActionEntity... actionEntities) {
            actionDao.update(actionEntities[0]);
            return null;
        }
    }

    private static class DeleteAllActionEntityAsyncTask extends AsyncTask<Void, Void, Void> {
        private ActionDao actionDao;

        private DeleteAllActionEntityAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            actionDao.deleteAllActions();
            return null;
        }
    }
}
