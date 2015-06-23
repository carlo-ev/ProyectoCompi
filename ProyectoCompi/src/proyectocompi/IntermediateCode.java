/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Jorge
 */
public class IntermediateCode {
    private SymbolTable symbolTable;
    public ArrayList<IntermediateNode> interTable = new ArrayList();
    private int tempCount = 0;
    private final Pattern operators = Pattern.compile("\\+|\\-|\\*|\\/|\\%");
    
    public void traverseTree(TreeNode statements)
    {
        
        String result = "";
        IntermediateNode interNode;
        
        for (int i = 0; i < statements.childs.size(); i++) {
            TreeNode statement = statements.childs.get(i);
            TreeNode currentStatement = statement;
            if(statement.operation.equals("Declare ID"))
            {
                currentStatement = statement.childs.get(0);
                if(currentStatement.operation == "=")
                {
                    //Recorrer
                    result = currentStatement.childs.get(0).operation;
                    currentStatement = currentStatement.childs.get(1);
                    if(currentStatement.operation.matches(operators.pattern()))
                    {
                        System.out.println("Entering Operator");
                        String temp = getTemp(currentStatement);
                        interNode = new IntermediateNode("=", temp, "", result);
                        interTable.add(interNode);
                    }

                    else
                    {
                        tempCount++;
                        String temp = "t" + tempCount;

                        interNode = new IntermediateNode("=", currentStatement.operation, "", temp);
                        interTable.add(interNode);
                        interNode = new IntermediateNode("=", temp, "", result);
                        interTable.add(interNode);

                    }
                }
            }
            
            else if(statement.operation.equals("="))
            {
                //Recorrer
                result = currentStatement.childs.get(0).operation;
                currentStatement = currentStatement.childs.get(1);
                if(currentStatement.operation.matches(operators.pattern()))
                {
                    //Recorrer
                    System.out.println("Entering Operator");
                    getTemp(currentStatement);
                }
                
                else
                {
                    tempCount++;
                    String temp = "t" + tempCount;
                    
                    interNode = new IntermediateNode("=", currentStatement.operation, "", temp);
                    interTable.add(interNode);
                    interNode = new IntermediateNode("=", temp, "", result);
                    interTable.add(interNode);
                }
            }
            
            else if(statement.operation.equals("act"))
            {
                currentStatement = statement.childs.get(0);// Function name
                interNode = new IntermediateNode(currentStatement.operation, "", "", "");
                interTable.add(interNode);
                
                currentStatement = statement.childs.get(2);// Function Body Statements
                if(currentStatement.operation != "")
                    traverseTree(currentStatement);
                currentStatement = statement.childs.get(3);// Function return value
                
                if(currentStatement.operation != "")
                    interNode = new IntermediateNode("ret", currentStatement.childs.get(0).operation, "", "");
                else
                    interNode = new IntermediateNode("ret", "", "", "");
                
                interTable.add(interNode);
            }
            
            else if(statement.operation.equals("Call Function"))
            {
                int paramCount = statement.childs.get(0).childs.size();
                
                for (int j = 0; j < paramCount; j++) {
                    currentStatement = statement.childs.get(0);// Function parameters
                    currentStatement = currentStatement.childs.get(j);// Function parameter value
                    System.out.println("For operation is: " + currentStatement.operation);
                    
                    if(currentStatement.type != null)
                    {
                        tempCount++;
                        String temp = "t" + tempCount;
                        interNode = new IntermediateNode("=", currentStatement.operation, "", temp);
                        interTable.add(interNode);
                        interNode = new IntermediateNode("param", temp, "", "");
                        interTable.add(interNode);
                    }
                    
                    else
                    {
                        interNode = new IntermediateNode("param", currentStatement.operation, "", "");
                        interTable.add(interNode);
                    }
                }
                currentStatement = statement.childs.get(0);// Function parameters
                interNode = new IntermediateNode("call", statement.type, Integer.toString(currentStatement.childs.size()), "");
                interTable.add(interNode);
                
            }
        }
        
        printInterTable();
    }
    
    public String getTemp(TreeNode statement)
    {
        TreeNode currentStatement;
        IntermediateNode interNode;
        String operator1 = statement.childs.get(0).operation;
        String operator2 = statement.childs.get(1).operation;
        
        currentStatement = statement.childs.get(0);
         if(currentStatement.operation.matches(operators.pattern()))
            operator1 = getTemp(currentStatement);
         
         currentStatement = statement.childs.get(1);
         
         if(currentStatement.operation.matches(operators.pattern()))
             operator2 = getTemp(currentStatement);
         
        tempCount++;
        String temp = "t" + tempCount;
        System.out.println("");
        interNode = new IntermediateNode(statement.operation, operator1, operator2, temp);
        interTable.add(interNode);
        return temp;
    }
    
    public void printInterTable()
    {
        System.out.println("Intermediate Table");
        
        for (int i = 0; i < interTable.size(); i++) {
            IntermediateNode node = interTable.get(i);
            System.out.println("|" + node.operator + "|" + node.operandum1 + "|" + node.operandum2 + "|" + node.result);
        }
    }
}
