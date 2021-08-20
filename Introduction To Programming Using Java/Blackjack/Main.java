//package blackjack2;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Main class executes the main program
 * 
 * @author soumilverma
 * @version 1.0
 *
 * sources: 
 * https://www.tutorialspoint.com/java/lang/thread_sleep_millis.htm
 * https://www.youtube.com/watch?v=buGFs1aQgaY
 * https://fog.ccsf.edu/~cpersiko/cs111a/Blackjack.java.html
 * https://www.cis.upenn.edu/~cis110/17fa/hw/hw08/blackjack.html
 * https://www.w3schools.com/java/java_switch.asp
 * https://www.w3schools.com/java/java_enums.asp
 * https://www.programiz.com/java-programming/enums
 * https://stackoverflow.com/questions/19440756/executing-lines-of-code-only-once-when-a-condition-changes-in-a-loop/19440797
 * https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
 * 
 * javadocs (source):
 * https://www.tutorialspoint.com/java/java_documentation.htm
 * https://jsdoc.app/tags-param.html
 */

public class Main {

	/**
	 * main program begins
	 * @param args Unused - an array of parameters of type String
	 */
	
	public static void main(String[] args) {
	
		try {		//simple try-catch to properly display exceptions in eclipse
			
		//scanner declaration
		Scanner input = new Scanner(System.in);

		//integer declaration
		int playerCardValue = 0, 
			dealerCardValue = 0, 
			playerScore = 0, 
			userChoice = 0, 
			dealerScore = 0,
			playerBankAccount = 50,
			//dealerBankAccount = 50,
			playerBet = 0,
			askToRunAgain = 1;
			
		//class declaration
		Player myPlayer = new Player();
		Dealer myDealer = new Dealer();
		
		
		System.out.println("----- Welcome to Blackjack Game! -----");

			

		//ask player to play game again
        while (askToRunAgain == 1) {
		
        /**
         * asking player bet and calculating dealer score
         */
		do {
			System.out.printf("\nPlayer Bank account $ %1$s", playerBankAccount);
			System.out.print("\nHow much would you like to bet (enter a number only)? $");
			try {
				do{
					playerBet = input.nextInt();
					if((playerBet > 50) || (playerBet < 1) || (playerBet > playerBankAccount)) {System.out.print("Bet too high/low or incorrect, try again. $");}
				}while((playerBet > 50) || (playerBet < 1) || (playerBet > playerBankAccount));
			}catch (Exception e) {
				System.out.print("INVALID ENTRY, please try again. $");
				input.nextLine();
			}
			input.nextLine();	//added to avoid Java Buffer problem
			
			//if bank account out of money, end game
			if(playerBankAccount < 1) {
				System.out.printf("\nPlayer Bank account: $%1$s", playerBankAccount);
				System.out.println("\nBank acount out of money. DEALER WINS. Please restart the game");
				System.exit(0);
			}
			
  		} while ((playerBankAccount <= 0) && !(playerBet >= 0)); 
		
		//performs calculation for player
		playerBankAccount -= playerBet;
		
		//Thread.sleep(1000);
		
		System.out.printf("\nPlayer bet: $%1$s  Bank account: $%2$s", playerBet, playerBankAccount);


		
		System.out.println("\n\nDealer's Cards: ");
		
		//for loop to print dealer initial card
		for (int i = 0; i < 2; i++) {
			dealerCardValue = randomNumber();			//generate random number for card value
			dealerScore += dealerCardValue;				//keeping score of card value
			//dealerScore = 19;
			myDealer.setCardValue(dealerCardValue);		
			myDealer.initialCardValue(dealerCardValue);
			//hide dealer's first card
			if(i==0) {
				System.out.println("\t - [HIDDEN]");
			}else {
				myDealer.showDealerHand(true);
			}
		}
		
		//Thread.sleep(1000);
		
		System.out.println("Player's Cards: ");
		
		/**
		 * for loop to print player initial card
		 */
		for (int i = 0; i < 2; i++) {
			playerCardValue = randomNumber();
			playerScore += playerCardValue;
			//playerScore = 18;
			myPlayer.setCardValue(playerCardValue);
			myPlayer.initialCardValue(playerCardValue);
			myPlayer.showPlayerHand(true);
		}
		
		// System.out.println("\nDealer's Hand Value: " + dealerScore);
		// System.out.println("Player's Hand Value: " + playerScore);
		
		/**
		 * Ask user whether to stand or hit?
		 */
			
		do {
		
			/**
			 * If else conditions added to process BEFORE player is asked to stand or hit
			 */
		
			//if( ((playerScore >= 21) && (dealerScore >= 21)) || ( ( (playerScore > 16) && (dealerScore > 16) ) && (playerScore == dealerScore) ) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
			if( ((playerScore >= 21) && (dealerScore >= 21)) && (playerScore == dealerScore) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
			if(playerScore == 21) {System.out.println("\nPlayer Hand card value 21. PLAYER WINS."); break;}
			if(dealerScore == 21) {System.out.println("\nDealer Hand card value 21. DEALER WINS."); break;}
			if((playerScore > 21)) {System.out.println("\nPlayer Hand card value over 21 (bust). DEALER WINS."); break;}
			if((dealerScore > 21)) {System.out.println("\nDealer Hand card value over 21 (bust). PLAYER WINS."); break;}
			
			//if bank account out of money, end game
			if (playerBankAccount < 1) {
				System.out.printf("\nPlayer Bank account: $%1$s", playerBankAccount);
				System.out.println("\nBank acount out of money. DEALER WINS. Please restart the game");
				break; 
			}
			
			/**
			 * Ask user if they would like to stand (0) or hit (1)
			 * Invalid entry check added via try catch logic
			 */

			System.out.print("\nWould you like to stand or hit? Press 0 to stand, 1 to hit: ");
			
			// try catch to validate user input
			try {										//I think TRY CATCH NOT WORKING PROPERLY
				userChoice = input.nextInt();
			}catch (Exception e) {							
				System.out.print("INVALID ENTRY. Press 0 to stand, 1 to hit: ");
				input.nextLine();
			}		
			
			
			
			/*
			 * if user hits (1)
			 * player draws a card
			 * dealer will only draw another card if <= 17
			 */
			
			if(userChoice == 1) {
			
				Thread.sleep(1000);
				
				if(dealerScore < 17) {
					System.out.println("\nDealer hits, chooses to draw another card: ");
					dealerCardValue = randomNumber();
					dealerScore += dealerCardValue;
					myDealer.setCardValue(dealerCardValue);
					myDealer.initialCardValue(dealerCardValue);
					myDealer.showDealerHand(true);
				}else{
					System.out.println("\nDealer stands.");
				}
				System.out.println("Player hits, chooses to draw another card: ");
				playerCardValue = randomNumber();
				playerScore += playerCardValue;
				myPlayer.setCardValue(playerCardValue);
				myPlayer.initialCardValue(playerCardValue);
				myPlayer.showPlayerHand(true);
				
				// System.out.println("\nDealer's Hand Value: " + dealerScore);
				// System.out.println("Player's Hand Value: " + playerScore);
				
				// these conditions only execute while player hits (1) (if userchioce = 1)
				//if( ((playerScore >= 21) && (dealerScore >= 21)) || ( ( (playerScore > 16) && (dealerScore > 16) ) && (playerScore == dealerScore) ) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
				if( ((playerScore >= 21) && (dealerScore >= 21)) && (playerScore == dealerScore) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
				if(playerScore == 21) {System.out.println("\nPlayer Hand card value 21. PLAYER WINS."); break;}
				if(dealerScore == 21) {System.out.println("\nDealer Hand card value 21. DEALER WINS."); break;}
				if((playerScore > 21)) {System.out.println("\nPlayer Hand card value over 21 (bust). DEALER WINS."); break;}
				if((dealerScore > 21)) {System.out.println("\nDealer Hand card value over 21 (bust). PLAYER WINS."); break;}
				
				if((dealerScore > playerScore) && (dealerScore > 17)) {System.out.println("\nDEALER WINS"); break;}
				if((playerScore > dealerScore) && (dealerScore > 17)) {System.out.println("\nPLAYER WINS"); break;}
			}
			
			
			/*
			 * if user choice is 0
			 * Player does not draw
			 * Dealer draws a card IF <= 17
			 */
			
			if(userChoice == 0) {
				
				Thread.sleep(1000);
				
				// if player score less than dealer score, dealer automatically draws again
				if(dealerScore < 17) {
					System.out.println("\nPlayer Stands.\nDealer hits, chooses to draw another card: ");
					dealerCardValue = randomNumber();
					dealerScore += dealerCardValue;
					myDealer.setCardValue(dealerCardValue);
					myDealer.initialCardValue(dealerCardValue);
					myDealer.showDealerHand(true);
				}else{
					System.out.println("\nPlayer & Dealer stand.");
					if(dealerScore > playerScore) {System.out.println("\nDEALER WINS"); break;}
					if(playerScore > dealerScore) {System.out.println("\nPLAYER WINS"); break;}
					break;
				}
				
				// System.out.println("\nDealer's Hand Value: " + dealerScore);
				// System.out.println("Player's Hand Value: " + playerScore);
				
				// these conditions only execute while player stands (0) (if userchioce = 0)
//				if( ((playerScore >= 21) && (dealerScore >= 21)) || ( ( (playerScore > 16) && (dealerScore > 16) ) && (playerScore == dealerScore) ) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
				if( ((playerScore >= 21) && (dealerScore >= 21)) && (playerScore == dealerScore) ) {System.out.println("\nPlayer and dealer TIE or hand value over 21."); break;}
				if(playerScore == 21) {System.out.println("\nPlayer Hand card value 21. PLAYER WINS."); break;}
				if(dealerScore == 21) {System.out.println("\nDealer Hand card value 21. DEALER WINS."); break;}
				if((playerScore > 21)) {System.out.println("\nPlayer Hand card value over 21 (bust). DEALER WINS."); break;}
				if((dealerScore > 21)) {System.out.println("\nDealer Hand card value over 21 (bust). PLAYER WINS."); break;}
				
				if((dealerScore > playerScore) && (dealerScore > 17)) {System.out.println("\nDEALER WINS"); break;}
				if((playerScore > dealerScore) && (dealerScore > 17)) {System.out.println("\nPLAYER WINS"); break;}
				
			}
			
		} while ( 
				 ( (userChoice == 1) && ( (playerScore < 22) ) 
						|| 
				( (dealerScore <= 17) ) ));
		
		
		/**
		 * Ask user to if they would like to play again
		 * Includes validation check and only accepts integer
		 * Resets dealer and player score to zero
		 */
		
		Thread.sleep(1000);
		
        System.out.println("\nWould you like to play again?");            
		do {
			System.out.printf("Bank account: $%1$s remaining\n", playerBankAccount);
			System.out.print("\nPress 1 to play again, press 0 to quit: ");
		    while ( (!input.hasNextInt()) ) {  
		        System.out.print("INVALID ENTRY. Press 1 to play again, press 0 to quit: ");
		        input.next();
		    }
		    askToRunAgain = input.nextInt();
		    dealerScore = 0;	//reset dealer score 
		    playerScore = 0;  	//reset player score
		    if (askToRunAgain == 0 ) {System.out.println("Thank you for playing, have a good day.");}
		} while (askToRunAgain != 1 && askToRunAgain !=0);
	
		
        } // } is for the while loop (ask user to run again).
		
        //scanner close
		input.close();
		
		}catch(Exception e) {				//added try catch just to make sure errors  
			System.out.println("\n" + e);	//are caught and displayed appropriately
		}
	}
	
	/**
	 * Random Number Generator for cards
	 * @return random number
	 */
	public static int randomNumber() {
		Random rndNumGenObj = new Random();
		int randomNumber = ( (rndNumGenObj.nextInt(13)) + 1 );
		return (int) randomNumber;
	}
	
}