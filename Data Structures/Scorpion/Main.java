/**
  * Solitaire Scorpion
  * Main.java
  * @version    1.0     2021-11-16
  * @author     Soumil Verma
  * Main reads input.txt and creates a Deck with cards in the order in input.txt
  * Sends shuffled cards to Tableau.java
  * Also need an option to shuffle this input before dealing the cards 
  * @param args[0],arg[1] accept input and output filepath. 
  * @return Scorpion game played by computer.
  */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//begins main class
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	//variable declarations
    	String[] expression = new String[52];		//52 = cards in a deck
     	int i = 0; 
        //java object declarations
    	Deck deck = null; 
    	Tableau tableau = null;	
    	//try catch to check if file is empty, if file found run program, else print exception error and end program
    	try {
			//load file, scanner, file writer
			File file = new File(args[0]);	 
			File file2 = new File(args[1]);   
			Scanner scan = new Scanner(file);
			//if file is empty, print message and end program.
			if(!scan.hasNextLine()){
				System.out.println("File is empty. Restart the program and try again.");
				System.exit(1);
			}
			//while data exists in file, process code.
			//if blank line is scanned, cards will not process.
            //i increment added to store scanned cards in expression array
			//expression array is passed into deck and tableau class and shuffled.
			while(scan.hasNext()){
				expression[i] = scan.next();
				i++;
			}
            deck = new Deck(expression);
            deck.shuffle();
            tableau = new Tableau(deck, file2);
            tableau.playGame();
            scan.close();
            System.out.println("Program complete, check output file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing filepath. Please try again.");
            System.exit(1);
        }
    }
}