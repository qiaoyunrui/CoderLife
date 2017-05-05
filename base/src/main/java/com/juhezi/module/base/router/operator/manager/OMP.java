package com.juhezi.module.base.router.operator.manager;

import android.content.Context;

import com.juhezi.module.base.router.Uri;
import com.juhezi.module.base.router.exception.DestNotFoundException;
import com.juhezi.module.base.router.operator.IOperator;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class OMP {

    public static void putURI(Uri uri, Class clazz) {
        OperatorManager.get().putURI(uri, clazz);
    }

    public static boolean checkURI(Uri uri) {
        return OperatorManager.get().checkOperatorForURI(uri);
    }

    public static <V> V invoke(Context context, Uri uri) {
        if (checkURI(uri)) {
            IOperator<?, V> operator = OperatorManager.get()
                    .getOperator(uri.getProtocol());
            return operator.invoke(uri, context);
        } else {
            throw new DestNotFoundException(uri.toString());
        }
    }

}
