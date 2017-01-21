package com.juhezi.coderslife.entry;

import android.support.annotation.StringDef;

/**
 * Created by qiao1 on 2017/1/10.
 */
public class Title {

    public static final String TODAY_TITLE = "今日日志";
    public static final String ADD = "添加";

    @StringDef({TODAY_TITLE, ADD})
    private @interface TITLE_TYPE {
    }

    private String content;

    public Title(@TITLE_TYPE String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(@TITLE_TYPE String content) {
        this.content = content;
    }

}
