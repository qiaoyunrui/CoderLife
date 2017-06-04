package com.juhezi.module.base.router.operator.manager;

import android.content.Context;

import com.juhezi.module.base.router.JUri;
import com.juhezi.module.base.router.exception.DestNotFoundException;
import com.juhezi.module.base.router.operator.IOperator;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class OMP {

    public static void putURI(JUri JUri, Class clazz) {
        OperatorManager.get().putURI(JUri, clazz);
    }

    public static boolean checkURI(JUri JUri) {
        return OperatorManager.get().checkOperatorForURI(JUri);
    }

    public static <V> V invoke(Context context, JUri JUri) {
        if (checkURI(JUri)) {
            IOperator<?, V> operator = OperatorManager.get()
                    .getOperator(JUri.getProtocol());
            return operator.invoke(JUri, context);
        } else {
            throw new DestNotFoundException(JUri.toString());
        }
    }

}
