package ce326.hw1;

import java.util.*;


public class ArithmeticCalculator
{
	
	private static final boolean debug = false;	// for debug with println

	public static void main (String[] args)
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
		
		input_first = input_first.replaceAll("( {2,})", " ");	// if there are more than one spaces it makes them one
		
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
		
		if(input_first.matches(".*[0-9.][ ][0-9.].*"))
		{
			System.out.println("[ERROR] Consecutive operands\n");
			return;
		}
		else if(input_first.matches(".*[0-9.][ ][ ][0-9.].*"))
		{
			System.out.println("[ERROR] Consecutive operands\n");
			return;
		}
		else if(input_first.matches(".*[.][0-9][.].*"))			// for cases that numbers has format x.y.z 
		{
			System.out.println("[ERROR] Consecutive operands\n");
		}

		else if(input_first.matches(".*[.][.].*"))
		{
			System.out.println("[ERROR] Consecutive operands\n");
			return;
		}
		
		int dot = 0;
		for(int i = 0; i < input_first.length(); i++)
		{
			if(input_first.charAt(i) == '.')
				dot = dot + 1;
			
			else if(input_first.charAt(i) == '+' || input_first.charAt(i) == '-' || input_first.charAt(i) == '*' || input_first.charAt(i) == 'x'|| input_first.charAt(i) == '/'|| input_first.charAt(i) == '^'|| input_first.charAt(i) == ')' || input_first.charAt(i) == '(' )
				dot = 0;
			
			if(dot > 1)
			{
				System.out.println("[ERROR] Consecutive operands\n");
				return;
			}
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
		if( (first_char == '+') || (first_char == '-')  || (first_char == '*') || (first_char == 'x') || (first_char == '/') || (first_char == '^') )
		{
			System.out.println("[ERROR] Starting or ending with operator\n");
			return;
		}
		else if ( (last_char == '+') || (last_char == '-')  || (last_char == '*') || (last_char == 'x') || (last_char == '/') || (first_char == '^'))
		{
			System.out.println("[ERROR] Starting or ending with operator\n");
			return;
		}
		
		/* Check if there is operator before or after closing or opening a parenthesis */
		if(input.matches(".*[\\+\\-\\*/x^][)].*"))
		{
			System.out.println("[ERROR] Operator appears before closing parenthesis\n");
			return;
		}
		else if (input.matches(".*[(][\\+\\-\\*/x].*") )
		{
			System.out.println("[ERROR] Operator appears after opening parenthesis\n");
			return;
		}
		
		/* Check if there is character ')' before character '(' */
		int flag = 0;
		for (int i = 0; i < input.length(); i++)
		{
			char current_char = input.charAt(i);
			
			if(current_char == '(')
			{
				flag = flag + 1;
			}
			else if (current_char == ')')
			{
				flag = flag - 1;
			}
			
			if(current_char == ')' && flag < 0)
			{
				System.out.println("[ERROR] ')' appears before opening parenthesis\n" );
				return;
			}
				
		}
		
		if(input.matches(".*[)][(].*"))
		{
			System.out.println("[ERROR] ')' appears before opening parenthesis\n");
			return;
		}
		else if(input.matches(".*[)][ ][(].*"))
		{
			System.out.println("[ERROR] ')' appears before opening parenthesis\n");
			return;
		}

		
		/* check if there is opernad before or after parenthesis */
		if(input.matches(".*[0-9.][(].*"))
		{
			System.out.println("[ERROR] Operand before opening parenthesis\n");
			return;
		}
		else if (input.matches(".*[)][0-9.].*"))
		{
			System.out.println("[ERROR] Operand after closing parenthesis\n");
			return;
		}
		
		
		
		/* After completing checks we make the tree */
		ArithmeticCalculator arithmeticObject = new ArithmeticCalculator(input);       	
        	
        double result = 0;
        StringBuilder graphPrint = new StringBuilder();
    	while(true)
    	{
    		String c = sc.nextLine();
        	switch(c)
        	{
        		case "-s":
        		{
        			System.out.print("\nPostfix: ");
        			arithmeticObject.kati();
        			System.out.print("\n");
        			return;
        		}
        	
        		case "-c":
        		{
        			result = arithmeticObject.calculate();
        			System.out.printf("\nResult: %.6f\n", result);
        			return;
        		}
        		
        		case "-d":
        		{
        	        graphPrint.append("digraph TreeGraph {\n");
        	        graphPrint.append(arithmeticObject.toDotString());
        	        graphPrint.append("}\n");
        	        System.out.println(graphPrint.toString());
        	        return;
        		}
        		default:	// waits for valid input
        		{
        			break;
        		}
        	}
    	}		

	}


		
	Tree_create newTree;
	public ArithmeticCalculator (String input)
	{
		newTree = new Tree_create();
		newTree.root =  newTree.insert(input);
	}
	
	
	
	public String toDotString()
	{
		String dotText;
		dotText = newTree.buildGraphvizTree(newTree.root);
		
		return dotText;
	}
	
	public double calculate()
	{
		double result;
		result = newTree.evaluate(newTree.root);
		
		return result;
	}
	
	public void kati()
	{
		newTree.Postfix(newTree.root);
	}
		
}
