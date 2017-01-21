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
    private int type;   //图片类型

    private String url; //网络图片

    public LogTypeEntry(String content, int resId) {
        this.content = content;
        this.resId = resId;
        type = TYPE_RES;
    }

    public LogTypeEntry(String content, String url) {
        this.content = content;
        this.url = url;
        resId = R.drawable.ic_error;
        type = TYPE_URL;
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
    public int getType() {
        return type;
    }

    public void setType(@PIC_TYPE int type) {
        this.type = type;
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
}
