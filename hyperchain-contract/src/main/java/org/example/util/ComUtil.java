package org.example.util;

import org.example.constant.ComConstants;

public class ComUtil {
    public static String genKey(String...params){
        return String.join(ComConstants.SPLIT_CHAR,params);
    }

    public static String[] splitKey(String key){
        return key.split(ComConstants.SPLIT_CHAR);
    }
}
