package com.juhezi.coderslife.model;

import android.content.Context;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.model.local.LocalResponse;
import com.juhezi.coderslife.model.remote.RemoteResponse;
import com.juhezi.coderslife.tools.Action1;

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

    @Override
    public void addLogContent(LogContent logContent, Action1<Integer> result) {
        /**
         * 1. 在本地数据库添加
         * 2. 执行后续操作(UI上显示添加成功Toast)
         * 3. Service中发送服务器请求
         */
        mLocalResponse.addLogContent(logContent, result);   //暂时不考虑发送服务器请求操作
    }
}
