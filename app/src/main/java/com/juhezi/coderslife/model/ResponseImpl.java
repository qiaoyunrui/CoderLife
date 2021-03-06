package com.juhezi.coderslife.model;

import android.content.Context;
import android.util.Log;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.model.local.LocalResponse;
import com.juhezi.coderslife.model.remote.RemoteResponse;
import com.juhezi.coderslife.tools.Action1;

import java.util.List;

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

    @Override
    public void getTodayAllLogs(String time, Action1<List<LogContent>> action) {
        mLocalResponse.getTodayAllLogs(time, action);
    }

    @Override
    public void updateLog(LogContent logContent, Action1<Integer> action1) {
        mLocalResponse.updateLog(logContent, action1);
    }

    @Override
    public void removeLog(String id, Action1<Integer> action) {
        mLocalResponse.removeLog(id, action);
    }

    @Override
    public void deleteDayAllLogs(String time, Action1<Integer> action) {
        mLocalResponse.deleteDayAllLogs(time, action);
    }

    @Override
    public void getAllLogs(Action1<List<LogContent>> action) {
        mLocalResponse.getAllLogs(action);
    }

    @Override
    public void getPartLogs(int start, int offset, Action1<List<LogContent>> action) {
        mLocalResponse.getPartLogs(start, offset, action);
    }

    @Override
    public void addDraft(LogDraftBean draft, Action1<Integer> action) {
        mLocalResponse.addDraft(draft, action);
    }

    @Override
    public void getDrafts(Action1<List<LogDraftBean>> action) {
        mLocalResponse.getDrafts(action);
    }

    @Override
    public void removeDraft(String id, Action1<Integer> action) {
        mLocalResponse.removeDraft(id, action);
    }
}
