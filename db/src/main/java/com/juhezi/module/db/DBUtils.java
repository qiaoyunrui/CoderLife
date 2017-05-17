package com.juhezi.module.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juhezi.module.db.domain.DailyTask;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/16.
 */

public class DBUtils extends SQLiteOpenHelper {

    private static String TAG = DBUtils.class.getSimpleName();

    private static final String DB_NAME_KEY = "db.name";
    private static final String DB_VERSION_KEY = "db.version";
    private static final String DB_CONFIG_PROPERTIES = "db.properties";

    private static String dbName;
    private static int dbVersion;
    private Context mContext;

    static {
        try {
            ResourceBundle resourceBundle =
                    ResourceBundle.getBundle(DB_CONFIG_PROPERTIES, Locale.ENGLISH);
            dbName = resourceBundle.getString(DB_NAME_KEY);
            dbVersion = Integer.parseInt(resourceBundle.getString(DB_VERSION_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DBUtils sInstance;

    public static DBUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBUtils(context, dbName, null, dbVersion);
        }
        return sInstance;
    }

    private DBUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行建表语句
        db.execSQL(DailyTask.CREATE_SQL);   //Create the daily_task
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库升级，进行数据迁移
    }
}
