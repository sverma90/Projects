/**
  * Solitaire Scorpion
  * Card.java
  * @version    1.0     2021-11-16
  * @author     Soumil Verma 
  * ListNode, an individual element of a Linked List.
  * @param nodeObject as String, creates a next, previous data info. of node
  * @return a node of string object
  */
public class ListNode{				//holds pointers of next/previous nodes
    public Object data;             //holds data
    public ListNode next; 			//pointer to next node
    public ListNode previous;		//pointer to previous linked node
  /**
    * String constructor
    * @param string accepts data as string, next and previous assisnged as null
    * @return next and previous assisnged as null, data = string value
    */  
    public ListNode(String string) {
        this.data = string;
        this.next = null;
        this.previous = null;
    }
  /**
    * toString method checks value of node
    * @return check value of node
    */ 
    public String toString() { return (String) data; }
  /**
    * Copies nodes and following stirng object
    * used in tableau for moving cards
    * @return check value of node
    */ 
    public ListNode copy() { return new ListNode((String) data); }
  /**
    * equals method checks value of node
    * @param accepts ListNode L
    * @return boolean value 
    */ 
    public boolean equals(ListNode L) {
        boolean eq = true;  //used for testing
        if (L == null) { return false; }
        if (((String) data).length() != ((String) L.data).length()) { return false; }
        for(int i = 0; i < ((String) data).length(); i++) {
            if (((String) data).charAt(i) != ((String) L.data).charAt(i)) { return false; }
        }
        return true;
    }
}