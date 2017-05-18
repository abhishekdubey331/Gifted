package com.gifted.app.giftededucation.utils;

import com.gifted.app.giftededucation.Global;
import com.greendao.db.DaoSession;
import com.greendao.db.UserResponses;
import com.greendao.db.UserResponsesDao;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

public class GetAllCounts {

    public int getAttempted() {
        int count = 0;
        for (int number = 0; number < Pref.get(Config.KEY_LAST_QUE, 30); number++) {
            UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                    .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();
            if (userResponses.getUser_response().length() > 0) {
                count++;
            }
        }
        return count;
    }


    public int getUnattemptedCount() {
        int count = 0;
        for (int number = 0; number < Pref.get(Config.KEY_LAST_QUE, 30); number++) {
            UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                    .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();
            if (userResponses.getUser_response().contentEquals("")) {
                count++;
            }
        }
        return count;
    }

    public int getCorrectCount() {
        int count = 0;
        for (int number = 0; number < Pref.get(Config.KEY_LAST_QUE, 30); number++) {
            UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                    .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();
            if (userResponses.getUser_response().contentEquals(userResponses.getAnswer())) {
                count++;
            }
        }
        return count;
    }

    public int getIncorrectCount() {

        int count = 0;
        for (int number = 0; number < Pref.get(Config.KEY_LAST_QUE, 30); number++) {
            UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                    .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();
            if (!userResponses.getUser_response().contentEquals(userResponses.getAnswer())) {
                count++;
            }
        }
        return count;
    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


}
