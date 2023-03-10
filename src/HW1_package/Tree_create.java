package HW1_package;

public class Tree_create
{
	public tree_node root;
	static int nodeCount = 0;
	String new_input = "";
	
	public Tree_create(int value)
	{
		root = null;
	}

	public tree_node insert(String input)
	{
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
//		//if(debug)
//		//{
//			System.out.println("leftString is "+ leftString +" --- " + "rightString is " + rightString);
//		//}
//		//root.operator = input.charAt(pos);
//		tree_node root = new tree_node(0, input.charAt(pos-1));
		tree_node root = new tree_node(0, '0');
		root = instertNode(root, input);
		//if(root != null)
		//	root = instertNode(root, input);
		return root;
	}
	
	public tree_node instertNode(tree_node node ,String input)
	{
		
		int flag = 0, pos = -1;
		
		if( (input.length() == 0) )		// gia na jerei ena to length einai 0 i 1
			return null;
		
		else
		{
			for(int i = 0; i < input.length(); i++)
			{
				new_input = "";
				char current = input.charAt(i);
				if(current == '(')
					flag = flag + 1;
				else if (current == ')')
					flag = flag -1;
				else if (flag == 0)
				{
					if(current == '+' || current == '-' )
					{
						pos = i;
					}
					
				}
			}
			/* Exei teleiosei apo + - kai paei sta * kai / */
			if(pos <= 0)
			{
				pos = -1;
				new_input = "";
				for(int i = 0; i < input.length(); i++)
				{
					char current = input.charAt(i);
					if(current == '(')
						flag = flag + 1;
					else if (current == ')')
						flag = flag -1;
					else if (flag == 0)
					{
						if(current == '*' || current == '/' || current == 'x' )
						{
							//pos = new_pos;
							//new_pos = i;
							pos = i;
							//System.out.println("The pos is " + pos );	// kanei print to dejiotero + or - 
						}
						
					}
				}
			}
			
			/* Exei teleiosei apo * kai / kai paei sto ^ */
			if(pos <= 0)
			{
				pos = -1;
				new_input = "";
				for(int i = 0; i < input.length(); i++)
				{
					char current = input.charAt(i);
					if(current == '(')
						flag = flag + 1;
					else if (current == ')')
						flag = flag -1;
					else if (flag == 0)
					{
						if(current == '^')
						{
							//pos = new_pos;
							//new_pos = i;
							pos = i;
							//System.out.println("The pos is " + pos );	// kanei print to dejiotero + or - 
						}
						
					}
				}
			}
			
			
			// jekiname gia tis parenthesis 
			
			if(pos <= 0)
			{
				int is_parenthesis = 0;
				new_input = "";
				pos = -1; 		// gia na jana mpei stin sinartisi
				for(int i = 0; i < input.length(); i++)
				{
					char current = input.charAt(i);
					if(current == '(')
					{
						flag = flag + 1;
//						is_parenthesis = 1;
						if(is_parenthesis != 1)
						{			
							is_parenthesis = 1;
							i = i + 1;
							if(input.charAt(i) == '(')
								flag = flag + 1;
						}
					}
					else if (current == ')')
						flag = flag -1;
					//else if (flag == 0)
					//{
						//is_parenthesis = 0;
//						if(current == '^')
//						{
//							pos = i;
//						}
						
					//}
					if(flag == 0)
						is_parenthesis = 0;
					
					if(is_parenthesis == 1)
					{
						new_input = new_input + input.charAt(i);
					}
					
					System.out.println("The new input is " + new_input);					
				}
			}
			
			if(new_input != "")
			{
				input = new_input;
				node = instertNode(node, input);
			}
			
			
			
			System.out.println("The pos is " + pos );
			String leftString;
			if(pos < 0)
			{
				leftString = input.substring(0, 0);
			}
			else
			{
				//if(new_input != "")
				//	leftString = new_input;
				//else
					leftString = input.substring(0, pos);
			}
			//if(input.length() >= 1)
			String rightString = "";
			//if(new_input != "")
				//rightString = new_input;
			//else
			//{
				rightString = input.substring(pos+1, input.length());
				//node.right = instertNode(node.right, rightString);
			//}
				
			
			//if(debug)
			//{
			System.out.println("leftString is "+ leftString +" --- " + "rightString is " + rightString);
			//}
			
	//		if(node == null)
	//			return new tree_node(0, input.charAt(pos - 1));
	//		else
	//		{
	//			
	//			node.operator = input.charAt(pos - 1);
	//			node.left = instertNode(node.left, leftString);
	//			node.right = instertNode(node.right, rightString);
	//		}
			if(node == null)
			{
				//return new tree_node(0, input.charAt(pos));
				//return null;
				if(pos < 0)
				{
					String double_number = "";
					int j = 0;
					
					node = new tree_node(0, '\0');
					
					while( j < input.length() && (input.charAt(j) != '+' &&  input.charAt(j) != '-' && input.charAt(j) != '*' && input.charAt(j) != '/' && input.charAt(j) != 'x' && input.charAt(j) != '^' &&  input.charAt(j) != '(' && input.charAt(j) != ')' ) )
					{
						double_number = double_number + input.charAt(j);
						j++;
					}
					if(double_number.length() != 0)
	 					node.data = Double.parseDouble(double_number);
				}	
			
				else
					node = new tree_node(0, input.charAt(pos));
			}
			//else
			if(pos < 0)
			{
				String double_number = "";
				int j = 0;
				
				while( (j < input.length()) && (input.charAt(j) != '+' &&  input.charAt(j) != '-' && input.charAt(j) != '*' && input.charAt(j) != '/' && input.charAt(j) != 'x' && input.charAt(j) != '^' && input.charAt(j) != '(' && input.charAt(j) != ')' ) )
				{
					double_number = double_number + input.charAt(j);
					System.out.println("double number is " + double_number);
					j++;
				}
				if(double_number.length() != 0)
 					node.data = Double.parseDouble(double_number);
			}
			else
				node.operator = input.charAt(pos);
			
			if(pos >= 0)	// mporei na thelei >= 1
			{				
				//node.left = new tree_node(0, input.charAt(pos));
				node.left = instertNode(node.left, leftString);
				//node.right = new tree_node(0, input.charAt(pos));
				node.right = instertNode(node.right, rightString);
			}
			
			return node;
		} 
		

	}
	
	public static void printInOrder(tree_node root) 
	{
        if (root != null) 
        {
            printInOrder(root.left);
            System.out.println(root.operator + " ");
            printInOrder(root.right);
        }
//        else
//        {
//        	System.out.println("einai NULL\n ");
//        }
	}

	
	
	 public static String buildGraphvizTree(tree_node node) {
		 
		 	
	        StringBuilder sb = new StringBuilder();
	        if (node != null) {
	            int nodeId = nodeCount++;
	            sb.append("\tnode").append(nodeId).append(" [label=\"");
	            if (node.operator != '\0') {
	                sb.append(node.operator);
	            } else {
	                sb.append(node.data);
	            }
	            sb.append("\"];\n");
	            
	            if (node.left != null) {
	                sb.append("\tnode").append(nodeId).append(" -> ");
	                sb.append("node").append(nodeCount).append(";\n");
	                sb.append(buildGraphvizTree(node.left));
	            }
	            
	            if (node.right != null) {
	                sb.append("\tnode").append(nodeId).append(" -> ");
	                sb.append("node").append(nodeCount).append(";\n");
	                sb.append(buildGraphvizTree(node.right));
	            } 
	        }
	        return sb.toString();
	    }
	 
	 
	 public static double calculate(tree_node node)
	 {		 
		 if (node == null) 
		 {
			 return 0;
	     }

        if (node.left == null && node.right == null) 
        {
            return node.data;
        }

        double leftResult = calculate(node.left);
        double rightResult = calculate(node.right);

        switch (node.operator) 
        {
            case '+':
                return leftResult + rightResult;
            case '-':
                return leftResult - rightResult;
            case '*':
                return leftResult * rightResult;
            case 'x':
            	return leftResult * rightResult;
            case '/':
                return leftResult / rightResult;
            case '^':
            	return Math.pow(leftResult, rightResult);
            default:
                System.out.println("No operator /n");
        }
        return 800; // apla gia na min exo error 
    }
 
} 
