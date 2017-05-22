package com.juhezi.module.db.dao;

import android.database.SQLException;

import com.juhezi.module.db.domain.DailyTask;

import java.util.List;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public interface DailyTaskDao {

    void add(DailyTask dailyTask) throws SQLException;

    void update(DailyTask dailyTask) throws SQLException;

    void delete(String id) throws SQLException;

    DailyTask getDailyTaskById(String id) throws SQLException;

    List<DailyTask> findAll() throws SQLException;

}
