package com.juhezi.module.db.dao;

import android.database.SQLException;

import com.juhezi.module.db.domain.Task;

import java.util.List;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public interface TaskDao {

    void add(Task task) throws SQLException;

    void update(Task task) throws SQLException;

    void delete(String id) throws SQLException;

    Task getDailyTaskById(String id) throws SQLException;

    List<Task> findAll() throws SQLException;

}
