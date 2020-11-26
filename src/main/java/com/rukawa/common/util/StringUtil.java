package com.rukawa.common.util;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * 字符串校验
 */
public class StringUtil {

    /**
     * 判断空字符串
     *
     * @param str 传入字符串
     * @return bool
     */
    public static boolean isEmpty(String str) {
        return !Optional.ofNullable(str).isPresent();
    }

    /**
     * 生成28位长度的唯一码
     *
     * @return String
     */
    public static String generateUniqueCode() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        // 获取UUID前14位
        String preUUID = uuid.toString()
                .replaceAll("-", "")
                .substring(0, 14);
        // 获取到秒的14位数
        String time = now.toString()
                .replaceAll("[-T:.]", "")
                .substring(0, 14);
        StringBuilder uniqueCodeBuilder = new StringBuilder(preUUID);
        uniqueCodeBuilder.append(time);
        return uniqueCodeBuilder.toString();
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
