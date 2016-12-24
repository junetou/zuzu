package org.andy.work.secure;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encrypt {

	
	private static final byte[] DES_KEY = { 12, 6, -10, 112, -94, -28, -38, -62 };  
	
	public static String Encrupt(Integer number){
		
		String numbers=String.valueOf(number);
		byte[] encrupt=null;
		try{
			SecureRandom random=new SecureRandom();
			DESKeySpec deskey=new DESKeySpec(DES_KEY);
			SecretKeyFactory keyfactory=SecretKeyFactory.getInstance("DES");
			SecretKey key=keyfactory.generateSecret(deskey);
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,key,random);
			encrupt=cipher.doFinal(numbers.getBytes());
		}
		catch(Exception e){
			System.out.println(e);
		}
		return byte2hex(encrupt);
	}
	
	public static String Deciphering(String number){
		
		byte[] numbers=null;
		try{
		// DES算法要求有一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
        DESKeySpec deskey = new DESKeySpec(DES_KEY);  
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey key = keyFactory.generateSecret(deskey);  
        // 解密对象  
        Cipher cipher = Cipher.getInstance("DES");  
        cipher.init(Cipher.DECRYPT_MODE, key, sr);  
        // 把字符串解码为字节数组，并解密  
        numbers =cipher.doFinal(hex2byte(number)); 
         } catch (Exception e) {  
        	 System.out.println("解密错误");
        	 return new String("1");
        }  
		
		return new String(numbers);
		
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
		stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
		if (stmp.length() == 1) {
		hs = hs + "0" + stmp;
		} else {
		hs = hs + stmp;
		}
		}
		return hs.toUpperCase();
	}
	
	public static byte[] hex2byte(String hex) {
		byte[] ret = new byte[8];
		byte[] tmp = hex.getBytes();

		for (int i = 0; i < 8; i++) {
		ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
		.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
		.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}
	
}
