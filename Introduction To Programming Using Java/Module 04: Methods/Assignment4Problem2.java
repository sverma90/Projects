import java.util.Scanner;

/**
 * 	@author Soumil Verma
 *  EN.605.201.84.SP21
 * 	JAVASE-13
 *  February 16th, 2021
 *  
 *  Assignment 4 Problem 2
 */

/*
 * I coded the program myself and used resource below to assist me.
 * However, the for-loop on printMonthBody() method was modified and I came up with that logic.
 * It took me 3 hours to finish.
 * 
 * Source
 * 
 * https://stackoverflow.com/questions/35679827/how-to-display-calendar-in-java
 */

public class Assignment4Problem2 {

	public static void main(String[] args) {
		
		//scanner objects
		Scanner yearInput = new Scanner(System.in);
		
		//declare initial variables
		int month = 1;
		int year = 0;
		
		//ask user for year from 0 - Present and onwards
		do {
  			System.out.print("Enter a year (e.g., 2012): ");
  		    while (!yearInput.hasNextInt()) {
  		        System.out.print("INVALID ENTRY. Please enter a year (e.g., 2012): ");
  		      yearInput.next();
  		    }
  		   year = yearInput.nextInt();
  		} while (year < 0);
		
		//call method to prints calendar and passes month/year into method
		printMonthCalendar(month, year);
		
		// Close scanner
		yearInput.close();
	}
	
	//Displays the days in the calendar associated with the corresponding days of the week.
	public static void printMonthBody ( int m, int y ) {
	
		int var = 0;
		for (int h = m; h <=12; h++) {
			var = h;
			printMonthHeader(var, y);
			for (int i = 0; i < getStartDay(var, 1, y); i++)
	            System.out.print("     ");
	        for (int i = 1; i <= getNumDaysInMonth(var, y); i++) {
	            System.out.printf("  %3d", i);
	            if (((i + getStartDay(var, 1, y)) % 7 == 0) || (i == getNumDaysInMonth(var, y))) System.out.println();
	        }
		}
		System.out.println();
	}
	
	//Displays a calendar like the one above for a specified month and year.
	public static void printMonthCalendar( int m, int y ) {
		printMonthBody(m, y);
	}
	
	//Displays the header information (month, year, line separator, 3- character day names) for a calendar.
	public static void printMonthHeader( int m, int y ) {
		System.out.println();
		System.out.println("            " + getMonthName(m) + " " +  y + "        ");
		System.out.println("-----------------------------------");
		System.out.println("  Sun  Mon  Tue  Wed  Thu  Fri  Sat");	//total 35 spaces
	}
	
	//Returns the number of days in a specified month and year. Leap years are accounted for.
	public static int getNumDaysInMonth( int m, int y ) {
		
		int numDaysInAMonth;
		
		if ( m == 4 || m == 6 || m == 9 || m == 11 ) {
			numDaysInAMonth = 30;
		}else if ( m == 2) {
			numDaysInAMonth = (isLeapYear(y)) ? 29 : 28; 
		}else {
			numDaysInAMonth = 31;
		}

		return numDaysInAMonth;
	}
	
	//Returns true if the specified year is a leap year, and returns false otherwise.
	public static boolean isLeapYear( int y ) {
	    boolean leapYear = false;

	    if (( y % 4 == 0 ) && (y % 100 != 0)) {
	    	leapYear = true;
	    }else {
	    	leapYear = false;
		}

		return leapYear;
	}
	
	//Returns the name of the month for a specified month number (e.g., returns March for m=3).
	public static String getMonthName( int m ) {
		String Month = null;
		if ( m == 1 ) { Month = "January"; }
		if ( m == 2 ) { Month = "February"; }
		if ( m == 3 ) { Month = "March"; }
		if ( m == 4 ) { Month = "April"; }
		if ( m == 5 ) { Month = "May"; }
		if ( m == 6 ) { Month = "June"; }
		if ( m == 7 ) { Month = "July"; }
		if ( m == 8 ) { Month = "August"; }
		if ( m == 9 ) { Month = "September"; }
		if ( m == 10 ) { Month = "October"; }
		if ( m == 11 ) { Month = "November"; }
		if ( m == 12 ) { Month = "December"; }
		return Month;
	}
	
	//Returns the day of week number (1=Monday,..., 7= Sunday) for the specified month, day, and year. Note this only works for the 1st day of the month.
	public static int getStartDay( int m, int d, int y )
    {
		
        // Adjust month number & year to fit Zeller's numbering system
        if (m < 3) 
        {
            m = m + 12;
            y = y - 1;
        }
        
        int k = y % 100;      // Calculate year within century
        int j = y / 100;      // Calculate century term
        int h = 0;            // Day number of first day in month 'm'
        
        h = ( d + ( 13 * ( m + 1 ) / 5 ) + k + ( k / 4 ) + ( j / 4 ) +
            ( 5 * j ) ) % 7;
        
        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ( ( h + 5 ) % 7 ) + 1; 
        
        //Manually added to stop printing issue on every Sundays (1st week only) of a new month
        if(dayNum == 7) { dayNum = 0; }
        
        return dayNum;
    }
}