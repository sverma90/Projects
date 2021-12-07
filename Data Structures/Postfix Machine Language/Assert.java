/**
  *  Simple program to test stack functionality before development of main program.
  *  @version    1.0     2021-10-05
  *  @author     Soumil Verma
  */
public class Assert{
      /**
        * Simple program to test stack functionality before development of main program.
        * Will display in console log.
        * @return if stack is empty, all elements of stack, functionality of pop/clear
        */ 
    public static void main(String[] args) {

        Stack stack = new Stack(10);
        System.out.println("---------------");
        System.out.println("Test stack!");
        System.out.println("Is stack empty: " + stack.isEmpty());        //if stack empty, print true, else print false.      
        System.out.println("---------------");
        // Fill up the remaining slots in the stack.
        for (int i = 0; i < 9; i++) {
            stack.push("true");             //true represents slot is full. 
        }
        System.out.println("Added elements: " + stack.count());
        System.out.println("What is stack size: " +stack.count());                  //return stack size
        System.out.println("Is stack empty after for-loop: " +stack.isEmpty());     //if stack empty, print true, else print false.
        System.out.println("---------------");
        System.out.println("Adding 10 items to stack.");
        stack.display();            //check to see if items are being stored after for loop, displays 10 elements
        System.out.println("---------------");
        System.out.println("Removing 1 item from stack");
        stack.pop();                //remove one item, checking pop
        System.out.println("---------------");
        System.out.println("Display stack items");
        stack.display();            //check if pop function is working. Should display 9 elements
        System.out.println("---------------");
        System.out.println("Remove stack elements and display");
        stack.clear();              //removes all elements
        stack.display();            //should display no elments
        System.out.println("Is stack empty after clear: " + stack.isEmpty());       //if stack empty, print true, else print false.
        System.out.println("---------------");
    }
}