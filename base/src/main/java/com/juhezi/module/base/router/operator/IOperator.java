package com.juhezi.module.base.router.operator;

import android.content.Context;

import com.juhezi.module.base.router.JUri;
import com.juhezi.module.base.router.exception.DestNotFoundException;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public interface IOperator<T, K> {

    /**
     * 添加路由器地址
     *
     * @param JUri
     * @param clazz
     */
    void put(JUri JUri, Class<T> clazz);

    /**
     * 执行路由操作
     *
     * @param context
     * @param JUri
     * @return
     */
    K invoke(JUri JUri, Context context) throws DestNotFoundException;

    /**
     * 检查当前路由线路是否存在
     *
     * @param JUri
     * @return
     */
    boolean check(JUri JUri);

}
