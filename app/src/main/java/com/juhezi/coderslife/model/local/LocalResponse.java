package com.juhezi.coderslife.model.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class LocalResponse implements Response {
    private static String TAG = "LocalResponse";

    private Context mContext;
    private Thread thread = new Thread();

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
}
