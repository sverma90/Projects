/**
  *  Postfix Expression Machine Instruction Processor program
  *  @version    1.0     2021-10-05
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
		* @param expression accepts a expression operator.
		* @return machine language instruction via fullOutput variable.
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
					expression = scan.nextLine();
					myWriter.write("Postfix Expression: " + expression);
					myWriter.write("\nMachine Instructions Below:\n");
					myWriter.write(postfixToMachineInstruction(expression));
					myWriter.write("\n\n");
				}
				//try catch to notify user via console log of output, if error, print message. 
				try {
					myWriter.close();
					scan.close();
					System.out.println("Successfully wrote to the file.");
				} catch (IOException e) {
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
    * postfixToMachineInstruction method processes postfix expressions to machine instructions.
	* Program ONLY processes postfix expresssion. 
	* If prefix or infix, program will print "Expression is not a postfix format" and break from processing the instruction.
	* If invalid characters, an error will print and stop processing the instruction. 
    * @param expression accepts a expression operator.
    * @return machine language instruction via fullOutput variable.
    */  
    public static String postfixToMachineInstruction(String expression){
        char operator=0;
		String operand1="", operand2="", tempRegister="", formattedOutput="";
		int count = 0; 
		String fullOutput = ""; 
		Stack stack = new Stack(expression.length());
		for (int i = 0; i < expression.length() ; i++) {
			operator = expression.charAt(i);
			if(operator =='+' || operator =='-' || operator =='*' || operator =='/' || operator =='$'){
				operand2 = stack.pop();
				operand1 = stack.pop();
				if(operand2 == "Stack Empty" || operand1 == "Stack Empty"){
					formattedOutput = "Expression is not in postfix format.\n";
					fullOutput+=formattedOutput;
					break;
				}else{ 
					count=count+1; 
					tempRegister = "TEMP" + String.valueOf(count);
					formattedOutput = "LD\t" + operand1 + "\n" + operator(operator) + "\t" + operand2 + "\n" + "ST\t" + tempRegister + "\n";
					stack.push(tempRegister);
					fullOutput += formattedOutput;
				}
			}
			else if ((operator >= 'A' && operator <= 'Z') || (operator >= 'a' && operator <= 'z')) {
				stack.push(operator +"");
			}
			else if(operator == ' '){		//if space detected, continue. (ex: AB+ C-)
				continue;
			}
			else if(operator == 0){
				continue;
			}
			else {
				fullOutput += "Error in postfix expression. \nInvalid operator/operand or symbol detected.\n";
				break;
			}
		}
		return fullOutput;
    }
  /**
    * The method determines which operator the variable String operator is processing.
	* Returns the following instructor back to postfixToMachineInstruction() method.
    * @param operator accepts a expression operator.
    * @return the operator in a type String format.
    */
	public static String operator(char operator){
		if(operator == '+') return "AD";
		if(operator == '-') return "SB";
		if(operator == '*') return "ML";
		if(operator == '/') return "DV";
		if((operator == '$') || (operator == '^')) return "EX";
		return null;
	}
}