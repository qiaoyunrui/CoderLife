package com.juhezi.coderslife.entry;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 日志内容
 * Created by qiao1 on 2017/1/12.
 */
public class LogContent extends BaseObservable implements Serializable {

    public static final int TYPE_REQUIREMENT = 0x000;   //类型为需求
    public static final int TYPE_BUG = 0x001;     //类型为Debug
    public static final int TYPE_VERSION = 0x002;   //类型为发布版本
    public static final int TYPE_ERROR = 0x003;     //错误类型

    public static final boolean STATE_COMPLETED = true;    //完成状态
    public static final boolean STATE_UNCOMPLETED = false;  //未完成状态

    private String content;     //内容
    private int contentType;   //内容类型
    private boolean state;  //状态
    private String time;    //时间
    private String id;

    @IntDef({TYPE_REQUIREMENT, TYPE_BUG, TYPE_VERSION, TYPE_ERROR})
    public @interface CONTENT_TYPE {
    }

    public LogContent(String content, int content_type) {
        this(UUID.randomUUID().toString(), content, content_type);
    }

    public LogContent(String id, String content, int content_type) {
        this.id = id;
        this.content = content;
        this.contentType = content_type;
        this.time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());  //获取当前时间
        this.state = STATE_UNCOMPLETED; //初始为未完成状态
    }

    public LogContent(String content, int contentType, boolean state, String time) {
        this(UUID.randomUUID().toString(), content, contentType, state, time);
    }

    public LogContent(String id, String content, int contentType, boolean state, String time) {
        this.id = id;
        this.content = content;
        this.contentType = contentType;
        this.state = state;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    @Bindable
    public int getContentType() {
        return contentType;
    }

    @Bindable
    public void setContentType(@CONTENT_TYPE int contentType) {
        this.contentType = contentType;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    @Bindable
    public void setContent(String content) {
        this.content = content;
    }

    @Bindable
    public void setState(boolean state) {
        this.state = state;
    }

    @Bindable
    public boolean getState() {
        return state;
    }

    @Bindable
    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "LogContent{" +
                "content='" + content + '\'' +
                ", contentType=" + contentType +
                ", state=" + state +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
