package com.juhezi.module.base.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/5.
 */

public class TypefaceBuilder {

    private static String path = "";

    private static AssetManager assetManager;

    public static void setPath(String path) {
        TypefaceBuilder.path = path;
    }

    public static void setAssetManager(AssetManager assetManager) {
        TypefaceBuilder.assetManager = assetManager;
    }

    public static Typeface build() {
        if (!TextUtils.isEmpty(path) && assetManager != null) {
            return Typeface.createFromAsset(null, path);
        }
        return null;
    }

}
