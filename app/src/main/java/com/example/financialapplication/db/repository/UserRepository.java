package com.example.financialapplication.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.financialapplication.db.dao.UserDao;
import com.example.financialapplication.db.database.UserInformationDatabase;
import com.example.financialapplication.db.entity.UserEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserEntity>> allUsers;
    private List<UserEntity> specifiedUsers;

    public UserRepository(Application application) {
        UserInformationDatabase database = UserInformationDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public void update(UserEntity userEntity) {
        new UpdateUserEntityAsyncTask(userDao).execute(userEntity);
    }

    public void delete(UserEntity userEntity) {
        new DeleteUserEntityAsyncTask(userDao).execute(userEntity);
    }

    public void deleteAllUsers() {
        new DeleteAllUserEntityAsyncTask(userDao).execute();
    }

    public void deleteUserById(int id) {
        new DeleteUserEntityByIdAsyncTask(userDao, id).execute();
    }

    public void insert(UserEntity userEntity) {
        new InsertUserEntityAsyncTask(userDao).execute(userEntity);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        allUsers = userDao.getAllUsers();
        return allUsers;
    }


    private static class InsertUserEntityAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private InsertUserEntityAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.insert(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserEntityAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private DeleteUserEntityAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.delete(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserEntityByIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private int mImageId;

        private DeleteUserEntityByIdAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        private DeleteUserEntityByIdAsyncTask(UserDao userDao, int mImageId) {
            this.userDao = userDao;
            this.mImageId = mImageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    private static class UpdateUserEntityAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private UpdateUserEntityAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.update(userEntities[0]);
            return null;
        }
    }

    private static class DeleteAllUserEntityAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUserEntityAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}