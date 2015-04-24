package com.sinaapp.whutec.util.common;

/**
 * true表示连着两次点击，false表示不是连着两次点击
 * 防止按钮连击很有效
 */
public class FastDoubleClickUtils {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;

        return false;
    }
}
