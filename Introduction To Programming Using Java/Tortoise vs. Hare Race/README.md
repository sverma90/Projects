# Tortoise vs. Hare Race


<p style="font-weight: bold">Project Specification:</p>

<p>This project involves writing a program to simulate a tortoise and hare race. The contenders will each race along a horizontal course that contains at least 50 positions. You may add more if you wish. The race begins with each contender at position 1. The contender that first reaches or passes the last position of the course is the winner of the race.</p>

<p>The following table indicates the types of moves that each contender can make.</p>

<table style="text-align: center">
<tr>
    <th>Conteder</th>
    <th>Type of Move</th>
    <th>Percentage of Time</th>
    <th>Result of Move</th>
  </tr>
  <tr>
    <td>Tortoise</td>
    <td>Fast plod</td>
    <td>50%</td>
    <td>3 squares to right</td>
  </tr>
  <tr>
   <td></td>
   <td>Slow plod</td>
   <td>30%</td>
   <td>1 square to right</td>
  </tr>
  <tr>
   <td></td>
   <td>Slip</td>
   <td>20%</td>
   <td>6 squares to left</td>
  </tr>
  <tr>
   <td>Hare</td>
   <td>Big hop</td>
   <td>20%</td>
   <td>9 squares to right</td>
  </tr>
  <tr>
   <td></td>
   <td>Small hop</td>
   <td>30%</td>
   <td>1 square to right</td>
  </tr>
  <tr>
   <td></td>
   <td>Big slip</td>
   <td>10%</td>
   <td>12 squares to left</td>
  </tr>
  <tr>
   <td></td>
   <td>Small slip</td>
   <td>20%</td>
   <td>2 squares to left</td>
  </tr>
  <tr>
   <td></td>
   <td>Fall asleep</td>
   <td>20%</td>
   <td></td>
  </tr>
</table>

<p>Each contender starts at position 1. When a contender slips, they can’t slip any further left than position 1. You will use a random number generator to simulate the percentages of each type of move indicated in the table. To generate random numbers, you can research the built-in Java random number method that is part of the Math class.</p>

<p>Generate a random integer, n, in the range 1 ≤ n ≤ 10. For the tortoise, perform a fast plod if the number is 1-5, a slow plod if the number is 6-8, and a slip if the number is 9-10. For the hare, perform a big hop if the number is 1-2, a small hop if the number is 3-5, a big slip if the number is 6, a small slip if the number is 7-8, and fall asleep if the number is 9-10.</p>

<p>There are a number of ways to design this program. One way would be to have a looping construct be the overall controller of things. Each iteration would adjust the contender positions, and the loop would terminate when one of the contenders reaches the last square of the race course. You will decide on an approach as part of your design step.</p>

<p>You must keep track of each contender’s position and display it each time positions change. Show the letter "T" in the position of the tortoise, and the letter "H" in the position of the Hare. It is possible for the contenders to land on the same square. When this happens, the tortoise bites the hare, and your program should display "OUCH!!" beginning at that square. All output positions other than the "T", the "H", and the "OUCH!!" should be blank.</p>

<p>If the tortoise wins, display "TORTOISE WINS!!". If the hare wins, display "HARE WINS!!". If the race is a tie, display "IT’S A TIE!!". At the beginning of the race, display "AND THEY’RE OFF!!".</p>

<br>

![Runtime Screenshot](https://github.com/sverma90/Java-Projects/blob/main/Introduction%20To%20Programming%20Using%20Java/Tortoise%20vs.%20Hare%20Race/Pseudocode.png?raw=true "Runtime Screenshot")
<br>
![Runtime Screenshot](https://github.com/sverma90/Java-Projects/blob/main/Introduction%20To%20Programming%20Using%20Java/Tortoise%20vs.%20Hare%20Race/Hare_wins_output.png?raw=true "Runtime Screenshot")
<br>
![Runtime Screenshot](https://github.com/sverma90/Java-Projects/blob/main/Introduction%20To%20Programming%20Using%20Java/Tortoise%20vs.%20Hare%20Race/Race_is_tie_output.png?raw=true "Runtime Screenshot")
<br>
![Runtime Screenshot](https://github.com/sverma90/Java-Projects/blob/main/Introduction%20To%20Programming%20Using%20Java/Tortoise%20vs.%20Hare%20Race/Tortoise_wins_with_ouch_statement.png?raw=true "Runtime Screenshot")
