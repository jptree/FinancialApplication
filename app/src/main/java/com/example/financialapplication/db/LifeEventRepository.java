package com.example.financialapplication.db;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.financialapplication.db.dao.LifeEventDao;
import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LifeEventRepository {
    private LifeEventDao lifeEventDao;
    private LiveData<List<LifeEventEntity>> allLifeEvents;
    private List<LifeEventEntity> allLifeEventsStatic;

    public LifeEventRepository(Application application) {
        LifeEventDatabase database = LifeEventDatabase.getInstance(application);
        lifeEventDao = database.lifeEventDao();
        allLifeEvents = lifeEventDao.getAllLifeEvents();
//        allLifeEventsStatic = lifeEventDao.getAllLifeEventsStatic();
    }

    public void update(LifeEventEntity lifeEventEntity) {
        new UpdateLifeEventEntityAsyncTask(lifeEventDao).execute(lifeEventEntity);
    }

    public void delete(LifeEventEntity lifeEventEntity) {
        new DeleteLifeEventEntityAsyncTask(lifeEventDao).execute(lifeEventEntity);
    }

    public void deleteAllLifeEvents() {
        new DeleteAllLifeEventEntityAsyncTask(lifeEventDao).execute();
    }

    public void deleteLifeEventById(int id) {
        new DeleteLifeEventEntityByIdAsyncTask(lifeEventDao, id).execute();
    }

    public void insert(LifeEventEntity lifeEventEntity) {
        new InsertLifeEventEntityAsyncTask(lifeEventDao).execute(lifeEventEntity);
    }

    public LiveData<List<LifeEventEntity>> getAllLifeEvents() {
        return allLifeEvents;
    }

    public List<LifeEventEntity> getAllLifeEventsStatic() {
        return allLifeEventsStatic;
    }

    private static class InsertLifeEventEntityAsyncTask extends AsyncTask<LifeEventEntity, Void, Void> {
        private LifeEventDao lifeEventDao;

        private InsertLifeEventEntityAsyncTask(LifeEventDao lifeEventDao) {
            this.lifeEventDao = lifeEventDao;
        }

        @Override
        protected Void doInBackground(LifeEventEntity... lifeEventEntities) {
            lifeEventDao.insert(lifeEventEntities[0]);
            return null;
        }
    }

    private static class DeleteLifeEventEntityAsyncTask extends AsyncTask<LifeEventEntity, Void, Void> {
        private LifeEventDao lifeEventDao;

        private DeleteLifeEventEntityAsyncTask(LifeEventDao lifeEventDao) {
            this.lifeEventDao = lifeEventDao;
        }

        @Override
        protected Void doInBackground(LifeEventEntity... lifeEventEntities) {
            lifeEventDao.delete(lifeEventEntities[0]);
            return null;
        }
    }

    private static class DeleteLifeEventEntityByIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private LifeEventDao lifeEventDao;
        private int mImageId;

        private DeleteLifeEventEntityByIdAsyncTask(LifeEventDao lifeEventDao) {
            this.lifeEventDao = lifeEventDao;
        }

        private DeleteLifeEventEntityByIdAsyncTask(LifeEventDao lifeEventDao, int mImageId) {
            this.lifeEventDao = lifeEventDao;
            this.mImageId = mImageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: " + "deleting...");
            lifeEventDao.deleteLifeEventById(mImageId);
            return null;
        }
    }

    private static class UpdateLifeEventEntityAsyncTask extends AsyncTask<LifeEventEntity, Void, Void> {
        private LifeEventDao lifeEventDao;

        private UpdateLifeEventEntityAsyncTask(LifeEventDao lifeEventDao) {
            this.lifeEventDao = lifeEventDao;
        }

        @Override
        protected Void doInBackground(LifeEventEntity... lifeEventEntities) {
            lifeEventDao.update(lifeEventEntities[0]);
            return null;
        }
    }

    private static class DeleteAllLifeEventEntityAsyncTask extends AsyncTask<Void, Void, Void> {
        private LifeEventDao lifeEventDao;

        private DeleteAllLifeEventEntityAsyncTask(LifeEventDao lifeEventDao) {
            this.lifeEventDao = lifeEventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            lifeEventDao.deleteAllLifeEvents();
            return null;
        }
    }
}
