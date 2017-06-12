package com.juhezi.module.base.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘工具类
 * Created by Juhezi[juhezix@163.com] on 2017/6/12.
 */
public class KeyBoardUtil {

    public static void openKeyBoard(Context context, EditText editText) {
        if (context == null || editText == null)
            throw new NullPointerException("The Context and EditText can not be null!");
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void closeKeyBoard(Context context, EditText editText) {
        if (context == null || editText == null)
            throw new NullPointerException("The Context and EditText can not be null!");
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
