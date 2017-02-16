package com.juhezi.coderslife.function.all_logs.bean;

import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.multitype.factory.TypeFactory;

/**
 * Created by qiao1 on 2017/2/16.
 */
public class TimeBean implements Visitable {
    private static String TAG = "TimeBean";

    @Override
    public int type(TypeFactory factory) {
        return factory.type(this);
    }

    public String time;

    public TimeBean(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
