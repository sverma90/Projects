package project1;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Soumil Verma
 * Mini-Project 1
 * EN.605.201.84
 * February 27, 2021
*/

/**
 * @Source 
 * 
 * - Random Number Generator: https://www.guru99.com/generate-random-number-java.html
 * - Movements Display: https://stackoverflow.com/questions/36386864/hare-tortoise-race-visual-display
 * - Thread Sleep: https://www.journaldev.com/1020/thread-sleep-java
 * - Visual Display: https://stackoverflow.com/questions/36386864/hare-tortoise-race-visual-display
*/

public class miniProject1 {
	
	public static void main(String[] args) throws InterruptedException {  
		//variable declarations
		int tortP = 0; 
		int hareP = 0; 
		int i = 1;
		int trackLength = 0; 
		boolean winner = false;
		boolean tortWin = false;
		boolean hareWin = false;
		
		//ask user for track length
		Scanner input = new Scanner(System.in);
//		System.out.print("Input track length: ");				
//		int trackLength = input.nextInt();
		
		do {
			System.out.print("Input track length (greater than 0): ");
			try {
				trackLength = input.nextInt();
			}catch (Exception e) {
				System.out.print("INVALID ENTRY. ");
				input.nextLine();
			}
  		} while(trackLength <= 0);
		
//		for (i=0; i <= trackLength; i++) {
//			System.out.println(randomNumber(trackLength));
//		}
			
		//extra line added for visual appeal
		System.out.println();
		
		//and they're off
		System.out.println("And They're Off!");
		
		do {
			//tortoise random number
			tortP = randomNumber(trackLength);
			//different move types for tortoise
			if(tortP == 3)  { tortP = right3(tortP); }
			if(tortP == 1)  { tortP = right1(tortP); }
			if(tortP == 6)  { tortP = left6(tortP);  }
				
			//hare random number
			hareP = randomNumber(trackLength);
			//different move types for hare
			if(hareP == 9)  { hareP = right9(hareP); }
			if(hareP == 1)  { hareP = right1(hareP); }
			if(hareP == 12) { hareP = left12(hareP); }
			if(hareP == 2)  { hareP = left2(hareP);  }
			
			//reset both if < 1
			if(tortP < 1) { tortP = 0; }
			if(hareP < 1) { hareP = 0; }
			
			//extra line added for visual appeal
			System.out.println();
			
		    //print tortoise with for loop
		    for(int t = 1; t <= tortP; t++){ System.out.print("_ "); }
		    System.out.println("T ");

	    	//print hare with for loop
		    for(int h = 1; h <= hareP; h++){ System.out.print("_ "); }
		    System.out.println("H ");
		    
			//have either won? (boolean)
			if((tortP > hareP) && i == trackLength) { tortWin = true; winner = true; }
			if((hareP > tortP) && i == trackLength) { hareWin = true; winner = true; }
			//if((tortP > hareP) && tortP >= trackLength) { tortWin = true; winner = true; }
		    //if((hareP > tortP) && hareP == trackLength) { hareWin = true; winner = true; }

		    //if tortP = hareP - OUCH
		    if(tortP == hareP){ System.out.println("OUCH!"); }
		    
		    //thread sleep (milliseconds)
		    Thread.sleep(500);
		    
		    //terminate program if tortP or hareP >= trackLength
		    //added only to avoid an infinite loop problem
		    //extra line added for visual appeal
		    if((i >= trackLength)) { winner = true; System.out.println(); }
		    
		    //counter for while loop, initial winner = false, if while true, ends loop
			i++;
		}while(!winner);
		
		//check if there is a tie
		if(tortP == hareP) { System.out.println("Race is a tie!"); }
		
		//one of two winners eg., "HARE WINS!"
		if((tortP > hareP) || tortWin == true) { System.out.println("Tortoise Wins!"); }
		if((hareP > tortP) || hareWin == true) { System.out.println("Hare Wins!"); }
		
		//scanner input close
		input.close();
	}
	
	//random number method
	//Generate a random integer, n, in the range 1 ≤ n ≤ 10
	public static int randomNumber(int trackLength) {
		Random rndNumGenObj = new Random();
		int randomNumber = 0;
		for (int i = 1; i <= 10; i++){
		  randomNumber = rndNumGenObj.nextInt(10);
		  if(randomNumber == 0) {randomNumber = randomNumber + 1; }
		}
		return (int) randomNumber;
	}
	
	//tortoise movement methods
	public static int right3(int i) { return (i+3); }
	public static int right1(int i) { return (i+1); }
	public static int left6 (int i) { return (i-6); }
	
	//hare movement methods ** right 1 is above is also used **
	public static int right9(int i) { return (i+9);  }
	public static int left12(int i) { return (i-12); }
	public static int left2 (int i) { return (i-2);  }
}
