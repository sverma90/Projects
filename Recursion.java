
	public class Main2 {
	//+AB/CD -> AB+CD/
	//-+ABC -> AB+C-
	public static void main(String[] args) {
		
		String exp[] = {"//A+B0-C+BA", "**A+BC+C-BA", "$+-ABC+D-EF", "-*A$B+C-DE*EF", 
				"/A+BC+C*BA", "*$A+BC+C-BA", "*$A^BC+C-BA", "*-*-ABC+BA", "/+/A-BC-BA", 
				"-+ABC", "-A+BC", "A+B"};
		
		//String exp[] = {"//A+B0-C+BA"};
		for(int i=0; i<exp.length; i++) {
			System.out.println("Original Expression \t : " + exp[i]);
			System.out.println("My Answer \t\t : " +prefixToPostFix(exp[i].replaceAll("\\s+",""), 0) );
			System.out.println("Correct Answer \t\t : " +preToPost1(exp[i].replaceAll("\\s+","")) + "\n");
		}
	}
	
	public static String prefixToPostFix(String src, int pos) {

//        if(pos ==0){
//            if(src.length()<3 || !isOperator(src.charAt(0))){
//                return "Invalid input data";
//            }
//        }

        if(src.length()<=pos) { 										//base case, returns string as is.			
            return "";
        }
        if(src.length()<=pos+1){
            return ""+src.charAt(pos);
        }

        //System.out.println(src+" : "+pos);
        char c = src.charAt(pos); // /
        
        if(isOperator(c)){												//if character is an operator
            String op1 = prefixToPostFix(src,pos+1)+c;					//storing operator (argument1) 2
            String op2 = prefixToPostFix(src, pos+op1.length());		//storing operand (argument2)
            
            if(op1.length()==2){
                return op1.charAt(0)+op2+c;
            }
            return op1 + op2;											//return concatenated string
        }

        else {
            if(pos<src.length()-1){
                if( isOperator(src.charAt(pos+1)))
                    return ""+c;
                else 
                	return ""+c+src.charAt(pos+1);

            }
            else return ""+c+src.charAt(pos+1);
            
        }
	}
	
	public static boolean isOperator(char c){
		if(c =='+' || c =='-' || c =='*' || c =='/' || c =='$' || c =='^'){
			return true;
		}
		return false;
	}
	
	
	static String preToPost1(String src)
	{
	    StringBuilder dest = new StringBuilder(src.length());
	    for(int pos=0; pos < src.length(); pos = preToPost1(dest, src, pos));
	    return dest.toString();
	}
  
  //method to check answers only
	static int preToPost1(StringBuilder dest, String src, int pos)
	{
	    if (pos >= src.length())
	    {
	        //no expression
	        return pos;
	    }
	    char c = src.charAt(pos++);
	    if ("+-/*".indexOf(c)>=0)
	    {
	        //operator. Convert both operands first
	        pos = preToPost1(dest, src, pos);
	        pos = preToPost1(dest, src, pos);
	    }
	    dest.append(c);
	    return pos;
	}
	
}
