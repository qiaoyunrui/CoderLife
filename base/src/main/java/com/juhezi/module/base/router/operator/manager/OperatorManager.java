package com.juhezi.module.base.router.operator.manager;

import com.juhezi.module.base.router.JUri;
import com.juhezi.module.base.router.operator.ActivityOperator;
import com.juhezi.module.base.router.operator.IOperator;

import java.util.HashMap;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class OperatorManager {

    private static class Holder {
        private static final OperatorManager sInstance = new OperatorManager();
    }

    public static OperatorManager get() {
        return Holder.sInstance;
    }

    //路由操作池
    private HashMap<String, IOperator> mOperatorPool;

    private OperatorManager() {
        mOperatorPool = new HashMap<>();
        putDefaultOperator();
    }

    /**
     * 初始化默认路由操作，把 IntentOperator 放入管理池中。
     */
    private void putDefaultOperator() {
        if (mOperatorPool != null) {
            mOperatorPool.put(ActivityOperator.PROTOCOL, new ActivityOperator());
        }
    }

    /**
     * 根据协议检查路由是否存在
     *
     * @param protocol
     * @return
     */
    public boolean checkOperatorForProtocol(@JUri.Protocol String protocol) {
        return mOperatorPool != null
                && mOperatorPool.keySet().contains(protocol);
    }

    public boolean checkOperatorForURI(JUri JUri) {
        if (JUri != null) {
            IOperator operator = getOperator(JUri.getProtocol());
            if (operator != null) return true;
        }
        return false;
    }

    /**
     * 根据 protocol 获取 Operator
     */
    public <T, V> IOperator<T, V> getOperator(@JUri.Protocol String protocol) {
        IOperator<T, V> operator = null;
        if (mOperatorPool != null) {
            operator = mOperatorPool.get(protocol);
        }
        return operator;
    }

    public <T> void putURI(JUri JUri, Class<T> clazz) {
        IOperator<T, ?> operator = getOperator(JUri.getProtocol());
        if (operator != null) {
            operator.put(JUri, clazz);
        }
    }

}
