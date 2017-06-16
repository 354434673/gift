package com.gift.commons;

import java.util.UUID;
/**
 * UUID生成工具
 * @ClassName:  UUIDHexGenerator   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月13日 上午11:41:00
 */
public class UUIDHexGenerator {

	public static String get() {
        String s = UUID.randomUUID().toString().replace("-", ""); 
        //去掉“-”符号 
//        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
        return s;
	}
	public static void main(String[] args) {
		System.out.print(UUIDHexGenerator.get());
		System.out.print(UUIDHexGenerator.get().length());
	}
}
