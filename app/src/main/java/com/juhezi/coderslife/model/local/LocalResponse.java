package com.juhezi.coderslife.model.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juhezi.coderslife.model.Response;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class LocalResponse implements Response {
    private static String TAG = "LocalResponse";

    private Context mContext;

    private static LocalResponse sInstance;
    private DBService dbService;
    private SQLiteDatabase database;

    public static LocalResponse getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalResponse(context);
        }
        return sInstance;
    }

    private LocalResponse(Context context) {
        this.mContext = context;
        dbService = DBService.getInstance(context);
        database = dbService.getWritableDatabase();
    }

}
