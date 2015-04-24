package com.sinaapp.whutec.util.common;

/**
 * TODO
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
