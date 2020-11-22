package org.example.util;

import org.example.constant.ComConstants;

public class ComUtil {

    public static String genKey(String...params){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length ; i++) {
            if(i == params.length-1){
                sb.append(params[i]);
            }else{
                sb.append(params[i]).append(ComConstants.SPLIT_CHAR);
            }
        }
        return sb.toString();
    }

}
