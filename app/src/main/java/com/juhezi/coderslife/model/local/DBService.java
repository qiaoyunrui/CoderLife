package com.juhezi.coderslife.model.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.juhezi.coderslife.tools.Config;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class DBService extends SQLiteOpenHelper {
    private static String TAG = "DBService";

    private static DBService sInstance;

    public static DBService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBService(context, Config.LOCAL_DB_NAME, null, Config.LOCAL_DB_VERSION);
        }
        return sInstance;
    }

    private Context mContext;

    private static final String CREATE_CONTENT_SQL = "create table LogContent (" +
            "id integer primary key autoincrement, " +
            "time text, " +
            "content text, " +
            "type integer, " +
            "state integer)";    //建表语句

    private DBService(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTENT_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
