package HW1_package;

//import java.util.Scanner;
import java.util.*;


public class arithmeticCalculator 
{
	
	private static final boolean debug = false;	// ean theloume na exoume serial debug me printf

	public static void main(String[] args)
	{
		
		int bracket_count = 0, bracket_second_count = 0 ;
		boolean specificChar = true;
		char first_char, last_char;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Expression: ");
		
		String input_first = sc.nextLine();
		
		//sc.close();
		
		if(debug)
			System.out.println(input_first);
		
		/* Check if there is correct number of brackets */
		for(int i = 0; i < input_first.length(); i++)
		{
			if(input_first.charAt(i) == '(')
				bracket_count++;
		}
		
		if(debug)
			System.out.println(bracket_count);
		
		for(int i = 0; i < input_first.length(); i++)
		{
			if(input_first.charAt(i) == ')')
				bracket_second_count++;
		}
		
		if(debug)
			System.out.println(bracket_second_count);
		
		if(bracket_count > bracket_second_count)
		{
			System.out.println("[ERROR] Not closing opened parenthesis\n");
			return;
		}
		else if (bracket_count < bracket_second_count)
		{
			System.out.println("[ERROR] Closing unopened parenthesis\n");
			return;
		}
		
		
		/* Check if there are two numbers in a row */
		/* Maybe i need to predict if there are more than one spaces between characters */
		if(input_first.matches(".*[0-9.][ ][0-9.].*"))
		{
			System.out.println("[ERROR] Consecutive operands\n");
			return;
		}
		
		//replace all spaces
		String input = input_first.replaceAll("\\s+","");
		
		/* Check if there are only specific characters */
		specificChar = input.matches("[\\+\\-\\*/x0-9.()^ ]+");
		
		if(debug)
			System.out.println(specificChar);
		
		if(!specificChar)
		{
			System.out.println("[ERROR] Invalid character\n");
			return;
		}
		
		/* Check if there is two operators in a row */
		if(input.matches(".*[\\+\\-\\*x/][\\+\\-\\*x/].*") )
		{
			System.out.println("[ERROR] Consecutive operators\n");
			return;
		}
		else if (input.matches(".*[\\+\\-\\*x/^][ ][\\+\\-\\*x/^].*"))
		{
			System.out.println("[ERROR] Consecutive operators\n");
			return;
		}
		
		
		first_char = input.charAt(0);
		last_char = input.charAt(input.length() - 1);
		if( (first_char == '+') || (first_char == '-')  || (first_char == '*') || (first_char == 'x') || (first_char == '/') )
		{
			System.out.println("[ERROR] Starting or ending with operator");
			return;
		}
		else if ( (last_char == '+') || (last_char == '-')  || (last_char == '*') || (last_char == 'x') || (last_char == '/') )
		{
			System.out.println("[ERROR] Starting or ending with operator");
			return;
		}
		
		/* Check if there is operator before or after closing or opening a parenthesis */
		if(input.matches(".*[\\+\\-\\*/x^][)].*"))
		{
			System.out.println("Operator appears before closing parenthesis");
			return;
		}
		else if (input.matches(".*[(][\\+\\-\\*/x].*") )
		{
			System.out.println("Operator appears after opening parenthesis");
			return;
		}
		
		/* Check if there is character ')' before character '(' */
		for (int i = 0; i < input.length(); i++)
		{
			char current_char = input.charAt(i);
			
			if(current_char == '(')
				break;
			else if (current_char == ')')
			{
				System.out.println("[ERROR] ')' appears before opening parenthesis\n" );
				break;
			}
		}
		
		
		if(input.matches(".*[)0-9.][(].*"))
		{
			System.out.println("[ERROR] Operand before opening parenthesis\n");
			return;
		}
		else if (input.matches(".*[)][(0-9.].*"))
		{
			System.out.println("[ERROR] Operand after closing parenthesis\n");
			return;
		}
		
		
		
		
		// If string is ok after checks, do the following
		
//		int flag = 0, pos = 0;
//		for(int i = 0; i < input.length(); i++)
//		{
//			char current = input.charAt(i);
//			if(current == '(')
//				flag = flag + 1;
//			else if (current == ')')
//				flag = flag -1;
//			else if (flag == 0)
//			{
//				if(current == '+' || current == '-' )
//				{
//					//pos = new_pos;
//					//new_pos = i;
//					pos = i;
//					//System.out.println("The pos is " + pos );	// kanei print to dejiotero + or - 
//				}
//					
//			}
//		}
//		
//		System.out.println("The pos is " + pos );
//		
//		String leftString = input.substring(0, pos);
//		String rightString = input.substring(pos+1, input.length()-1);
//		
//		if(debug)
//		{
//			System.out.println("leftString is "+ leftString +" --- " + "rightString is " + rightString);
//		}

		Tree_create newTree;
		newTree = new Tree_create(5);
		newTree.root =  newTree.insert(input);
		
		//Tree_create.printInOrder(newTree.root);
		
		//StringBuilder sb = new StringBuilder();
//        sb.append("digraph {\n");
//        //sb.append(buildGraphvizTree(newTree.root));
//        sb.append(Tree_create.buildGraphvizTree(newTree.root));
//        sb.append("}\n");
//        System.out.println(sb.toString());
        
//        double result = 0;
//        result = Tree_create.calculate(newTree.root);
//        System.out.println("The result is " + result);
	       	
        	
        double result = 0;
        StringBuilder sb = new StringBuilder();
        //Scanner scanner = new Scanner(System.in);
    	while(true)
    	{
    		char c = sc.nextLine().charAt(0);
    		//scanner.close();
        	switch(c)
        	{
        		case 'c':
        		{
        	        //double result = 0;
        			System.out.println("\nResult: ");
        	        result = Tree_create.calculate(newTree.root);
        	        System.out.println(result+"\n");
        	        break;
        		}
        		
        		case 'd':
        		{
        			//StringBuilder sb = new StringBuilder();
        	        sb.append("digraph {\n");
        	        //sb.append(buildGraphvizTree(newTree.root));
        	        sb.append(Tree_create.buildGraphvizTree(newTree.root));
        	        sb.append("}\n");
        	        System.out.println(sb.toString());
        	        break;
        		}
        		default:
        		{
        			System.out.println("Elousa\n");
        	    	//sc.close();
        			return;
        		}
        	}
    	}		

	}
}
