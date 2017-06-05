package com.juhezi.module.base.common;

import android.app.Application;

import com.juhezi.module.base.Config;
import com.juhezi.module.base.util.TypefaceBuilder;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/5.
 */

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceBuilder.setAssetManager(getAssets());
        TypefaceBuilder.setPath(Config.FONT_ASSET_PATH);
    }
}
