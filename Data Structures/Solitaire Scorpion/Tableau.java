/**
  * Solitaire Scorpion
  * Tableau.java
  * @version    1.0     2021-11-16
  * @author     Soumil Verma 
  * Board of game, along with computer playing the game
  * Contain a deck of cards, populate the linked list of stacks of cards and
  * the linked list of the 3 reserve cards using deck.drawCard()
  * @param deck deck of cards
  * @return actual board gameplay to output.txt file
  */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;
//begins class below
public class Tableau {
    //declarations
    private Deck deck;
    private LinkedList[] columns;
    private final int NUM_COLUMNS = 7;
    private String[] downCards;
    public Tableau[] prevStates;
    public boolean[][] faceUp;
    public String[] foundationPile;
    public int foundationIndex;
    public boolean stockPileUsed = false;
    public PrintWriter f;
    public File F;
    public int moveCounter =0;
  /**
    * constructor, sets initial head/tail to null and length to 0
    * @return null values for head/tail, length to 0
    */ 
    public Tableau(LinkedList[] L) {
        this.columns = L;
        prevStates = new Tableau[1000];
        faceUp = new boolean[3][4];
        foundationIndex = 0;
        foundationPile = new String[4];
    }
  /**
    * constructors for deck, setting foundations, columns, and linked list
    * @param deck accepts cards
    * @param i foundation counter
    * @param L linked list with cards stacked in columns
    * @return return deck of cards
    * @return counter for foundation
    * @return columns
    */ 
    public Deck getDeck() { return deck; }
    public void setFoundationIndex(int i) { foundationIndex = i; }
    public void setColumns(LinkedList[] L) { columns = L; }
    public LinkedList[] getColumns() { return columns; }
  /**
    * copies linked list from one column to another
    * @return copied LL value and its cards
    */ 
    public Tableau copy() {
        LinkedList[] L = new LinkedList[7];
        for (int i = 0; i < 7; i++) {
            L[i] = columns[i].copy();
        }
        Tableau T = new Tableau(L);
        return T;
    }
  /**
    * whether LL value equeals treu or false
    * @return boolean value true or false
    */ 
    public boolean equals(Tableau T) { 
        if (T == null) { return false; }
        for (int i = 0; i < 7; i++) {
            if (!columns[i].equals(T.getColumns()[i])) { return false; }
        }
        return true;
    }
  /**
    * method to set columns
    * @param col accepts column value
    * @param linkedListValues values of LL
    * @return void
    */ 
    public void setColumn(int col, String[] linkedListValues) {
        LinkedList L = new LinkedList();
        for (int i = 0; i < linkedListValues.length; i++) {
            L.insertLast(linkedListValues[i]);
        }
        columns[col] = L;
    }
  /**
    * tableau main constructor
    * @param deck of cards
    * @param F file path
    * @return 
    */
    public Tableau(Deck deck, File F) throws FileNotFoundException {
        this.F = F;
        f = new PrintWriter(F);
        foundationIndex = 0;
        int curColumn = 0;
        foundationPile = new String[4];
        faceUp = new boolean[3][4];
        prevStates = new Tableau[1000]; 
        this.deck = deck;
        columns = new LinkedList[NUM_COLUMNS];
        downCards = new String[3];
        // initialize each linked list that is in the array
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new LinkedList();
        }
        // deal cards from the deck into the columns
        // populated all linked list and array in the tableau
        for (int i = 0; i < 49; i++) {                          // 49 = number of cards in 7 piles (1 pile = 7 cards)
            String card = deck.drawCard();                      // draw card from deck
            columns[curColumn].insertLast(card);                // store it in array
            curColumn = (curColumn + 1) % NUM_COLUMNS;          // 0, 1, 2, 3, 4, 5, 6 - represents array
        }
        // deal the hidden cards from stock pile
        downCards[0] = deck.drawCard();
        downCards[1] = deck.drawCard();
        downCards[2] = deck.drawCard();
    }
  /**
    * prints tableau game space (doundation p)
    * a printing function to see if cards are dealt correctly in tableau
    * @return print tableau to file
    */
    public String toString() {
        String s = "";
        for (int i = 0; i < 4; i++) {
            if(foundationPile[i] == null) { s += ("O"); } 
            else { s+= (foundationPile[i]); }
            s+=("\t");
        }
        s+= "\n\n";
        boolean[] endedNull = new boolean[7];                       //this decides when to print an underscore
        ListNode[] lastNodes = new ListNode[7];
        for (int i = 0; i < 7; i++) {
            lastNodes[i] = columns[i].getHead();                    //this is the node to be printed in each column
        }
        int depthCounter = 0;
        while (!printHelper(endedNull)) {                           //while there is still a true value in the array
            for (int curCol = 0; curCol < 7; curCol++) {            //iterate over columns
                if (lastNodes[curCol] != null) {
                    if (depthCounter > 2 || curCol > 3 || faceUp[depthCounter][curCol]) {
                        s+= (lastNodes[curCol].toString());
                        s+= ("\t");
                        lastNodes[curCol] = lastNodes[curCol].next;
                    } else {
                        s+= ("X");
                        s+= ("\t");
                        lastNodes[curCol] = lastNodes[curCol].next;
                    }
                } else {
                    s+= ("_\t");
                    endedNull[curCol] = true;
                }
            }
            if (!stockPileUsed && depthCounter < 3) { s+= ("X"); }
            if (stockPileUsed && depthCounter < 3) { s+= ("O"); }
            s+= "\n";
            depthCounter++;
        }
        return s;
    }
  /**
    * returns true unless there is a false value in the array
    * @param check true or false
    * @return boolean true or false
    */
    public boolean printHelper(boolean[] check) {
        boolean rvl = true;
        for (int i = 0; i < check.length; i++) { if (!check[i]) { return false; } }
        return true;
    }
  /**
    * Logic to make a move on the board
    * - looking at the last card in a column, finding the card one less than it with the same face value in another column, and moving it over
    * - if no move is found to be possible this way, then deal the down cards if they are available
    * - if moving a stack results in exposing a card, change it from "X" to the value another valid move is to move a stack starting with a king to a currently empty column
    * - if there are down cards a move is always possible because you can deal the down cards otherwise search the board for a valid move and to make a move consists of three things. 
    * - The first is the LinkedList we are moving to the second is the linked list we are moving from and the third is where in the linked list we are moving from is the node that we are moving
    * @param colCard column of card 
    * @param moveTo what column number should cards move to
    * @param update should column update?
    * @return void
    */
    public void makeMove(int[] colCard, int moveTo, boolean update) {
        moveCounter++;
        if (update) { updatePrevMoves(); }
        if (colCard[0] < 4 && colCard[1] <= 3 && colCard[1] > 0) {
            faceUp[colCard[1] - 1][colCard[0]] = true;
        }
        ListNode mover = columns[colCard[0]].getHead();
        for (int i = 0; i < colCard[1]; i++) {
            mover = mover.next;
        }
        ListNode moveToCard = columns[moveTo].getTail();
        if (moveToCard != null) {
            moveToCard.next = mover;
            columns[moveTo].setTail(columns[colCard[0]].getTail());
            columns[colCard[0]].setTail(mover.previous);
            if (mover.previous != null) {
                mover.previous.next = null;
            } else {
                columns[colCard[0]].setTail(null);
                columns[colCard[0]].setHead(null);
            }
            mover.previous = moveToCard;
        } else {
            columns[moveTo].resetLength();
            if (mover.previous != null) {
                columns[colCard[0]].setTail(mover.previous);
                columns[colCard[0]].getTail().next = null;
            } else {
                columns[colCard[0]].setTail(null);
                columns[colCard[0]].setHead(null);
            }
            while (mover != null) {
                columns[moveTo].insertLast(mover.toString());
                mover = mover.next;
            }
        }
        checkFoundation();
    }
  /**
    * check foundation values
    * For each column, if head is king and tail is ace and their suits are the same
    * call removeColumn and cycle through each column.
    * if the intermediate card is the wrong rank or suit, dont call removeColumn.
    * @return void
    */
    public void checkFoundation() {
        for (int i = 0; i < columns.length; i++) {  
            if (i >= 4 || faceUp[0][i]) {
                if (columns[i].getHead() != null) {
                    Card checkHead = new Card(columns[i].getHead().toString());            
                    Card checkTail = new Card(columns[i].getTail().toString());
                    if (checkHead.getSuit() == checkTail.getSuit() && checkHead.getRankNum() == 13 && checkTail.getRankNum() == 1) {
                        boolean callRemove = true;                                         
                        ListNode checkIntermediate = columns[i].getHead().next;
                        int count = 12;
                        while (checkIntermediate != null) {                                
                            Card intermediateCard = new Card(checkIntermediate.toString());
                            if (intermediateCard.getRankNum() != count || intermediateCard.getSuit() != checkHead.getSuit()) {
                                callRemove = false;                                                                            
                            }
                            checkIntermediate = checkIntermediate.next;
                            count--;
                        }
                        if (callRemove) { removeColumn(i,checkHead.getSuit()); }
                    }
                }
            }
        }
    }
  /**
    * which column to remove and set column
    * adds cards to foundation pile
    * @param i which column number
    * @param suitRemoved which suit to remove
    * @return void
    */
    public void removeColumn(int i, char suitRemoved) {
        columns[i] = new LinkedList();
        foundationPile[foundationIndex] = "A" + suitRemoved;
        foundationIndex++;
    }
  /**
    * Once a move is complete, update the previous move and set column 
    * @return void
    */
    public void updatePrevMoves() {
        for (int i =prevStates.length-2; i >=0 ; i --) { prevStates[i+1] = prevStates[i]; }
        prevStates[0] = this.copy();
    }
  /**
    * if the game was won return true, else return false
    * @return boolean true or false
    */
    public boolean gameWon() { return false; }
  /**
    * Checks whether a move is allowable or not. 
    * Each int array of length 3 is an allowable move
    * @return boolean true or false, an array of 49 int arrays of length 3.
    */
    public int[][] allowableMoves() {
        int[][] returnArr = new int[49][3];
        for (int i =0; i < returnArr.length; i++) { returnArr[i][0] = -1; }
        int m = 0;                                          //m holds the row in the array 
        for (int i = 0; i <7; i++) {                        //i iterates over the columns
            ListNode temp = columns[i].getHead();           //first card in column
            int k=0;                                        //counts depth of temp
            while(temp != null) {                           //traverse down the linked list at columns[i]
                for(int j = 0; j <7; j++ ) {                //j holds final position
                    int[] colCard = {i,k};                  //array hold position of column, card depth
                    if(isLegal(colCard,j)) {
                        returnArr[m][0] = i;
                        returnArr[m][1] = k;
                        returnArr[m][2] = j;
                        m++;
                    }
                }
                k++;
                temp = temp.next;
            }
        }
        return returnArr;
    }
  /**
    * Logic that decides which is the best move to pick from 49 sets of position
    * calls allowable move to obtain int values
    * @return boolean true or false
    */
    public void pickAMove() {
        int[][] aMoves = allowableMoves();
        if (aMoves[0][0] != -1) { makeMove(new int[]{aMoves[0][0], aMoves[0][1]}, aMoves[0][2],true); }
    }
  /**
    * Plays the actual game
    * picks move, inserts card to the stockpile when determined appropriately, and repeats loop
    * also prints game using file
    * @return boolean true or false
    */
    public void playGame() throws IOException {
        int[][] aMoves = new int[49][3];
        while (aMoves[0][0] != -1) {
            aMoves = allowableMoves();
            f.print(this.toString());
            pickAMove();
        }
        for (int i = 0; i < 3; i ++) {                  //use the stockpile
            columns[i].insertLast(downCards[i]);
        }
        stockPileUsed = true;
        aMoves = new int[49][3];                        //repeat the above while loop
        while (aMoves[0][0] != -1) {
            aMoves = allowableMoves();
            f.print(this.toString());
            pickAMove();
            if (foundationIndex > 3) {
                aMoves[0][0] = -1;
                break;
            }
        }
        boolean allColempty = true;
        for(int i =0; i < columns.length; i++) {
            if(!columns[i].isEmpty()) { allColempty = false; }
        }
        if(foundationIndex >=3 && allColempty) {
            f.write("\n");
            f.write(this.toString());
            f.write("\n Computer Wins.");
        } else {
            f.write("\n");
            f.write(this.toString());
            f.write("\n Computer Loses.");
        }
        f.close();
    }
  /**
    * Is the move legal or not? 
    * - If the card represented by moving[] is the correct card for the fianlPos, return true
    * - moving is an array with 2 ints, the column and the position in the column
    * - final Pos is the column (in columns) moving = the column, the position in that column[]
    * if statement is to prevent moves on facedown cards moving[0] 
    * is the column, moving[1] is the depth if depth < 3, and column < 4, we need to check faceUp
    * if rank and suit of temp is appropriate to move to the card on columns[finalPos].getTail(), return true
    * @return boolean true or false
    */
    public boolean isLegal(int[] moving, int finalPos) {
        if (moving[0] == finalPos) { return false; }
        if (moving[1] < 3 && moving[0] <4) { 
            if (!faceUp[moving[1]][moving[0]]) { return false; }
        }
        ListNode movingCard = columns[moving[0]].getHead();
        for (int i = 0; i < moving[1]; i++) { movingCard = movingCard.next; }
        if (movingCard == null) {return false; }
        ListNode moveToCard = columns[finalPos].getTail();
        Card mCard = new Card(movingCard.toString());
        if (moveToCard != null) {
            Card mTCard = new Card(moveToCard.toString());
            if (mCard.getSuit() == mTCard.getSuit() && mCard.getRankNum() == mTCard.getRankNum() - 1) { return true; } 
        } else {
            if (mCard.getRankNum() == 13) {                 
                Tableau check = this.copy();  
                check.makeMove(moving, finalPos,false);
                for (int i = 0; i < prevStates.length; i++) {
                    if(check.equals(prevStates[i])) { return false; }
                }
                return true;
            }
        }
        return false;
    }
}