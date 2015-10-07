package com.o9paathshala.security;

import org.apache.commons.codec.binary.Base64;


public class EncoderDecoder {
	// encoding is of BASE64
	private EncoderDecoder(){
		
	}
	public static String encode(String userId){
		byte[]   bytesEncoded = Base64.encodeBase64(userId.getBytes());
		String encodedForm = new String(bytesEncoded);
		return encodedForm;
	}
	
	public static String decode(String encodedId){
		byte[] valueDecoded= Base64.decodeBase64(encodedId);
		return new String(valueDecoded);
	}

}
