package com.gift.commons;

/**
 * 输出日志
 * @ClassName:  Printer   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月13日 上午11:06:06
 */
public class Printer {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("gift");

    public static void info(Object message) {
    	log.info(message);
    }
    
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable t) {
        log.error(message, t);
    }
}
