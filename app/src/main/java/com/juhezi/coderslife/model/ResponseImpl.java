package com.juhezi.coderslife.model;

import android.content.Context;

import com.juhezi.coderslife.model.local.LocalResponse;
import com.juhezi.coderslife.model.remote.RemoteResponse;

/**
 * Created by qiao1 on 2017/1/18.
 */

public class ResponseImpl implements Response {
    private static String TAG = "ResponseImpl";

    private Context mContext;
    private Response mLocalResponse;
    private Response mRemoteResponse;

    private ResponseImpl(Context context) {
        this.mContext = context;
        mLocalResponse = LocalResponse.getInstance(mContext);
        mRemoteResponse = RemoteResponse.getInstance(mContext);
    }

    private static ResponseImpl sInstance;

    public static ResponseImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ResponseImpl(context);
        }
        return sInstance;
    }

}
