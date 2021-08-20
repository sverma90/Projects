//package assignment6;

import java.util.regex.PatternSyntaxException;
import java.util.Scanner;

/**
 * @author soumilverma
 * Module 6: Assignment 6
 * EN.605.201.84
 * March 7th, 2021
 */

/**
 * @source 
 * - Creating Arrays and printing via loop: https://www.tutorialspoint.com/How-to-read-data-from-scanner-to-an-array-in-java
 * - If string contains Integer - https://stackoverflow.com/questions/18712064/java-exceptions-enter-string-only
 * - Try Catch: https://stackoverflow.com/questions/18711896/how-can-i-prevent-java-lang-numberformatexception-for-input-string-n-a
 * - Java (.equals()): https://codingbat.com/doc/java-string-equals-loops.html
 * - Regular expression for address filed: https://stackoverflow.com/questions/11456670/regular-expression-for-address-field-validation
 * - Zip Code Validation: https://stackoverflow.com/questions/46590539/validate-an-integer-and-make-it-5-digits
 */

public class Employee {

	private int employeeID;
	private Name employeeName; 
	private Address employeeAddress;
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public Name getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(Name employeeName) {
		this.employeeName = employeeName;
	}
	public Address getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public static void main(String[] args) {
		
		/* initial scanner & variable declaration */
		Scanner input = new Scanner(System.in);
		int numberOfEmployees = 0;
		String inputState = "", inputStreet = "", inputCity = "", inputZip = "", inputFirstName = "", inputLastName = "";
		int inputDay = 0, inputMonth = 0, inputYear = 0;
		
		/**
		 * ask user how many employees to enter.
		 * while loop repeats if less than or equal to zero.
		 */

		try {
			do {
				System.out.print("How many employees you would like to enter (i.e. must be greater than 0)? ");
	  		    while (!input.hasNextInt()) {
	  		        System.out.print("INVALID ENTRY, try again. ");
	  		      input.nextLine();
	  		    }
	  		  numberOfEmployees = input.nextInt();
	  		} while (numberOfEmployees < 0);
		}
		catch(Exception e) {
			//System.out.print(e);
		}
		input.nextLine();
		
		/* Initial Class Declaration */
		Address employeeAddress[] = new Address[numberOfEmployees];
		Date employeeDate[] = new Date[numberOfEmployees];
		Employee employee[] = new Employee[numberOfEmployees];	
		
		/*
		 * Begins for loop to input # of employee information
		 */
		
		for(int h=0; h<numberOfEmployees; h++) {
			
			System.out.println("\nEmployee #" + (h+1) + " information:");
			
			/* initial class array declaration */
			employee[h] = new Employee();
			employeeAddress[h] = new Address();
			employeeDate[h] = new Date();
			
			/*
			 * Begins name class information
			 * first and last name.
			 */			

			/* ask user for first name */
			try {
				do {
					System.out.print("Enter the employee's first name: ");
					try {
						inputFirstName = input.nextLine();
					}catch (PatternSyntaxException ex) {
						System.out.print("Invalid entry, please try again.");
						input.next();
					}
					if(inputFirstName.isBlank() || inputFirstName.length() < 2 || 
							inputFirstName.matches(".*\\d.*")) { System.out.print("Invalid entry, please try again. "); }
		  		} while (inputFirstName.isBlank() || inputFirstName.length() < 2 || inputFirstName.matches(".*\\d.*"));
			}catch(PatternSyntaxException ex) {
				System.out.print("Invalid entry, please try again.");
				input.next();
			}

			/* ask user for last name */
			try {
				do {
					System.out.print("Enter the employee's last name: ");
					try {
						inputLastName = input.nextLine();
					}catch (PatternSyntaxException ex) {
						System.out.print("Invalid entry, please try again.");
						input.next();
					}
					if(inputLastName.isBlank() || inputLastName.length() < 2 || 
							inputLastName.matches(".*\\d.*")) { System.out.print("Invalid entry, please try again. "); }
		  		} while (inputLastName.isBlank() || inputLastName.length() < 2 || inputLastName.matches(".*\\d.*"));
			}catch(PatternSyntaxException ex) {
				System.out.print("Invalid entry, please try again.");
				input.next();
			}
			
			/* stores names to class */
			employee[h].setEmployeeName(new Name(inputFirstName, inputLastName));
			
			/*
			 * begins address class information
			 * employee street, city, state, ZIP
			 */		
					
			/* employee street */
			do {
				System.out.print("Enter the employee's street address (i.e. 123 Main Ave.): ");
				try {
					inputStreet = input.nextLine();
				}catch (Exception e) {
					System.out.print("Invalid entry, please try again.");
					input.next();
				}
				if(inputStreet.isBlank() || inputStreet.length() < 6) { System.out.print("Invalid entry, please try again. "); }
	  		} while (inputStreet.isBlank() || inputStreet.length() < 6);
			employeeAddress[h].setStreet(inputStreet);
			
			/* employee city */
			do {
				System.out.print("Enter the employee's city name (i.e. Baltimore): ");
				try {
					inputCity = input.nextLine();
				}catch (PatternSyntaxException ex) {
					System.out.print("Invalid entry, please try again.");
					input.next();
				}
				if(inputCity.matches("^[0-9]+$")) { System.out.print("Invalid entry, please try again. "); }
	  		} while (inputCity.matches("^[0-9]+$"));
			employeeAddress[h].setCity(inputCity);
					
			
			/* employee state */
			do {
				System.out.print("Enter the employee's state name (2-letter state abbreviations only i.e. MD): ");
				try {
					inputState = input.next().toUpperCase();
				}catch (Exception e) {
					System.out.print("Invalid entry, please try again.");
					input.next();
				}
				if( (!inputState.equals("AL")) && (!inputState.equals("AK")) && (!inputState.equals("AZ")) && (!inputState.equals("AR")) && (!inputState.equals("CA")) && (!inputState.equals("CO")) && (!inputState.equals("CT")) && (!inputState.equals("DE")) && (!inputState.equals("FL")) && (!inputState.equals("GA"))
		  		 && (!inputState.equals("HI")) && (!inputState.equals("ID")) && (!inputState.equals("IL")) && (!inputState.equals("IN")) && (!inputState.equals("IA")) && (!inputState.equals("KS")) && (!inputState.equals("KY")) && (!inputState.equals("LA")) && (!inputState.equals("ME")) && (!inputState.equals("MD"))
		  		 && (!inputState.equals("MA")) && (!inputState.equals("MI")) && (!inputState.equals("MN")) && (!inputState.equals("MS")) && (!inputState.equals("MO")) && (!inputState.equals("MT")) && (!inputState.equals("NE")) && (!inputState.equals("NV")) && (!inputState.equals("NH")) && (!inputState.equals("NJ"))
		  		 && (!inputState.equals("NM")) && (!inputState.equals("NY")) && (!inputState.equals("NC")) && (!inputState.equals("ND")) && (!inputState.equals("OH")) && (!inputState.equals("OK")) && (!inputState.equals("PA")) && (!inputState.equals("RI")) && (!inputState.equals("SC")) && (!inputState.equals("SD"))
		  		 && (!inputState.equals("TN")) && (!inputState.equals("TX")) && (!inputState.equals("UT")) && (!inputState.equals("VT")) && (!inputState.equals("VA")) && (!inputState.equals("WA")) && (!inputState.equals("WV")) && (!inputState.equals("WI")) && (!inputState.equals("WY")) && (!inputState.equals("OR")) ) 
				{ System.out.print("Invalid entry, please try again. "); }
	  		} while (	(!inputState.equals("AL")) && (!inputState.equals("AK")) && (!inputState.equals("AZ")) && (!inputState.equals("AR")) && (!inputState.equals("CA")) && (!inputState.equals("CO")) && (!inputState.equals("CT")) && (!inputState.equals("DE")) && (!inputState.equals("FL")) && (!inputState.equals("GA"))
	  				 && (!inputState.equals("HI")) && (!inputState.equals("ID")) && (!inputState.equals("IL")) && (!inputState.equals("IN")) && (!inputState.equals("IA")) && (!inputState.equals("KS")) && (!inputState.equals("KY")) && (!inputState.equals("LA")) && (!inputState.equals("ME")) && (!inputState.equals("MD"))
	  				 && (!inputState.equals("MA")) && (!inputState.equals("MI")) && (!inputState.equals("MN")) && (!inputState.equals("MS")) && (!inputState.equals("MO")) && (!inputState.equals("MT")) && (!inputState.equals("NE")) && (!inputState.equals("NV")) && (!inputState.equals("NH")) && (!inputState.equals("NJ"))
	  				 && (!inputState.equals("NM")) && (!inputState.equals("NY")) && (!inputState.equals("NC")) && (!inputState.equals("ND")) && (!inputState.equals("OH")) && (!inputState.equals("OK")) && (!inputState.equals("PA")) && (!inputState.equals("RI")) && (!inputState.equals("SC")) && (!inputState.equals("SD"))
	  				 && (!inputState.equals("TN")) && (!inputState.equals("TX")) && (!inputState.equals("UT")) && (!inputState.equals("VT")) && (!inputState.equals("VA")) && (!inputState.equals("WA")) && (!inputState.equals("WV")) && (!inputState.equals("WI")) && (!inputState.equals("WY")) && (!inputState.equals("OR")) );
			employeeAddress[h].setState(inputState);
			input.nextLine();

			
			/* employee ZIP */
			do {
				System.out.print("Enter the employee's ZIP CODE (must be 5-digit number): ");
				try {
					inputZip = input.next();
				}catch (Exception e) {
					System.out.print("Invalid entry, please try again.");
					input.next();
				}
				if(!inputZip.matches("[0-9]{5}")) { System.out.print("Invalid entry, please try again. "); }
	  		} while (!inputZip.matches("[0-9]{5}"));
	  		employeeAddress[h].setZip(inputZip);
			
			/*
			 * begins date class information
			 * employee street, city, state, ZIP
			 */	
	  		
			/* employee day */
			do {
				System.out.print("Enter what DAY the employee started working (enter 1-31 only): ");
				try {
					inputDay = input.nextInt();
				}catch (Exception e) {
					System.out.print("INVALID ENTRY.");
					input.next();
				}
				if((inputDay < 1) || (inputDay > 31)) { System.out.print("Invalid entry, please try again. "); }
	  		} while ((inputDay < 1) || (inputDay > 31));
			employeeDate[h].setDay(inputDay);
			
			/* employee month */
			do {
				System.out.print("Enter what MONTH the employee started working (enter 1-12, i.e. 1=Jan, 2=Feb, etc.): ");
				try {
					inputMonth = input.nextInt();
				}catch (Exception e) {
					System.out.print("Invalid entry, please try again.");
					input.nextLine();
				}
				if((inputMonth < 1) || (inputMonth > 12)) { System.out.print("Invalid entry, please try again. "); }
	  		} while ((inputMonth < 1) || (inputMonth > 12));
			employeeDate[h].setMonth(inputMonth);

			
			
			/* employee year */
			do {
				System.out.print("Enter what YEAR the employee started working (input range from 1900 - 2021): ");
				try {
					inputYear = input.nextInt();
				}catch (Exception e) {
					System.out.print("INVALID ENTRY. ");
					input.next();
				}
				if((inputYear < 1900) || (inputYear > 2021)) { System.out.print("Invalid entry, please try again. "); }
	  		} while ((inputYear < 1900) || (inputYear > 2021));
			employeeDate[h].setYear(inputYear);	
			input.nextLine();
			
		}		
		
		/*output via for loop
		employeeAddress is retrieving its output from toString method from Address Class */
		
		for(int j=0; j<numberOfEmployees; j++) {
			System.out.print("\nEmployee #" + (j+1) + ": \t" + 
						"Name: " + employee[j].getEmployeeName() + " \t" + 
						"Address: " + employeeAddress[j] + " \t" + 
						"Date (MM/DD/YYYY): " + employeeDate[j].getMonth() + "/" + 
						employeeDate[j].getDay() + "/" + 
						employeeDate[j].getYear());
		}
		
		//added extra space for visual appeal
		System.out.println();

		//scanner close
		input.close();
	}
}