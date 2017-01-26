package com.juhezi.coderslife.model.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.BoolUtil;
import com.juhezi.coderslife.tools.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class LocalResponse implements Response {
    private static String TAG = "LocalResponse";

    private Context mContext;

    private static LocalResponse sInstance;
    private DBHelper dbHelper;

    public static LocalResponse getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalResponse(context);
        }
        return sInstance;
    }

    private LocalResponse(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getInstance(context);
    }

    /**
     * @param logContent
     * @param action
     */
    @Override
    public void addLogContent(final LogContent logContent, final Action1<Integer> action) {
        new Thread() {
            @Override
            public void run() {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                try {
                    if (database.insert(DBContract.LOG_CONTENT_TABLE_NAME, null, createContentValues(logContent)) != -1) {
                        action.onAction(Config.RESULT_CODE_OK);
                    } else {
                        action.onAction(Config.RESULT_CODE_ERROR);
                    }
                    database.close();
                } catch (Exception e) {
                    action.onAction(Config.RESULT_CODE_ERROR);
                }

            }
        }.start();
    }

    @Override
    public void getTodayAllLogs(final String time, final Action1<List<LogContent>> action) {
        new Thread() {
            @Override
            public void run() {
                String sql = "SELECT * FROM " +
                        DBContract.LOG_CONTENT_TABLE_NAME +
                        " WHERE " + DBContract.LOGCONTENT_TIME +
                        " = '" + time + "'";
                List<LogContent> list;
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.rawQuery(sql, null);
                list = cursor2LogContent(cursor);
                action.onAction(list);
            }
        }.start();
    }

    @Override
    public void getAllLogs(final Action1<List<LogContent>> action) {
        new Thread() {
            @Override
            public void run() {
                String sql = "SELECT * FROM " +
                        DBContract.LOG_CONTENT_TABLE_NAME;
                try {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    Cursor cursor = database.rawQuery(sql, null);
                    List<LogContent> list = cursor2LogContent(cursor);
                    action.onAction(list);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    action.onAction(new ArrayList<LogContent>());
                }
            }
        }.start();
    }

    @Override
    public void updateLog(final LogContent logContent, final Action1<Integer> action1) {
        if (logContent == null)
            return;
        new Thread() {
            @Override
            public void run() {
                try {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    String sql = "UPDATE " + DBContract.LOG_CONTENT_TABLE_NAME
                            + " SET " + DBContract.LOGCONTENT_CONTENT
                            + " = '" + logContent.getContent()
                            + "'," + DBContract.LOGCONTENT_TIME
                            + " = '" + logContent.getTime()
                            + "'," + DBContract.LOGCONTENT_TYPE
                            + " = " + logContent.getContentType()
                            + "," + DBContract.LOGCONTENT_STATE
                            + " = " + BoolUtil.bool2Int(logContent.getState())
                            + " WHERE " + DBContract.LOGCONTENT_ID + " = '"
                            + logContent.getId() + "'";
                    database.execSQL(sql);
                    action1.onAction(Config.RESULT_CODE_OK);
                    database.close();
                } catch (Exception ex) {
                    action1.onAction(Config.RESULT_CODE_ERROR);
                }

            }
        }.start();
    }

    @Override
    public void removeLog(final String id, final Action1<Integer> action) {
        new Thread() {
            @Override
            public void run() {
                String sql = "DELETE FROM " + DBContract.LOG_CONTENT_TABLE_NAME
                        + " WHERE " + DBContract.LOGCONTENT_ID
                        + " = '" + id
                        + "'";
                try {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL(sql);
                    db.close();
                    action.onAction(Config.RESULT_CODE_OK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    action.onAction(Config.RESULT_CODE_ERROR);
                }
            }
        }.start();
    }

    @Override
    public void deleteDayAllLogs(final String time, final Action1<Integer> action) {
        new Thread() {
            @Override
            public void run() {
                String sql = "DELETE FROM " + DBContract.LOG_CONTENT_TABLE_NAME
                        + " WHERE " + DBContract.LOGCONTENT_TIME
                        + " = '" + time
                        + "'";
                try {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL(sql);
                    db.close();
                    action.onAction(Config.RESULT_CODE_OK);
                } catch (Exception ex) {
                    action.onAction(Config.RESULT_CODE_ERROR);
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    private static List<LogContent> cursor2LogContent(Cursor cursor) {
        List<LogContent> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(new LogContent(
                    cursor.getString(0),
                    cursor.getString(2),
                    cursor.getInt(3),
                    BoolUtil.int2Bool(cursor.getInt(4)),
                    cursor.getString(1)));
        }
        cursor.close();
        return list;
    }

    /**
     * 装载ContentValues
     *
     * @param logContent
     * @return
     */
    private ContentValues createContentValues(LogContent logContent) {
        ContentValues values = new ContentValues();
        values.put(DBContract.LOGCONTENT_TIME, logContent.getTime());
        values.put(DBContract.LOGCONTENT_CONTENT, logContent.getContent());
        values.put(DBContract.LOGCONTENT_STATE, BoolUtil.bool2Int(logContent.getState()));
        values.put(DBContract.LOGCONTENT_TYPE, logContent.getContentType());
        values.put(DBContract.LOGCONTENT_ID, logContent.getId());
        return values;
    }

}
