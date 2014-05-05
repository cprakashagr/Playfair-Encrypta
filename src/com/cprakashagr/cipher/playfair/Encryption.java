package com.cprakashagr.cipher.playfair;

public class Encryption {

	private String key;
	private String matKey = "";
	private String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char matrixKey[][] = new char[6][6];
	
	public Encryption() {
		
		key = "PLAYFAIR EXAMPLE EXTENDED2";
		buildKey();
	}
	
	public Encryption(String k) {
		
		key = k;
		buildKey();
	}
	
	private void buildKey() {
		
		int lenKey = key.length();		
		int i=0,j=0,flag=0;
		char c;
		
		for (i=0;i<lenKey;i++) {
			c = key.charAt(i);
			
			if (c == ' ') {
				continue;
			}
			
			if (!contains(matKey, c)) {
				matKey = matKey.concat(String.valueOf(c));
			}
		}
		
		lenKey = alphaNumeric.length();
		for (i=0;i<lenKey;i++) {
			c = alphaNumeric.charAt(i);
			if (!contains(matKey, c)) {
				matKey = matKey.concat(String.valueOf(c));
			}
		}
		
		// Generating the Matrix.
		for (i=0;i<6;i++) {
			
			for (j=0;j<6;j++) {
				
				matrixKey[i][j] = matKey.charAt(flag++);
			}
		}
	}

	private boolean contains(String word, char c) {
		
		int lenMatKey = matKey.length();
		int i = 0;
		
		for (i=0;i<lenMatKey;i++) {
			if (matKey.charAt(i) == c) {
				return true;
			}
		}
		return false;
	}
	
	public String encrypt(String plainText) {
		
		String enText = "";
		
		return enText;
	}

}
