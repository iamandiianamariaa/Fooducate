package com.example.fooducate.db;

import android.app.Application;
import android.os.AsyncTask;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        Database database = Database.getDatabase(application);
        userDao = database.userDao();
    }

    public void insert(UserWithScans userWithScans) {
        new insertAsync(userDao).execute(userWithScans);
    }

    public void delete(String model) {
        new DeleteCourseAsyncTask(userDao).execute(model);
    }

    private class insertAsync extends AsyncTask<UserWithScans, Void, Void> {
        private UserDao userDaoAsync;

        insertAsync(UserDao userDao) {
            userDaoAsync = userDao;
        }

        @Override
        protected Void doInBackground(UserWithScans... userWithScans) {
            if(!userDao.isDataExist(userWithScans[0].user.getFirebaseUserId()))
            {
                userDaoAsync.insertUser(userWithScans[0].user);
            }

            for (Scan scan : userWithScans[0].scans) {
                scan.setId_fkcourse(userWithScans[0].user.getFirebaseUserId());
            }
            userDaoAsync.insertScan(userWithScans[0].scans);
            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<String, Void, Void> {
        private UserDao dao;

        private DeleteCourseAsyncTask(UserDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... models) {
            dao.deleteByScanBarcode(models[0]);
            return null;
        }
    }

}
