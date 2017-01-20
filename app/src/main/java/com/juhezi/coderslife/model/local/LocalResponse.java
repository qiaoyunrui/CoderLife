package com.juhezi.coderslife.model.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.tools.Action1;
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
                ContentValues values = new ContentValues();
                values.put(DBContract.LOGCONTENT_TIME, logContent.getTime());
                values.put(DBContract.LOGCONTENT_CONTENT, logContent.getContent());
                values.put(DBContract.LOGCONTENT_STATE, logContent.getState());
                values.put(DBContract.LOGCONTENT_TYPE, logContent.getContentType());
                if (database.insert(DBContract.LOG_CONTENT_TABLE_NAME, null, values) != -1) {
                    action.onAction(Config.RESULT_CODE_OK);
                } else {
                    action.onAction(Config.RESULT_CODE_ERROR);
                }
                database.close();
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

    private static List<LogContent> cursor2LogContent(Cursor cursor) {
        List<LogContent> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(new LogContent(cursor.getString(2),
                    cursor.getInt(3),
                    Boolean.parseBoolean(cursor.getString(4)),
                    cursor.getString(1)));
        }
        cursor.close();
        return list;
    }

}
