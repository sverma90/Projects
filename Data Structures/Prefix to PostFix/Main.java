/**
  *  Prefix to Postfix Expression Converter program
  *  @version    1.0     2021-10-26
  *  @author     Soumil Verma
  */
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  /**
	* Main includes file, scanner, fileWriter to process each input file (reads each line instead of character)
	* try-catch statements are used to catch errors.
	* - check if file is empty, if file found run program, else print exception error and end program 
	* - notify user via console log of output, if error, print message. 
	* If invalid characters, an error will print and stop processing the instruction. 
	* @param expression scans for prefix expressions.  
	* @return postfix expressions to an output text file. 
	* NOTE: Any infix expression will write "error" to output file. 
	*/
    public static void main(String[] args) throws IOException, InterruptedException {
		String expression;  
		//try catch to check if file is empty, if file found run program, else print exception error and end program
		try{
			try{
				//load file, scanner, file writer
				File file = new File(args[0]);
				Scanner scan = new Scanner(file);
				FileWriter myWriter = new FileWriter(args[1], false);
				//if file is empty, print message and end program.
				if(!scan.hasNextLine()){
					System.out.println("File is empty. Restart the program and try again.");
					System.exit(1);
				}
				//while data exists in file, process code.
				//if blank line is scanned, instruction will not process.
				while(scan.hasNextLine()){
					expression = format(scan.nextLine());								//custom format method that functions like replaceAll()
					myWriter.write("Prefix Expression : " + expression);
					myWriter.write("\nPostfix Expression: ");
					//Enhancement to prevent reading infix expressions (if-else statement)
					String myAnswer = prefixToPostFix(expression, 0);						//call prefixToPostFix method
					if(myAnswer.length() != expression.length()){							//if input length != expression length
						myWriter.write("Error. Invalid Prefix Expression " + expression);	//write error (means it is a infix expression)
					}else{																	//else write output
						myWriter.write(myAnswer);
					}					
					myWriter.write("\n\n");
				}
				//try catch to notify user via console log of output, if error, print message. 
				try {
					myWriter.close();
					scan.close();
					System.out.println("Successfully wrote to the file.");
				}catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Missing filepath. Please try again.");
					System.exit(1);
				}
		}catch(FileNotFoundException e){
			System.out.println("An error occurred. Check the file and/or file is missing. Restart the program.");
			System.exit(1);
		}
	}
  /**
    * Method to convert prefix to postfix expressions. If unable to convert, returns an error.
    * @param expression accepts prefix expression
	* @param pos accepts start position of expression (i.e. pos=0)
    * @return postfix expression. 
    * Time complexity: O(2^N)
    */ 
	public static String prefixToPostFix(String expression, int index) {		
        if(expression.length()<=index) { 										//base case, returns string as is.			
            return "";
        }
        if(expression.length() <= index+1){										//base case + 1, returns string as is, with expression at particular index.
            return "" + expression.charAt(index);
        }
		char ch = expression.charAt(index);										//convert expression to individual character for processing.       
        if(isOperator(ch)){														//if character is an operator.
            String arg1 = prefixToPostFix(expression, index+1);					//storing operator (argument1).
            String arg2 = prefixToPostFix(expression, index+1+arg1.length());	//storing operand (argument2).
            return arg1 + arg2 + ch;											//return concatenated string (argument1 + argument2 + remaining character).
        }
        else {																	//else return string as is.
        	return "" + ch;
        }
	}
  /**
    * Boolean method that returns true or false if operator is detected. Improves readibility of prefixToPostFix() method. 
	* Enhancement include $, ^ operator logic. 
    * @param operator Accepts operator from prefixToPostFix() method.
    * @return If operator, return true. Else return false. 
    */ 
	public static boolean isOperator(char operator){
		if(operator =='+' || operator =='-' || operator =='*' || operator =='/' || operator =='$' || operator =='^'){
			return true;
		}else{
			return false;
		}
	}
  /**
    * Enhancement String method that accepts an expression and removes all spaces. 
	* Method similar to replaceAll() functionality. In addition, it also removes parenthesis for processing expressions. 
    * @param expression Accepts postfix expression from scanner read file.
    * @return formatted string. 
    */ 
	public static String format(String expression){
		String format = ""; 
		 for(int i=0; i<expression.length(); i++){
			if((expression.charAt(i)!=' ') && (expression.charAt(i)!='(') && (expression.charAt(i)!=')') ){	
		         format=format+expression.charAt(i);
			}else {
				 continue;
			}
		}
		return format;
	}	
}