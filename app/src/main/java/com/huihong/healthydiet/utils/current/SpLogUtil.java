package com.huihong.healthydiet.utils.current;

import android.util.Log;

/**
 * 这个日志类用来打印 sp中的数据
 */
public class SpLogUtil {

    public static boolean isDebug = true;
    private static String TAG = "SharedPreferences";

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(TAG, tag + msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, "\n\n\n" + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(TAG, tag + "\n" + msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, "" + "\n" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(TAG, tag + "\n" + msg);
        }
    }
}
