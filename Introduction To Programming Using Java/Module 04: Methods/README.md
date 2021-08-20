# Assignment 4

<p style="font-weight:bold;">Part 1</p>

<p>Write a program that prompts the user to enter a month (1-12) and a year (e.g., 2012), and then displays a calendar for that month and year</p>

<p>Your program must use the following methods:</p>


<table>
  <tr>
    <th>Method</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>void printMonthCalendar( int m, int y )</td>
    <td>Displays a calendar like the one above for a specified month and year.</td>
  </tr>
  <tr>
    <td>void printMonthHeader( int m, int y )</td>
    <td>Displays the header information ( month, year, line separator, 3- character day names) for a calendar.</td>
  </tr>
  <tr>
    <td>void printMonthBody( int m, int y )</td>
    <td>Displays the days in the calendar associated with the corresponding days of the week.</td>
  </tr>
  <tr>
    <td>String getMonthName( int m )</td>
    <td>Returns the name of the month for a specified month number (e.g., returns March for m=3).</td>
  </tr>
  <tr>
    <td>int getStartDay( int m, int d, int y )</td>
    <td>Returns the day of week number (1=Monday,..., 7= Sunday) for the specified month, day, and year. Note this only works for the 1st day of the month.</td>
  </tr>
  <tr>
    <td>int getNumDaysInMonth( int m, int y)</td>
    <td>Returns the number of days in a specified month and year. Leap years are accounted for.</td>
  </tr>
  <tr>
    <td>boolean isLeapYear( int y )</td>
    <td>Returns true if the specified year is a leap year, and returns false otherwise.</td>
  </tr>
</table>

<p>Code for the getStartDay() method can be downloaded from the course website. You must write the code for the remaining methods. Be sure to clearly document your code and your methods.</p>

<p style="font-weight:bold;">Part 2</p>

<p>Write a program that prompts the user to specify a year (e.g., 2012) and then displays a calendar for each month in that year. You must reuse the methods from part one.</p>