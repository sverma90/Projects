## Programming Assignment 2
###### By: Soumil Verma  &ensp;&ensp;&ensp;&ensp;     Date: 8/21/23 &ensp;&ensp;&ensp;&ensp; Class: EN 605.681.84

### Description of Project

<p>Design an algorithm for a company that requires monitoring and analyzing electronic signals coming form ships in the Atlantic Ocean. This algorithm has to be fast and efficient for a basic primitive that arises frequent untangling a superposition of 2 known signals. More specifically, the company wants to make sure that the signal they are receiving is simply an interweaving of these two emissions, with nothing extra added in. The short sequence emitted by each ship is known to the ship and to you, the receiver. 
</p>

<p>For example, given a string x consisting of 0s and 1s, we write xk to denote k copies of x concatenated together. We say that string x′ is a repetition of x if it is a prefix of xk for some number k. So, x′ = 101101101 is a repetition of x = 101. We say that a string s is an interweaving of x and y if its symbols can be partitioned into two (not necessarily contiguous) subsequence s′ and s′′ so that s′ is a repetition of x and s′′ is a repetition of y. Each symbol in s must belong to exactly one of s’ of s”. For example, if x = 101 and y = 0, then s = 100010101 is an interweaving of x and y since characters 1,2,5,7,8, and 9 form 101101 – a repetition of x – and the remaining characters 3,4,6 form 000 – a repetition of y. In terms of our application, x and y are the repeating sequences from the two ships, and s is the signal we are receiving. We want to make sure s “unravels” into  simple repetitions of x and y. 
</p>

### Brief Outline of File Structure

<p>Within the directory, the file structure includes the following files:</p>

<ul>
    <li><strong>iw.py</strong> - Main algorithm function that merges 2 sorted list into one complete list. </li>
    <li><strong>main.py</strong> - Sample Main with print statements and input error checking provided to run the program.</li>
    <li><strong>test.py</strong> - Sample test file to test parts of program.</li>
    <li><strong>Assignment2Analysis</strong> - Analysis of Assignment 2 by student.</li>
    <li><strong>__pycache__</strong> - Cache files of runtime (program generated).</li>
    <li><strong>README.md</strong> - Brief introduction of the program and includes how to run the program.</li>
    <li><strong>Runtime Snippet</strong> - Directory that contains all screenshots, also used in analysis.</li>
</ul>

### How To Run The Code

<p>To run the program, simply open a terminal window, cd into the Assignment2_Final directory, and type: <em>python main.py</em> with your 3 arguments for x, y s. As an example: <em>python main.py 101 0 1010101</em>.</p>

<p>Please note that a <em>main.py</em> is provided by student and it includes the appropriate print statements that displays the output of program. The sample main also contains code that checks for user input errors. The main algorithm file contains the return statements of x/y position, x/y repetition, and whether the string is interweaving or not.</p>

### How To Interpret the Program Output

<p>As provided in the trace run screenshots of the analysis, the program will print the following output:
 <ul>
  <li>number of comparisons</li>
  <li>your x/y/s inputs</li>
  <li>whether the x and y are interwoven or not as a true or false value (if true, s is interweaving with x and y. If false, s is not interweaving with x and y)</li>
  <li>your x/y position in an array form</li>
  <li>x/y repetition in an array form</li>
 </ul>
</p>

### How To Run the Test Case file

<p>To run the test.py program, simply open a terminal window and enter the command: <em>python test.py</em>. The program will display all test cases coded by student.</p>