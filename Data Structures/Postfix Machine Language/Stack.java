/**
  *  A stack of String to process postfix expression
  *  @version    1.0     2021-10-05
  *  @author     Soumil Verma
  */
public class Stack {
	  int top;            				  //define top of stack
    int stackSize;    	  				//size of the stack
    String[] stackArray;  				//define array that will hold stack elements
    String previousPopVal;        //hold previous popped values
  /**
    * Constructor stack to process postfix expression
    * @param stackArray sets the length of array, passed from main and assigned automatically based on length of expression
    * @param top define top of stack 
    */ 
    public Stack(int val) {
    	top = -1;
    	this.stackSize = val;
    	stackArray = new String[val];	//using constructor to set length of array, # of elements
    }    
  /**
    * Method to check the length of stack. 
    * @return length of stack
    */ 
    public void stackLength() {
    	System.out.println(stackArray.length);
    }    
  /**
    * Method checks to see if stack is empty. 
    * @return if stack empty, true. Otherwise, false.
    */   
    public boolean isEmpty(){         			
        if (top < 0)
            return true;
        else
            return false;   
    }
  /**
    * Push method to store postfix character elements to stackArray
    * @param str accepts postfix character and stores to stack
    * @return false if stack is overflow, otherwise true
    * Time complexity: O(n)
    */ 
    public boolean push (String str){     			  
        if(top == stackSize-1) {  
            System.out.println("Stack Overflow!!!");
            return false;  
        }  
        else  {  
            top++;  
            stackArray[top]=str;  
            return true;  
        }  
    }    
  /**
    * Pop method to remove postfix character elements from stackArray
    * @return If empty, returns string stack empty. Else, returns previous pop values.
    * NOTE: Seeked help from JHU EP Tutor. Problem with pop method 
    * and it was modified by tutor's assistance.
    */ 
    public String pop () {            
        if(isEmpty()){
            return "Stack Empty";
        }  
        else   {  
            previousPopVal = stackArray[top--];
            return previousPopVal;
        }  
    }    
  /**
    * Display method to ALL elments on the stack
    * @return all stack items stored in stack
    * Time complexity: O(n)
    */ 
    public void display() {            
        System.out.print("Stack Items: ");  
        for(int i = top; i>=0;i--) {  
            System.out.print(stackArray[i] + " ");  
        }
        System.out.println();
    }
  /**
    * Peek method only displays top item of stack
    * @return top items stored on stack
    */     
    public String peek(){
        System.out.println(stackArray[top]);
        return stackArray[top];
    }    
  /**
    * Clear method clears all items from stack
    * @return clear all items froms stack
    * Time complexity: O(n)
    */ 
    public void clear() {
    	for (int i = 0; i < stackArray.length; i++)
    		pop();
    }
  /**
    * Returns size of stack
    * @return size of stack
    */     
    public int count() {
    	return stackSize;
    }
}