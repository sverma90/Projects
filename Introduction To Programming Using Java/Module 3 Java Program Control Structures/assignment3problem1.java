import java.util.Scanner;

/**
 * @author Soumil Verma
 * EN.605.201.84.SP21
 * JAVASE-13
 * February 9th, 2021
 */

/**
 * I coded this myself
 * It took me about 4-6 hours to understand for-loops 
 * and to get the program working
 */

public class assignment3problem1 {

	public static void main( String [] args )
	{	
		/*	
		 * ask user for number of * to generate
		 * ask user which choice to output 
		 * scanner accepts user input as string
		 * and stored as an integer
		 */
		System.out.print("Enter a number of '*' to generate: ");
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
		
		System.out.print("Enter choice 1 or choice 2 to display: ");
		Scanner choice = new Scanner(System.in);
		int execChoice = choice.nextInt();

		if(execChoice == 1)
		{
			/* 
			* Choice 1
			* Loop increments and prints '*' based on user input
			* prints from lowest to highest
			*/
			System.out.println("Choice 1: ");
			for(int i = 1; i <= userInput; i++) {
				for(int j=1; j <= i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		if (execChoice == 2)
		{
			/* 
			* Choice 2
			* Loop decrements and prints '*' based on user input
			* prints from highest to lowest
			*/
			System.out.println("\nChoice 2: ");
			for(int k = userInput; k >= 1; k--) {
				for(int l=1; l <= k; l++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}

		// Close scanner
		input.close();
		choice.close();
	}

}
