package com.gift.commons;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	/**
	 * BASE64加密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String data) throws Exception {
		//return (new BASE64Encoder()).encodeBuffer(data.getBytes());
        byte[] b = null;  
        String s = null;  
        try {  
            b = data.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
	}
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String data) throws Exception {
		//将salt截去
		//return new String((new BASE64Decoder()).decodeBuffer(data)).replace(FinalData.SALT, "");
	       byte[] b = null;  
	        String result = null;  
	        if (data != null) {  
	            BASE64Decoder decoder = new BASE64Decoder();  
	            try {  
	                b = decoder.decodeBuffer(data);  
	                result = new String(b, "utf-8");  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return result.replace(FinalData.SALT, ""); 
	}
}
