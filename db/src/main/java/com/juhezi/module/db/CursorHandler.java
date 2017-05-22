package com.juhezi.module.db;

import android.database.Cursor;
import android.database.SQLException;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */

public interface CursorHandler {
    Object doHandler(Cursor cursor) throws SQLException;
}
