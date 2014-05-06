package com.cprakashagr.cipher.playfair;

public class UI {

	public UI() {
	}

	public static void main(String[] args) {
		
		Encryption encryption = new Encryption("23456PLAYFAIREXAMPLEEXTENDED");
		String plainText = "CP";
		String encryptText = encryption.encrypt(plainText);
		System.out.println(encryptText);
		
		Decryption decryption = new Decryption("23456PLAYFAIREXAMPLEEXTENDED");
		plainText = decryption.decrypt(encryptText);
		System.out.println(plainText);
	}
}