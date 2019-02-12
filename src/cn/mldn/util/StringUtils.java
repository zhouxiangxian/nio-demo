package cn.mldn.util;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mldn on 2015/7/7.
 */
public class StringUtils {
    public static String initcap(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1) ;
    }
}
