package assignment5;

import java.io.IOException;
import java.util.Scanner;

public class Assignment5 {
	
/**
 * @author: Soumil Verma
 * Module 5: Assignment 5
 * EN.605.201.84
 * February 27, 2021
 */
	
/**
 * @Source: 
 * - https://www.geeksforgeeks.org/convert-a-string-to-character-array-in-java/
 * - https://stackoverflow.com/questions/19594587/how-to-convert-a-string-array-to-char-array-in-java
 * - https://stackoverflow.com/questions/29706653/morse-code-translatorsimple
 * - https://www.artofmanliness.com/articles/morse-code/
 * - https://www.java-examples.com/compare-two-java-char-arrays-example
*/
	
	public static void main(String[] args) throws IOException {
		
		//Variable Initialization
		int userChoice = 0;
		
		// Declare English Text Char array (one-dimensional)
		char[] englishText = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
				'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				',', '.', '?', '/', '@', ' ' };

		// Declare Morse Code String array (one-dimensional)
		String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", /*a-i*/
				".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",		/*j-r*/		
				"...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", 				/*s-z*/
				".----", "..---", "...--", "....-", ".....", "-....", "--...", 			/*1-7*/
				"---..", "----.", "-----", "--..--", ".-.-.-", "..--..",  				/*8-0 , . ? */								/*8-0*/
				"-..-.", ".--.-.", "|" };
				
		//Scanner Declaraction
		Scanner userInputChoice = new Scanner(System.in);
		
		/* Print Statments
		 * Your program shall prompt the user to specify the desired type of translation, 
		 * input a string of Morse code characters or English characters,
		 */
		
//      System.out.println("Welcome to Morse Code Translator. Please select from the following option:");
//      System.out.println("- Enter 1 to translate from english to morse code.");
//      System.out.println("- Enter 2 to translate from morse code to English.");
//      System.out.print("User Choice: ");
		
		//ask user choice (only accepts numbers (1-2))
		do {
			System.out.print("User Choice (1 or 2): ");
			try {
				userChoice = userInputChoice.nextInt();
			}catch (Exception e) {
				System.out.print("INVALID ENTRY.");
				userInputChoice.nextLine();
			}
  		} while ((userChoice != 1) && (userChoice != 2));
			
		//load appropriate method via if statements
		if(userChoice == 1) {
			convertEnglishToMorseCode(englishText, morseCode);
		}
		if(userChoice == 2) {
			convertMorseCodeToEnglish(englishText, morseCode);
		}
		//close scanner
		userInputChoice.close();
	}
	
	/*
	 * Method to convert English text to Morse Code
	 * Accepts char, string as array, passes into method, asks user to input word/sentence
	 * for loop processes based on charArray user input 
	 * inner for loop compares charArray with englishText input array 
	 * if english text (based on jth position) and char array (based on ith position) equal, set string and print. 
	 */
	
	public static void convertEnglishToMorseCode(char[] englishText, String[] morseCode) {
		Scanner userInputChoice = new Scanner(System.in);
		System.out.print("Enter a word or sentence to translate: ");
		String storeIntoArray = userInputChoice.nextLine().toLowerCase();

		char[] charArray = storeIntoArray.toCharArray();

		String string = "";
		for (int i = 0; i < charArray.length; i++){
			for (int j = 0; j < englishText.length; j++){
				if (englishText[j] == charArray[i]){
					string = string + morseCode[j] + " ";  
				}
			}
		}
		System.out.println(string);
		userInputChoice.close();
	}
	
	/*
	 * Method to convert Morse Code to English text
	 * Accepts char, string as array, passes into method, asks user to input morse code
	 * for loop processes based on var user input 
	 * inner for loop compares morseCode array with var input array 
	 * if morse code text (based on jth position) and var (based on ith position) are equal, set string and print. 
	 */
	
	public static void convertMorseCodeToEnglish(char[] englishText, String[] morseCode) {
		Scanner userInputChoice = new Scanner(System.in);
		System.out.print("Enter a morse code: ");
		String storeIntoArray = userInputChoice.nextLine();
		
		String[] var = storeIntoArray.split(" ");
		
		String string = "";
        for(int i = 0;i < var.length;i++)
        {  
            for(int j = 0;j < morseCode.length;j++)
            {
                if(morseCode[j].equals(var[i])) 	//.equals from JHU lecture, == was not working
                {
                	string = string + englishText[j];
                }
            }
        }
        System.out.println(string);
        userInputChoice.close();
	}
}