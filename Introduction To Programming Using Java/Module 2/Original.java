import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.*;

/**
 * 	@author Soumil Verma
 *  EN.605.201.84.SP21
 * 	JAVASE-13
 *  February 2nd, 2021
 */

/**
 * 	Source for programming ideas: 
 * 	DecimalFormat: https://www.admfactory.com/display-double-in-2-decimal-points-in-java/
 */

public class Original
{
  public static void main( String [] args )
  {
	/*	
	* ask user for number 
	* scanner accepts user input as string
	* and stored as an integer
	*/
	System.out.print("Enter a number: ");
	Scanner input = new Scanner(System.in);
	int userInput = input.nextInt();
	
	//Extra space
	System.out.println();
	
	/* 
	* Choice 1
	* Loop increments and prints '*' based on user input
	*/
	System.out.println("Choice 1: ");
	for(int i = 1; i <= userInput; i++) {
		for(int j=1; j <= i; j++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	/* 
	* Choice 2
	* Loop decrements and prints '*' based on user input
	*/
	System.out.println("\nChoice 2: ");
	for(int k = userInput; k >= 1; k--) {
		for(int l=1; l <= k; l++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	// Close scanner
	input.close();
  }
}