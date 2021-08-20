//package blackjack2;

/**
 * Deck class holding value of cards
 * 
 * @author soumilverma
 *
 */

public class Deck {
	
	Player myPlayer = new Player();
	
	private Suit mySuit;
	private int myNum;
	private int cardValue;
	
	public Deck(int cardValue, Suit mySuit) {
		this.mySuit = mySuit;
		this.myNum = cardValue;
		this.cardValue = cardValue;
	}
	
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public int getMyNum() {
		return myNum;
	}
	public void setMyNum(int myNum) {
		this.myNum = myNum;
	}

	public String toString() {
		String numStr = "";
		
		switch(this.myNum) {
		case 1: 
			numStr = "Ace";
			this.cardValue = 1;
			myPlayer.setCardValue(this.cardValue);
			break;
		case 2: 
			numStr = "Two";
			this.cardValue = 2;
			break;
		case 3: 
			numStr = "Three";
			this.cardValue = 3;
			break;
		case 4: 
			numStr = "Four";
			this.cardValue = 4;
			break;
		case 5: 
			numStr = "Five";
			this.cardValue = 5;
			break;
		case 6: 
			numStr = "Six";
			this.cardValue = 6;
			break;
		case 7: 
			numStr = "Seven";
			this.cardValue = 7;
			break;
		case 8: 
			numStr = "Eight";
			this.cardValue = 8;
			break;
		case 9: 
			numStr = "Nine";
			this.cardValue = 9;
			break;
		case 10: 
			numStr = "Ten";
			this.cardValue = 10;
			break;
		case 11: 
			numStr = "Jack";
			this.cardValue = 11;
			break;
		case 12: 
			numStr = "Queen";
			this.cardValue = 12;
			break;
		case 13: 
			numStr = "King";
			this.cardValue = 13;
			break;
		}

		return numStr + " of " + this.mySuit.toString();
	}

}