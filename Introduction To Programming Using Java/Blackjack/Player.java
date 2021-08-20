//package blackjack2;

/**
 * Player class building deck and showing hands
 * 
 * @author soumilverma
 *
 */

import java.util.Random;

public class Player {
	
	private Deck[] myDeck = new Deck[52];
	private int cardValue;
		
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	
	public Deck buildPlayerDeck() {
		Deck myPlayerDeck = null; 
		for(int i = 0; i < 52; i++) {
			this.myDeck[i] = new Deck(this.cardValue, Suit.values()[new Random().nextInt(Suit.values().length)]);
			myPlayerDeck = this.myDeck[i];
			//System.out.println(this.cardValue + " " + this.myDeck[i]);
		}
		return myPlayerDeck;
	}
	
	public void showPlayerHand(boolean showCard) {
		//System.out.println("\t - "+this.buildPlayerDeck()+" ("+this.initialCardValue(this.cardValue)+")");
		System.out.println("\t - "+this.buildPlayerDeck());
		this.calcCardValue(this.cardValue);
	}
	
	public int initialCardValue(int initCardValue) {
		//System.out.println("calcCardValue" + " "+ calcCardValue);
		return initCardValue;
	}
	public int calcCardValue(int calcCardValue) {
		calcCardValue += calcCardValue;	
		return calcCardValue;
	}
}