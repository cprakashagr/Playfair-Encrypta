package com.cprakashagr.cipher.playfair;

public class Decryption {

	private String key;
	private String matKey = "";
	private String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char matrixKey[][] = new char[6][6];
	
	public Decryption() {
		
		key = "PLAYFAIR EXAMPLE EXTENDED2";
		buildKey();
	}
	
	public Decryption(String k) {
		
		key = k;
		buildKey();
		/*
		for (int i=0;i<6;i++) {
			for (int j=0;j<6;j++) {
				System.out.print(" " + matrixKey[i][j]);
			}
			
			System.out.println();
		}
		*/
	}
	
	private void buildKey() {
		
		int lenKey = key.length();		
		int i=0,j=0,flag=0;
		char c;
		
		for (i=0;i<lenKey;i++) {
			c = key.charAt(i);
			
			if (c == ' ') {
//				continue;
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
		matKey = null;
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

	public String decrypt(String enText) {
		
		String plainText = "";
		
		int lenEnText = enText.length();
		int i=0;
		int ind1=-1;
		int ind2=-1;
		
		int i1,i2;
		int j1,j2;
		
		for (i=0;i<=lenEnText;i+=2) {
			try {
				char c1 = enText.charAt(i);
				char c2 = enText.charAt(i+1);
				
				ind1 = findIndex(c1);
				ind2 = findIndex(c2);
				i1 = (ind1)/6;
				j1 = (ind1)%6;
				
				i2 = (ind2)/6;
				j2 = (ind2)%6;
				
				//System.out.println("C1: "+ c1 + ", C2: " + c2 + ":::" + i1 + " " + j1 + " , " + i2 + " " + j2);
				
				if (i1 == i2) {
					// Forms Row
					int k1 = (j1+5)%6;
					int k2 = (j2+5)%6;
					plainText = plainText.concat(String.valueOf(matrixKey[i1][k1]));
					plainText = plainText.concat(String.valueOf(matrixKey[i1][k2]));
					System.gc();
				}
				else if (j1 == j2) {
					// Forms Row
					int k1 = (i1+5)%6;
					int k2 = (i2+5)%6;
					plainText = plainText.concat(String.valueOf(matrixKey[k1][j1]));
					plainText = plainText.concat(String.valueOf(matrixKey[k2][j1]));
					System.gc();
				}
				else {
					// Forms Rectangle
					plainText = plainText.concat(String.valueOf(matrixKey[i1][j2]));
					plainText = plainText.concat(String.valueOf(matrixKey[i2][j1]));
					System.gc();					
				}
			}
			catch(Exception e) {
				
			}
		}		
		return plainText;
	}
	
	private int findIndex(char c) {
		int i=0,j=0;
		
		for (i=0;i<6;i++) {
			for (j=0;j<6;j++) {
				if (matrixKey[i][j] == c) {
					return (i)*6+j;
				}
			}
		}
		
		return -1;
	}
}
