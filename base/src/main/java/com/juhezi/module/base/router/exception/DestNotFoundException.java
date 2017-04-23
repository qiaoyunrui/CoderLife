package com.juhezi.module.base.router.exception;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class DestNotFoundException extends RuntimeException {

    private static final String CODE = "404";

    private static final String MESSAGE = "The destination has not found anything matching the Request-URI.";

    public DestNotFoundException() {
        super(CODE + " " + MESSAGE);
    }

    public DestNotFoundException(String message) {
        super(CODE + " " + MESSAGE + " " + message);
    }

}
