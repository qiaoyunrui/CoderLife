package com.juhezi.module.db.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.juhezi.module.db.CursorHandler;
import com.juhezi.module.db.DBService;
import com.juhezi.module.db.dao.TaskDao;
import com.juhezi.module.db.domain.Task;

import java.util.ArrayList;
import java.util.Date;
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
        String sql = "DELETE FROM " + Task.TABLE_NAME + " WHERE " + Task.ID + " = '?'";
        mDBService.update(sql, id);
    }

    @Override
    public Task getDailyTaskById(final String id) throws SQLException {
        String sql = "SELECT " + Task.CONTENT + ", " + Task.CREATE_DATE + ", " +
                Task.LIMIT_DATE + ", " + Task.STATE + " FROM " + Task.TABLE_NAME +
                " WHERE " + Task.ID + " = '?'";
        return (Task) mDBService.query(sql, new CursorHandler() {
            @Override
            public Object doHandler(Cursor cursor) throws SQLException {
                Task task = null;
                if (cursor.moveToNext()) {
                    task = new Task();
                    task.setId(id)
                            .setContent(cursor.getString(1))
                            .setCreateDate(new Date(cursor.getLong(2)))
                            .setLimitDate(new Date(cursor.getLong(3)))
                            .setState(cursor.getInt(4) == 1);
                }
                return task;
            }
        });
    }

    //不安全，需要优化
    @Override
    public List<Task> findAll() throws SQLException {
        String sql = "SELECT * FROM " + Task.TABLE_NAME;
        return (List<Task>) mDBService.query(sql, new CursorHandler() {
            @Override
            public Object doHandler(Cursor cursor) throws SQLException {
                List<Task> list = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Task task = new Task();
                    task.setId(cursor.getString(1))
                            .setContent(cursor.getString(2))
                            .setCreateDate(new Date(cursor.getLong(3)))
                            .setLimitDate(new Date(cursor.getLong(4)))
                            .setState(cursor.getInt(5) == 1);
                    list.add(task);
                }
                return list;
            }
        });
    }
}
