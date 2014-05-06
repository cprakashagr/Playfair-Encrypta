package com.cprakashagr.cipher.playfair;

public class UI {

	public UI() {
	}

	public static void main(String[] args) {
		
		Encryption encryption = new Encryption("TESTCPCHANDRAPRAKASH");
		String plainText = "BALWANT";
		String encryptText = encryption.encrypt(plainText);
		System.out.println(encryptText);
		
		Decryption decryption = new Decryption("TESTCPCHANDRAPRAKASH");
		plainText = decryption.decrypt(encryptText);
		System.out.println(plainText);
	}
}