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
}
