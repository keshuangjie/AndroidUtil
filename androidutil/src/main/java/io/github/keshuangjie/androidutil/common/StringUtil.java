package io.github.keshuangjie.androidutil.common;

/**
 * 字符串相关工具类
 *
 * @author keshuangjie
 * @date 2015-04-12 12:17
 */
public class StringUtil {

    /**
     * 判断是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobileNumber(String str) {
        return str.matches("^1[0-9]{10}$");
    }
}
