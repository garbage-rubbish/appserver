package com.hkd.utils;

import com.hkd.enums.BaseCodeValueEnum;

/**
 * Created by huangkd on 2019/2/26.
 */
public class EnumUtils {
        public static <T extends Enum<?> & BaseCodeValueEnum> T codeOf(Class<T> enumClass, int code) {
            T[] enumConstants = enumClass.getEnumConstants();
            for (T t : enumConstants) {
                if (t.getCode() == code) {
                    return t;
                }
            }
            return null;
        }
}
