/*
 * Class: CMSC203 CRN 34165
 * Instructor: Dr. Grinberg
 * Description: 
		CryptoManager class:
			Purpose: decryption and encryption using the caesar and bellaso ciphers
 * Due: 03/14/22
 * Platform/compiler: Eclipse IDE
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Christopher Perez Lebron
*/


/**
 * 
 * @author Christopher Perez Lebron
 * 		Purpose: decryption and encryption using the caesar and bellaso ciphers
 *
 */
public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		//throw new RuntimeException("method not implemented");
		boolean flag = true;
		int count = 0;
		while(count < plainText.length() && flag)
		{
			if (plainText.charAt(count) > UPPER_BOUND || plainText.charAt(count) < LOWER_BOUND)
				flag = false;
			count++;
		}
		return flag;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		//throw new RuntimeException("method not implemented");
		char charAtIndex;
		String encryptedText = "";
		
		for (int count = 0; count < plainText.length(); count++)
		{
			charAtIndex = plainText.charAt(count);
			encryptedText += charToDesiredRange((char)(charAtIndex + key));
		}
		return encryptedText;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		//throw new RuntimeException("method not implemented");
		//obtain offsetBellasoStr of the same length of plainText string
		String offsetBellasoStr = getOffsetBellasoString(plainText, bellasoStr);
	
		//create the encrypted text
		char plainCharAtIndex, //holds the plain text char at current index 
			 offsetCharAtIndex; //holds the offset char value at current index
		String encryptedText = "";
		for (int count = 0; count < plainText.length(); count++)
		{
			plainCharAtIndex = plainText.charAt(count);
			offsetCharAtIndex = offsetBellasoStr.charAt(count);
			encryptedText += charToDesiredRange((char)(plainCharAtIndex + offsetCharAtIndex));
		}
		
		return encryptedText;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		//throw new RuntimeException("method not implemented");
		char charAtIndex;
		String plainText = "";
		
		for (int count = 0; count < encryptedText.length(); count++)
		{
			charAtIndex = encryptedText.charAt(count);
			plainText += charToDesiredRange((char)(charAtIndex - key));
		}
		return plainText;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		//throw new RuntimeException("method not implemented");
		//obtain offsetBellasoStr of the same length of plainText string
		String offsetBellasoStr = getOffsetBellasoString(encryptedText, bellasoStr);
		
		//create the encrypted text
		char encryptedCharAtIndex, //holds the plain text char at current index 
			 offsetCharAtIndex; //holds the offset char value at current index
		String plainText = "";
		for (int count = 0; count < encryptedText.length(); count++)
		{
			encryptedCharAtIndex = encryptedText.charAt(count);
			offsetCharAtIndex = offsetBellasoStr.charAt(count);
			plainText += charToDesiredRange((char)(encryptedCharAtIndex - offsetCharAtIndex));
		}
		return plainText;
	}
	/**
	 * takes a character and subtracts or adds to its ASCII value 
	 * until it is inbounds. 64 is the total range and is used for simplicity
	 * @param ch a character to be repositioned into the proper bounds
	 * @return ch the character in proper bounds
	 */
	public static char charToDesiredRange(char ch)
	{
		
		while (ch > UPPER_BOUND || ch < LOWER_BOUND)
		{
			if (ch > UPPER_BOUND)
				ch -= 64;
			if (ch < LOWER_BOUND)
				ch += 64; 
		}
		return ch;
	}
	/**
	 *  Takes text (plainText or encryptedText) and the bellasoStr to
	 *   produce a offsetBellasoStr of the same length as the text. method repeats characters 
	 *   in the bellasoStr as necessary to produce the offsetBellasoStr
	 * @param text the text to be encrypted or decrypted
	 * @param bellasoStr the bellassoStr key
	 * @return offsetBellasoStr a string of the same length of text made up of characters
	 * 			in the bellasoStr. 
	 */
	public static String getOffsetBellasoString(String text, String bellasoStr)
	{
		String offsetBellasoStr = "";
		int charSelector = 0;
		while(offsetBellasoStr.length() != text.length())
		{
			offsetBellasoStr += bellasoStr.charAt(charSelector);
			if (charSelector < (bellasoStr.length() - 1))
				charSelector++;
			else
				charSelector = 0;
		}
		return offsetBellasoStr;
	}
}