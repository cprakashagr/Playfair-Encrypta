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
		
		for (int i=0;i<6;i++) {
			for (int j=0;j<6;j++) {
				System.out.print(" " + matrixKey[i][j]);
			}
			
			System.out.println();
		}
		
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
	
	public String encrypt(String plainText) {
		
		String enText = "";
		
		int lenPlainText = plainText.length();
		int i=0;
		int ind1=-1;
		int ind2=-1;
		
		int i1=0,i2=0;
		int j1=0,j2=0;
		
		for (i=0;i<=lenPlainText;i++) {
			try {
				char c1 = plainText.charAt(i++);
				ind1 = findIndex(c1);
				i1 = (ind1)/6;
				j1 = (ind1)%6;
				
				char c2 = plainText.charAt(i++);
				ind2 = findIndex(c2);
				
				i2 = (ind2)/6;
				j2 = (ind2)%6;
				
				//System.out.println("C1: "+ c1 + ", C2: " + c2 + ":::" + i1 + " " + j1 + " , " + i2 + " " + j2);
				
				// Logics for the Encryption.
				if (i1 == i2) {
					// Forms Row
					int k1 = (j1+1)%6;
					int k2 = (j2+1)%6;
					enText = enText.concat(String.valueOf(matrixKey[i1][k1]));
					enText = enText.concat(String.valueOf(matrixKey[i1][k2]));
					System.gc();
				}
				else if (j1 == j2) {
					// Forms Row
					int k1 = (i1+1)%6;
					int k2 = (i2+1)%6;
					enText = enText.concat(String.valueOf(matrixKey[k1][j1]));
					enText = enText.concat(String.valueOf(matrixKey[k2][j1]));
					System.gc();
				}
				else {
					// Forms Rectangle
					enText = enText.concat(String.valueOf(matrixKey[i1][j2]));
					enText = enText.concat(String.valueOf(matrixKey[i2][j1]));
					System.gc();					
				}

			}
			catch (StringIndexOutOfBoundsException e) {
				enText = enText.concat(String.valueOf(matrixKey[i1][j1]));
				System.gc();
			}
		}
		return enText;
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
