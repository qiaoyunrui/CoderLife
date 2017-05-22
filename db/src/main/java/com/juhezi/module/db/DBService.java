package com.juhezi.module.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 提供最基本的数据库操作 update、query
 * <p>
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public class DBService {

    private static DBService sInstance;
    private DBHelper mDBHelper = null;

    public static DBService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBService(context);
        }
        return sInstance;
    }

    private DBService(Context context) {
        mDBHelper = DBHelper.getInstance(context);
    }

    public int update(String sql, Object... args) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        try {
            db.execSQL(sql, args);
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return -1;
    }

    public Object query(String sql, CursorHandler handler, String... args) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(sql, args);
            if (handler != null) {
                handler.doHandler(cursor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return null;
    }

}
