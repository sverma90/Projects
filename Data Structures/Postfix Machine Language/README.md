# Postfix Expression Machine Instruction Processor

<p><b>Data Structure used:</b> Stacks</p>

<p>Write a program that accepts a postfix expression containing single letter operands and the operators +, -, *, and / and prints a sequence of instructions to evaluate the expression and leaves the result in the register. Use variables of the form TEMPn as temporary variables. For example, using the postfix expression should print the following:</p>

<p><b>Example: [ ( ( B * C ) + A ) / ( D - E ) ]</b></p>

| File | Description |
| ------------- | ------------- |
| LD | B |
| ML | C |
| ST | TEMP1 |
| LD | A |
| AD | TEMP1 |
| ST | TEMP2 |
| LD | D |
| SB | E |
| ST | TEMP3 |
| LD | TEMP2 |
| DV | TEMP3 |
| ST | TEMP4 |

<p><b>Machine Instructions</b></p>

| File | Description |
| ------------- | ------------- |
| LD | Load |
| ST | Store |
| AD | Add |
| SB | Substract |
| ML | Multiply |
| DV | Divide |
| EX | Exponential |

<br>

![Runtime Screenshot](https://github.com/sverma90/Java-Projects/blob/main/Data%20Structures/Postfix%20Machine%20Language/RunTime_Screenshot.png?raw=true "Runtime Screenshot")
