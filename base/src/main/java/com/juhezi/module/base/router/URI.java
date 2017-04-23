package com.juhezi.module.base.router;

import android.os.Bundle;
import android.support.annotation.StringDef;

/**
 * Created by Juhezi on 2017/4/23.
 * <p>
 * 封装协议
 * <p>
 * 例如:
 */
public abstract class URI {

    private static final String separator = "://";

    public static final String ACTIVITY = "Activity";  //Intent跳转

    public static final String CUSTOM = "Custom";  //自定义

    @StringDef({ACTIVITY, CUSTOM})
    public @interface Protocol {
    }

    @Protocol
    private String protocol;

    private String path;

    public URI(@Protocol String protocol, String path) {
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
        URI uri = (URI) o;
        if (protocol != null ? !protocol.equals(uri.protocol) : uri.protocol != null) return false;
        return path != null ? path.equals(uri.path) : uri.path == null;
    }

}
