package com.juhezi.coderslife.function.all_logs.bean;

import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.multitype.factory.TypeFactory;

/**
 * Created by qiao1 on 2017/2/16.
 */
public class ProgressBean implements Visitable {
    private static String TAG = "ProgressBean";

    //no element

    @Override
    public int type(TypeFactory factory) {
        return factory.type(this);
    }
}
