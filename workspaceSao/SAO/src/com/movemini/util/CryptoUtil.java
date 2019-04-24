package com.movemini.util;

/**
 * 
 * @author Nava
 * 
 * http://javapapers.com/java/simple-encryption-decryption-with-modulo-26-polyalphabetic-cipher/
 */
public class CryptoUtil {

	
	/**
	 * 
	 */
	public static String encrypt(String plainText) {
		
		return encrypt(plainText, "MyVerySpecialSecretKey");
	}
	
	/**
	 * 
	 */
	public static String decrypt(String encryptedText) {
		
		return decrypt(encryptedText, "MyVerySpecialSecretKey");
	}
	
	public static void main(String[] args) {
		
		String plainText = "28";
		
		String secretKey = "MyVerySpecialSecretKey";
		
		
		
		System.out.println("Plain Text Before Encryption: " + plainText);
		
		String encryptedText = encrypt(plainText, secretKey);
		
		System.out.println("Encrypted Text After Encryption: " + encryptedText);
		
		String decryptedText = decrypt(encryptedText, secretKey);
		
		System.out.println("Decrypted Text After Decryption: " + decryptedText);
		
		
		
		
	}

	private static String encrypt(String plainText, String secretKey) {
		StringBuffer encryptedString = new StringBuffer();
		int encryptedInt;
		for (int i = 0; i < plainText.length(); i++) {
			int plainTextInt = (int) (plainText.charAt(i) - 'A');
			int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
			encryptedInt = (plainTextInt + secretKeyInt) % 26;
			encryptedString.append((char) ((encryptedInt) + (int) 'A'));
		}
		return encryptedString.toString();
	}

	private static String decrypt(String decryptedText, String secretKey) {
		StringBuffer decryptedString = new StringBuffer();
		int decryptedInt;
		for (int i = 0; i < decryptedText.length(); i++) {
			int decryptedTextInt = (int) (decryptedText.charAt(i) - 'A');
			int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
			decryptedInt = decryptedTextInt - secretKeyInt;
			if (decryptedInt < 1)
				decryptedInt += 26;
			decryptedString.append((char) ((decryptedInt) + (int) 'A'));
		}
		return decryptedString.toString();
	}
	
}