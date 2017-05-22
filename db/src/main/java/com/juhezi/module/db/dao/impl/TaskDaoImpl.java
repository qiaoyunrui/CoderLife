package com.juhezi.module.db.dao.impl;

import android.content.Context;
import android.database.SQLException;

import com.juhezi.module.db.DBService;
import com.juhezi.module.db.dao.TaskDao;
import com.juhezi.module.db.domain.Task;

import java.util.List;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public class TaskDaoImpl implements TaskDao {

    public DBService mDBService;

    public TaskDaoImpl(Context context) {
        mDBService = DBService.getInstance(context);
    }

    @Override
    public void add(Task task) throws SQLException {
        String sql = "INSERT INTO " + Task.TABLE_NAME +
                " VALUES (" +
                "'?', '?', ?, ?, ? )";
        mDBService.update(sql,
                task.getId(),
                task.getContent(),
                task.getCreateDate(),
                task.getLimitDate(),
                task.getState());
    }

    @Override
    public void update(Task task) throws SQLException {
        String sql = "UPDATE " + Task.TABLE_NAME +
                " SET " +
                Task.CONTENT + " = '?', " +
                Task.CREATE_DATE + " = ?, " +
                Task.LIMIT_DATE + " = ?, " +
                Task.STATE + " = ? WHERE " +
                Task.ID + " = '?'";
        mDBService.update(sql,
                task.getContent(),
                task.getCreateDate(),
                task.getLimitDate(),
                task.getState(),
                task.getId());

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public Task getDailyTaskById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Task> findAll() throws SQLException {
        return null;
    }
}
