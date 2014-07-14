package com.cprakashagr.cipher.playfair;

public class Encryption {

	private String key;
	private StringBuilder matKey = new StringBuilder();
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
		int i,j,flag=0;
		char c;
		
		for (i=0;i<lenKey;i++) {
			c = key.charAt(i);
			
			if (c == ' ') {
//				continue;
			}
			
			if (!contains(matKey, c)) {
				matKey.append(String.valueOf(c));
			}
		}
		
		lenKey = alphaNumeric.length();
		for (i=0;i<lenKey;i++) {
			c = alphaNumeric.charAt(i);
			if (!contains(matKey, c)) {
				matKey.append(String.valueOf(c));
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

	private boolean contains(StringBuilder matKey2, char c) {
		
		int lenMatKey = matKey.length();
		int i;
		
		for (i=0;i<lenMatKey;i++) {
			if (matKey.charAt(i) == c) {
				return true;
			}
		}
		return false;
	}
	
	public String encrypt(String plainText) {
		
		StringBuilder enText = new StringBuilder();
		
		int lenPlainText = plainText.length();
		int i=0;
		int ind1=-1;
		int ind2=-1;
		
		int i1=0,i2=0;
		int j1=0,j2=0;
		
		for (i=0;i<=lenPlainText;i++) {
			try {
				char c1 = plainText.charAt(i);
				ind1 = findIndex(c1);
				i1 = (ind1)/6;
				j1 = (ind1)%6;
				
				char c2 = plainText.charAt(++i);
				ind2 = findIndex(c2);
				
				i2 = (ind2)/6;
				j2 = (ind2)%6;
				
				// Logics for the Encryption.
				if (i1 == i2) {
					// Forms Row
					int k1 = (j1+1)%6;
					int k2 = (j2+1)%6;
					enText.append(String.valueOf(matrixKey[i1][k1]));
					enText.append(String.valueOf(matrixKey[i1][k2]));
				}
				else if (j1 == j2) {
					// Forms Row
					int k1 = (i1+1)%6;
					int k2 = (i2+1)%6;
					enText.append(String.valueOf(matrixKey[k1][j1]));
					enText.append(String.valueOf(matrixKey[k2][j1]));
				}
				else {
					// Forms Rectangle
					
					enText.append(String.valueOf(matrixKey[i1][j2]));
					enText.append(String.valueOf(matrixKey[i2][j1]));
				}

			}
			catch (StringIndexOutOfBoundsException e) {
				enText.append(String.valueOf(matrixKey[i1][j1]));
//				System.out.println("\n Exception.\n");
			}
		}
		return enText.toString();
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
