package io.github.keshuangjie.androidutil.common;

/**
 * 日志管理类
 *
 * @author keshuangjie
 * @date 2014-12-2 下午4:09:50
 */
public class Log {

	/** 是否打印日志 */
    public static boolean DEBUG = true;

    private static String LOG_TAG = "androidUtil";

    public static void v(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            android.util.Log.e(LOG_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.e(tag, msg, tr);
        }
    }
}
