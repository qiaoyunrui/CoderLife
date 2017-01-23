package com.juhezi.coderslife.tools;

/**
 * Created by qiao1 on 2017/1/23.
 */
public class BoolUtil {
    private static String TAG = "BoolUtil";

    public static boolean int2Bool(int index) {
        if (index == 1)
            return true;
        return false;
    }

    public static int bool2Int(boolean bool) {
        return bool ? 1 : 0;
    }

}
