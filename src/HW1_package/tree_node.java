package HW1_package;

public class tree_node 
{
	double data;
	char operator;
	tree_node parent, left, right; 
	
	// Constructor
	public tree_node (int initData, char initOperator)
	{
		data = initData;
		operator = initOperator;
		//parent = initParent;
	}
		
	
	void setValue(int value)
	{
		data = value;
	}
	
	void setOperator(char operator_value)
	{
		operator = operator_value;
	}
	
	double getValue()
	{
		return data;
	}
	
	char getOperator()
	{
		return operator;
	}
	
	
}
