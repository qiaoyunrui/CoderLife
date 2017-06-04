package com.juhezi.module.base.router;

import android.support.annotation.StringDef;

/**
 * Created by Juhezi on 2017/4/23.
 * <p>
 * 封装协议
 * <p>
 * 例如:
 */
public class JUri {

    private static final String separator = "://";

    public static final String ACTIVITY = "Activity";  //Intent跳转

    public static final String CUSTOM = "Custom";  //自定义

    @StringDef({ACTIVITY, CUSTOM})
    public @interface Protocol {
    }

    @Protocol
    private String protocol;

    private String path;

    public JUri(@Protocol String protocol, String path) {
        this.protocol = protocol;
        this.path = path;
    }

    @Protocol
    public String getProtocol() {
        return protocol;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return protocol + separator + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JUri JUri = (JUri) o;
        if (protocol != null ? !protocol.equals(JUri.protocol) : JUri.protocol != null) return false;
        return path != null ? path.equals(JUri.path) : JUri.path == null;
    }

}
