package io.github.keshuangjie.androidutil.common;

/**
 * 防止按钮连击很有效
 * true表示连着两次点击，false表示不是连着两次点击
 *
 * @author keshuangjie
 * @date 2015-2-28 上午11:46:41
 */
public class FastDoubleClickUtils {
    private static final int INTERVAL_VALID = 1000; // 两次点击有效的最小间隔时间

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < INTERVAL_VALID) {
            return true;
        }
        lastClickTime = time;

        return false;
    }
}
