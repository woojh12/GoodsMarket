package com.goodsmarket.jh.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {
	public static String encode(String message)
	{
		String result = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			for(int i = 0; i < digest.length; i++)
			{
				result += Integer.toHexString(digest[i] & 0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
		return result;
	}
}
