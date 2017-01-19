package com.juhezi.coderslife.model.remote;

import android.content.Context;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class RemoteResponse implements Response {
    private static String TAG = "RemoteResponse";

    private Context context;

    private static RemoteResponse sInstance;

    private RemoteResponse(Context context) {
        this.context = context;
    }

    public static RemoteResponse getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RemoteResponse(context);
        }
        return sInstance;
    }

    @Override
    public void addLogContent(LogContent logContent, Action1<Integer> result) {
        result.onAction(Config.RESULT_CODE_ERROR);
    }
}