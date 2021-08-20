import java.util.Scanner;

/**
 * 	@author Soumil Verma
 *  EN.605.201.84.SP21
 * 	JAVASE-13
 *  February 15th, 2021
 *  
 *  Assignment 4 Problem 1
 */

/*
 * I coded the program myself and used resources below to assist me.
 * It took me 10 hours to finish.
 * 
 * Source
 * 
 * https://www.programiz.com/java-programming/examples/leap-year
 * https://stackoverflow.com/questions/8940438/number-of-days-in-particular-month-of-particular-year
 * https://stackoverflow.com/questions/35679827/how-to-display-calendar-in-java
 */

public class Assignment4Problem1 {
	
	public static void main(String[] args) {
		
		//scanner objects
		Scanner monthInput = new Scanner(System.in);
		Scanner yearInput = new Scanner(System.in);
		
		//declare initial variables
		int month = 0;
		int year = 0;
		
		//ask user for month (only accepts numbers (1-12))
		do {
  			System.out.print("Enter a month (1-12): ");
  		    while (!monthInput.hasNextInt()) {
  		        System.out.print("INVALID ENTRY. Please enter a month (1-12): ");
  		      monthInput.next();
  		    }
  		   month = monthInput.nextInt();
  		} while ((month != 1) && (month != 2) && (month != 3) && (month != 4) && 
				 (month != 5) && (month != 6) && (month != 7) && (month != 8) && 
				 (month != 9) && (month != 10) && (month != 11) && (month != 12));
		
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
		monthInput.close();
		yearInput.close();
	}
	
	//Displays the days in the calendar associated with the corresponding days of the week.
	public static void printMonthBody ( int m, int y ) {
		int index = getStartDay(m, 1, y);
		int numDaysInMonth = getNumDaysInMonth(m, y);		

        for (int i = 0; i < index; i++)
            System.out.print("     ");
        for (int i = 1; i <= numDaysInMonth; i++) {
            System.out.printf("  %3d", i);
            if (((i + index) % 7 == 0) || (i == numDaysInMonth)) System.out.println();
        }
	}
	
	//Displays a calendar like the one above for a specified month and year.
	public static void printMonthCalendar( int m, int y ) {
		printMonthHeader(m, y);
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