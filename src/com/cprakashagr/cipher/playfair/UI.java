package com.cprakashagr.cipher.playfair;

public class UI {

	public UI() {
	}

	public static void main(String[] args) {
		
		Encryption encryption = new Encryption("PLAY FAIR EXAMPLE EXTENDED CP LPU");
		String plainText = "I'M CHANDRA PRAKASH";
		String encryptText = encryption.encrypt(plainText);
	}

}
