package com.gt;

import java.io.File;

/**
 * Created by Administrator on 2018/9/6.
 */
public class StringUtils {


    /**
     * 返回斜杠拼接的字符串
     * @param args
     * @return
     */
    public static String mergeStringWithSeparator(String...args){
        StringBuilder sb=new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(File.separator);
        }

        return sb.substring(0, sb.length()-1).toString();
    }

}