package com.juhezi.module.base.router.operator.manager;

import android.content.Context;

import com.juhezi.module.base.router.URI;
import com.juhezi.module.base.router.exception.DestNotFoundException;
import com.juhezi.module.base.router.operator.IOperator;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class OperatorManagerProxy {

    public static void putURI(URI uri, Class clazz) {
        OperatorManager.get().putURI(uri, clazz);
    }

    public static boolean checkURI(URI uri) {
        return OperatorManager.get().checkOperatorForURI(uri);
    }

    public static <V> V invoke(Context context, URI uri) {
        if (checkURI(uri)) {
            IOperator<?, V> operator = OperatorManager.get()
                    .getOperator(uri.getProtocol());
            return operator.invoke(uri, context);
        } else {
            throw new DestNotFoundException(uri.toString());
        }
    }

}
