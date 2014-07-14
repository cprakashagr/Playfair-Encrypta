package com.cprakashagr.cipher.playfair;

public class UI {

	public UI() {
	}

	public static void main(String[] args) {
		
		Encryption encryption = new Encryption("PLAYFAIREXAMPLEEXTENDED");
		String plainText = "HIIAMCHANDRAPRAKASH";
		String encryptText = encryption.encrypt(plainText);
		System.out.println(encryptText);
		
		Decryption decryption = new Decryption("PLAYFAIREXAMPLEEXTENDED");
		plainText = decryption.decrypt(encryptText);
		System.out.println(plainText);
	}
}