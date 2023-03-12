package ce326.hw1;

// Class that creates the Tree
public class Tree_create
{
	public tree_node root;
	int nodeCount = 0;
	String new_input = "";		// Input from User 
	
	public Tree_create()		// constructor
	{
		root = null;
	}
	
	/* First insert for root */
	public tree_node insert(String input)
	{
		tree_node root = new tree_node(0, '0');
		root = instertNode(root, input);

		return root;
	}
	
	/* Insert for each node of tree */
	public tree_node instertNode(tree_node node, String input)
	{
		int flag = 0, pos = -1;
		
		if( (input.length() == 0) )		// if string is empty return
			return null;
		
		else
		{
			/* Check if there are + or - outside of parenthesis */
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
			
			/* if there are not anymore + or - checks for * and / outside of parenthesis */
			if(pos <= 0)
			{
				pos = -1;		// initialize pos < 0 and it will change only if there are * or /
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
							pos = i;
						}
						
					}
				}
			}
			
			/* if there are not anymore * or / checks for ^ outside of parenthesis */
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
							pos = i;
						}
					}
				}
			}
			
			/* if there are not any operators outside of parenthesis, it starts to check inside of them */
			
			if(pos <= 0)
			{
				int is_parenthesis = 0;
				new_input = "";
				pos = -1;
				for(int i = 0; i < input.length(); i++)
				{
					char current = input.charAt(i);
					if(current == '(')
					{
						flag = flag + 1;
						if(is_parenthesis != 1)
						{			
							is_parenthesis = 1;
							i = i + 1;		// We increase variable i to use string inside parenthesis
							if(input.charAt(i) == '(')
								flag = flag + 1;
						}
					}
					else if (current == ')')
						flag = flag -1;

					if(flag == 0)
						is_parenthesis = 0;
					
					if(is_parenthesis == 1)
					{
						new_input = new_input + input.charAt(i);	// we copy string of first parenthesis in input. So it is the new input
					}			
				}
			}
			
			if(new_input != "")		// if it is not empty it means that there is parenthesis
			{
				input = new_input;
				node = instertNode(node, input);		// again the same for same node but with input the string that was inside parenthesis
			}
			
			/* if we find operator we separate left and right string of it in two new strings */
			String leftString;
			if(pos < 0)
			{
				leftString = input.substring(0, 0);
			}
			else
			{
				leftString = input.substring(0, pos);
			}
			
			//String rightString = "";

			String rightString = input.substring(pos+1, input.length());

			if(node == null)
			{
				if(pos < 0)
				{
					String double_number = "";	// i need to store the string that contains the value so that i can convert it to double
					int j = 0;
					
					node = new tree_node(0, '\0');
					
					while( j < input.length() && (input.charAt(j) != '+' &&  input.charAt(j) != '-' && input.charAt(j) != '*' && input.charAt(j) != '/' && input.charAt(j) != 'x' && input.charAt(j) != '^' &&  input.charAt(j) != '(' && input.charAt(j) != ')' ) )
					{
						double_number = double_number + input.charAt(j);
						j++;
					}
					if(double_number.length() != 0 )
					{
						node.data = Double.parseDouble(double_number);
					}
				}	
			
				else		// it is operator
					node = new tree_node(0, input.charAt(pos));
			}
			
			if(pos < 0)
			{
				String double_number = "";
				int j = 0;
				
				while( (j < input.length()) && (input.charAt(j) != '+' &&  input.charAt(j) != '-' && input.charAt(j) != '*' && input.charAt(j) != '/' && input.charAt(j) != 'x' && input.charAt(j) != '^' && input.charAt(j) != '(' && input.charAt(j) != ')' ) )
				{
					double_number = double_number + input.charAt(j);
					j++;
				}
				if(double_number.length() != 0 )
				{
					node.data = Double.parseDouble(double_number);
				}
			}
			else
				node.operator = input.charAt(pos);
			
			if(pos >= 0)		// recursive method 
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
	}

	
	
	 public String buildGraphvizTree(tree_node node) 
	 {
		 	
        StringBuilder graph = new StringBuilder();
        if (node != null) 
        {
            int nodeId = nodeCount++;
            graph.append("\tnode").append(nodeId).append(" [label=\"");
            if (node.operator != '\0') 
            {
                graph.append(node.operator);
            } 
            else 
            {
                graph.append(node.data);
            }
            graph.append("\"];\n");
            
            if (node.left != null) 
            {
                graph.append("\tnode").append(nodeId).append(" -> ");
                graph.append("node").append(nodeCount).append(";\n");
                graph.append(buildGraphvizTree(node.left));
            }
            
            if (node.right != null) 
            {
                graph.append("\tnode").append(nodeId).append(" -> ");
                graph.append("node").append(nodeCount).append(";\n");
                graph.append(buildGraphvizTree(node.right));
            } 
        }
        return graph.toString();
    }
	 
	 
	 public double evaluate(tree_node node)
	 {		 
		 if (node == null) 
		 {
			 return 0;
	     }

        if (node.left == null && node.right == null) 
        {
            return node.data;
        }

        double leftResult = evaluate(node.left);
        double rightResult = evaluate(node.right);

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
                //System.out.println("No operator /n");
        }
        return 800; // just to return a value for no warning
    }
	 
	 
	 
	 public void Postfix(tree_node node)
	 {		 
		 if(node == null)
			 return;
		 
		 Postfix(node.left);
		 
		 Postfix(node.right);
		 
		 if(node.operator != '+' && node.operator != '-' && node.operator != '^' && node.operator != 'x' && node.operator != '/' && node.operator != '*' )
		 {
			 if(node.data == Math.floor(node.data))
			 {
				 System.out.print((int)node.data + " ");
			 }
			 else
				 System.out.print(node.data + " ");
		 }
		 else
		 {
			 System.out.print(node.operator + " "); 
		 }
	 } 
} 
