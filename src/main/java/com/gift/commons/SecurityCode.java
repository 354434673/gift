package com.gift.commons; 

import java.util.Arrays;
/**
 * @ClassName:  SecurityCode   
 * @Description:生成验证码数字
 * @author YangNingSheng
 * @date 2017年4月25日 下午2:48:45
 */
public class SecurityCode {

	public enum SecurityCodeLevel {
		Simple, Medium, Hard
	}
	public static String getSecurityCode(int length, SecurityCodeLevel level,
			boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		// 字符集合（--除去易混淆的数字0,1,字母l,o,O）
		char[] codes = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
				'n',  'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z' };
		// 根据不同难度截取字符串
		if (level == SecurityCodeLevel.Simple) {
			codes = Arrays.copyOfRange(codes, 0, 10);
		} else if (level == SecurityCodeLevel.Medium) {
			codes = Arrays.copyOfRange(codes, 0, 36);
		}
		// 字符集和长度
		int n = codes.length;
		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
			throw new RuntimeException(String.format(
					"调用SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，"
							+ "当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", len,
					level, isCanRepeat, n));
		}
		// 存放抽取出来的字符
		char[] result = new char[len];
		// 判断能否出现重复字符
		if (isCanRepeat) {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
				// 必须确保不会再次抽取到那个字符，这里用数组中最后一个字符改写code[r],并将n-1
				codes[r] = codes[n - 1];
				n--;
			}
		}
		return String.valueOf(result);
	}
}
