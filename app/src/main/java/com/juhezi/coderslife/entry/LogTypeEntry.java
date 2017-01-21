package com.juhezi.coderslife.entry;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;

import com.juhezi.coderslife.R;

/**
 * Created by qiao1 on 2017/1/21.
 */
public class LogTypeEntry extends BaseObservable {
    private static String TAG = "LogTypeEntry";

    public static final int TYPE_RES = 0x100;
    public static final int TYPE_URL = 0x101;

    private String content;
    private int resId;  //资源图片
    private int picType;   //图片类型
    private String url; //网络图片

    @LogContent.CONTENT_TYPE
    private int logType;    //日志类型

    public LogTypeEntry(String content, int resId, @LogContent.CONTENT_TYPE int logType) {
        this.content = content;
        this.resId = resId;
        this.logType = logType;
        picType = TYPE_RES;
    }

    public LogTypeEntry(String content, String url) {
        this.content = content;
        this.url = url;
        resId = R.drawable.ic_error;
        picType = TYPE_URL;
    }

    @IntDef({TYPE_RES, TYPE_URL})
    private @interface PIC_TYPE {
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DrawableRes
    public int getResId() {
        return resId;
    }

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }

    @PIC_TYPE
    public int getPicType() {
        return picType;
    }

    public void setPicType(@PIC_TYPE int picType) {
        this.picType = picType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    @LogContent.CONTENT_TYPE
    public int getLogType() {
        return logType;
    }

    public void setLogType(@LogContent.CONTENT_TYPE int logType) {
        this.logType = logType;
    }
}
