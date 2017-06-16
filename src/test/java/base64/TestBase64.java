package base64;

import org.junit.Test;

import com.gift.commons.Base64Util;
import com.gift.commons.FinalData;

public class TestBase64 {	
	@Test
	public void test() throws Exception{
		String passBase64 = Base64Util.encryptBASE64("123456"+FinalData.SALT);
		
		System.out.println(passBase64);
		
		String decryptBASE64 = Base64Util.decryptBASE64(passBase64);
		
		System.out.println(decryptBASE64);
	}
}
