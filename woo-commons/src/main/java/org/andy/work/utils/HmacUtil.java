package org.andy.work.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HmacUtil {
	
	public static String hmac(String value, String key, String algorithm)  {
	    try  {
	        // Get an hmac_sha1 key from the raw key bytes
	        byte[] keyBytes = key.getBytes();           
	        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, algorithm);
	
	        // Get an hmac_sha1 Mac instance and initialize with the signing key
	        Mac mac = Mac.getInstance(algorithm);
	        mac.init(signingKey);
	
	        // Compute the hmac on input data bytes
	        byte[] rawHmac = mac.doFinal(value.getBytes());
	
	        // Convert raw bytes to Hex
	        byte[] hexBytes = new Hex().encode(rawHmac);
	
	        //  Covert array of Hex bytes to a String
	        return new String(hexBytes, "UTF-8");
	    }  catch (Exception e)  {
	        throw new RuntimeException(e);
	    }
	}
}
