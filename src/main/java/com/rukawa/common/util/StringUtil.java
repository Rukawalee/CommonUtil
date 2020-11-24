package com.rukawa.common.util;

import java.util.Optional;

/**
 * 字符串校验
 */
public class StringUtil {

    /**
     * 判断空字符串
     *
     * @param str 传入字符串
     * @return 返回bool
     */
    public static boolean isEmpty(String str) {
        return !Optional.ofNullable(str).isPresent();
    }

    /**
     * 截取包含最后一个关键字之前的字符串，不包含关键字所在位置
     *
     * @param src 源字符串
     * @param key 关键字
     * @return 子串
     */
    public static String substringByLastKey(String src, String key) {
        int offset = src.lastIndexOf(key);
        if (offset != -1) {
            return src.substring(0, offset);
        }
        return src;
    }
}
