package com.juhezi.coderslife.entry;

import android.support.annotation.BoolRes;
import android.support.annotation.IntDef;

/**
 * 日志内容
 * Created by qiao1 on 2017/1/12.
 */
public class LogContent {

    public static final int TYPE_REQUIREMENT = 0x000;   //类型为需求
    public static final int TYPE_DEBUG = 0x001;     //类型为Debug
    public static final int TYPE_VERSION = 0x002;   //类型为发布版本

    public static final boolean STATE_COMPLETED = true;    //完成状态
    public static final boolean STATE_UNCOMPLETED = false;  //未完成状态

    private String content;     //内容
    private int contentType;   //内容类型
    private boolean state;  //状态

    @IntDef({TYPE_REQUIREMENT, TYPE_DEBUG, TYPE_VERSION})
    public @interface CONTENT_TYPE {
    }

    public LogContent(String content, int content_type) {
        this.content = content;
        this.contentType = content_type;
        this.state = STATE_UNCOMPLETED; //初始为未完成状态
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(@CONTENT_TYPE int contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
}
