package com.juhezi.module.base.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/4.
 */

public class IntentUtil {

    public static String DEFAULT_TYPE = "*/*";

    public static String IMAGE_TYPE = "image/*";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Intent getOpenDocumentIntent(String type) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(type);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Intent getOpenDocumentIntent() {
        return getOpenDocumentIntent(DEFAULT_TYPE);
    }

}
