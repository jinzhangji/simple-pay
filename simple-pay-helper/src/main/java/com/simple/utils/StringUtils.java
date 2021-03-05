package com.simple.utils;

/**
 * Created by Jin.Z.J  2020/11/26
 */
public class StringUtils {

    public static final String EMPYT = "";


    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }


    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


    public static String stringToUnicode(String str) {
        if(isEmpty(str)){
            return EMPYT;
        }
        char[] utfBytes = str.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }


}
