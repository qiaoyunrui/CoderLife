package com.juhezi.coderslife.model.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juhezi.coderslife.tools.Config;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static String TAG = "DBHelper";

    private static DBHelper sInstance;

    public static DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context, Config.LOCAL_DB_NAME, null, Config.LOCAL_DB_VERSION);
        }
        return sInstance;
    }

    private Context mContext;

    private static final String CREATE_CONTENT_SQL = "create table " +
            DBContract.LOG_CONTENT_TABLE_NAME + " (" +
            DBContract.LOGCONTENT_ID + " text primary key, " +
            DBContract.LOGCONTENT_TIME + " text, " +
            DBContract.LOGCONTENT_CONTENT + " text, " +
            DBContract.LOGCONTENT_TYPE + " integer, " +
            DBContract.LOGCONTENT_STATE + " integer)";    //建表语句

    private static final String CREATE_LOG_DRAFT_SQL = "create table " +
            DBContract.LOG_DRAFT_TABLE_NAME + " (" +
            DBContract.LOGCONTENT_ID + " text primary key, " +
            DBContract.LOGCONTENT_TIME + " text, " +
            DBContract.LOGCONTENT_CONTENT + " text, " +
            DBContract.LOGCONTENT_TYPE + " integer, " +
            DBContract.LOGCONTENT_STATE + " integer, " +
            DBContract.LOG_DRAFT_DRAFT_TYPE + "integer)";

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
