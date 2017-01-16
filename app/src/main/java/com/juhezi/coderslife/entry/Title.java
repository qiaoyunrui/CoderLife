package com.juhezi.coderslife.entry;

import android.support.annotation.StringDef;

/**
 * Created by qiao1 on 2017/1/10.
 */
public class Title {

    public static final String TODAY_TITLE = "今日日志";
    public static final String ADD_REQUIREMENT = "添加需求";

    @StringDef({TODAY_TITLE, ADD_REQUIREMENT})
    public @interface TITLE_TYPE {
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
