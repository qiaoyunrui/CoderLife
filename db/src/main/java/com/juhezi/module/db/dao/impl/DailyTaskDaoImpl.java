package com.juhezi.module.db.dao.impl;

import android.content.Context;
import android.database.SQLException;

import com.juhezi.module.db.DBService;
import com.juhezi.module.db.dao.DailyTaskDao;
import com.juhezi.module.db.domain.DailyTask;

import java.util.List;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public class DailyTaskDaoImpl implements DailyTaskDao {

    public DBService mDBService;

    public DailyTaskDaoImpl(Context context) {
        mDBService = DBService.getInstance(context);
    }

    @Override
    public void add(DailyTask dailyTask) throws SQLException {
        String sql = "INSERT INTO " + DailyTask.TABLE_NAME +
                " VALUES (" +
                "'?', '?', '?', ?, ?, ? )";
        mDBService.update(sql,
                dailyTask.getId(),
                dailyTask.getTitle(),
                dailyTask.getContent(),
                dailyTask.getCreateDate(),
                dailyTask.getLimitDate(),
                dailyTask.getState());
    }

    @Override
    public void update(DailyTask dailyTask) throws SQLException {
        String sql = "UPDATE " + DailyTask.TABLE_NAME +
                " SET " +
                DailyTask.TITLE + " = '?', " +
                DailyTask.CONTENT + " = '?', " +
                DailyTask.CREATE_DATE + " = ?, " +
                DailyTask.LIMIT_DATE + " = ?, " +
                DailyTask.STATE + " = ? WHERE " +
                DailyTask.ID + " = '?'";
        mDBService.update(sql,
                dailyTask.getTitle(),
                dailyTask.getContent(),
                dailyTask.getCreateDate(),
                dailyTask.getLimitDate(),
                dailyTask.getState(),
                dailyTask.getId());

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public DailyTask getDailyTaskById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<DailyTask> findAll() throws SQLException {
        return null;
    }
}
