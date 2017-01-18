package com.juhezi.coderslife.model.remote;

import android.content.Context;

import com.juhezi.coderslife.model.Response;

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

}
