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
    private final Pattern logicalOperators = Pattern.compile("\\==|\\>|\\<|\\>=|\\<=\\!=");
    
    final String con = "if";
    final String til = "while";
    final String rep = "for";
    final String set = "switch";
    final String opt = "case";
    final String trueTag = "trueTag";
    final String falseTag = "falseTag";
    final String argTag = "arg";
    
    int logResCount = 0;
    int argCount = 0;
    int conCount = 0;
    int tilCount = 0;
    int repCount = 0;
    int setCount = 0;
    int optCount = 0;
    
    boolean yet = false;
    String nextConcat = "";
    String nextAnd = "";
    String nextOr = "";
    int concatSize = 0;
    
    public void traverseTree(TreeNode statements)
    {
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
                    assignmentConstruction(currentStatement);
                }
            }
            
            else if(statement.operation.equals("="))
            {
                //Recorrer
                assignmentConstruction(currentStatement);
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
                TreeNode functionName = statement.childs.get(0);// Name of function
                int paramCount = functionName.childs.get(0).childs.size(); //Number of parameters
                
                for (int j = 0; j < paramCount; j++) {
                    currentStatement = functionName.childs.get(0);// Function parameters
                    currentStatement = currentStatement.childs.get(j);// Function parameter value
                    System.out.println("For operation is: " + currentStatement.operation);
                    
                    if(currentStatement.type != null)
                    {
                        tempCount++;
                        String temp = "$t" + tempCount;
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
                interNode = new IntermediateNode("call", functionName.operation, Integer.toString(paramCount), "");
                interTable.add(interNode);
            }
            
            else if(statement.operation.equals("con"))
            {
                conCount++;// Increments the count for the condition tag everytime a new condition is found
                logResCount++;
                interNode = new IntermediateNode(con + conCount, "", "", "");
                interTable.add(interNode);
                
                currentStatement = statement.childs.get(0);// Arguments of condition
                yet = statement.childs.get(2).operation.equals("yet");
                logicalExpressionConstruction(currentStatement);
                
                currentStatement = statement.childs.get(1);// Condition body
                interNode = new IntermediateNode(trueTag + logResCount, "", "", "");
                interTable.add(interNode);
                traverseTree(currentStatement);
                interNode = new IntermediateNode("goto " + falseTag + logResCount, "", "", "");
                interTable.add(interNode);
                currentStatement = statement.childs.get(2);
                if(yet)
                {
                    interNode = new IntermediateNode("yet" + conCount, "", "", "");
                    interTable.add(interNode);
                    traverseTree(currentStatement.childs.get(0));// Yet body
                    
                    interNode = new IntermediateNode("goto " + falseTag + logResCount, "", "", "");
                    interTable.add(interNode);
                }
                
                interNode = new IntermediateNode(falseTag + logResCount, "", "", "");
                interTable.add(interNode);
                    
            }
            
            else if(statement.operation.equals("til"))
            {
                tilCount++;
                logResCount++;
                interNode = new IntermediateNode(til + tilCount, "", "", "");
                interTable.add(interNode);
                
                currentStatement = statement.childs.get(0);// Arguments of while
                logicalExpressionConstruction(currentStatement);
                
                currentStatement = statement.childs.get(1);// Til body
                interNode = new IntermediateNode(trueTag + logResCount, "", "", "");
                interTable.add(interNode);
                traverseTree(currentStatement);
                interNode = new IntermediateNode("goto " + til + tilCount, "", "", "");
                interTable.add(interNode);
                interNode = new IntermediateNode(falseTag + logResCount, "", "", "");
                interTable.add(interNode);
            }
            
            else if(statement.operation.equals("rep"))
            {
                repCount++;
                logResCount++;
                interNode = new IntermediateNode(rep + repCount, "", "", "");
                interTable.add(interNode);
                /*>>>>>>>>>> First Assignment Construction <<<<<<<<<<*/
                String retArgument = argTag + argCount;
                currentStatement = statement.childs.get(0);
                assignmentConstruction(currentStatement);
                /*>>>>>>>>>> Logical Expression Construction <<<<<<<<<<*/
                currentStatement = statement.childs.get(1);// Logical Expression of for
                logicalExpressionConstruction(currentStatement);
                /*>>>>>>>>>> For Body Construction <<<<<<<<<<*/
                currentStatement = statement.childs.get(3);// Til body
                interNode = new IntermediateNode(trueTag + logResCount, "", "", "");
                interTable.add(interNode);
                traverseTree(currentStatement);
                /*>>>>>>>>>> Second Assignment Construction <<<<<<<<<<*/
                currentStatement = statement.childs.get(2);
                assignmentConstruction(currentStatement);
                interNode = new IntermediateNode("goto " + retArgument, "", "", "");
                interTable.add(interNode);
                interNode = new IntermediateNode(falseTag + logResCount, "", "", "");
                interTable.add(interNode);
            }
            
            else if(statement.operation.equals("set"))
            {
                String setArg = "unassigned";
                int optTot = 0;
                setCount++;
                interNode = new IntermediateNode(set + setCount, "", "", "");
                interTable.add(interNode);
                currentStatement = statement.childs.get(0);// Gets the condition argument to compare with the options/cases.
                tempCount++;
                setArg = "$t" + tempCount;
                interNode = new IntermediateNode("=", currentStatement.operation, "", "$t" + tempCount);
                interTable.add(interNode);
                
                currentStatement = statement.childs.get(1);// Gets the options/cases.
                optTot = currentStatement.childs.size();
                boolean any = statement.childs.get(2).operation.equals("any");
                traverseOptions(currentStatement,  setArg, optTot, any);
                
                currentStatement = statement.childs.get(2);//get Any/Default value for set/switch statement.
                
                if(any)
                {
                    interNode = new IntermediateNode("any" + setCount, "", "", "");
                    interTable.add(interNode);
                    
                    currentStatement = currentStatement.childs.get(0);// Statements of any/default.
                    traverseTree(currentStatement);
                    interNode = new IntermediateNode("goto " + set + "False" + setCount, "", "", "");
                    interTable.add(interNode);
                }
                interNode = new IntermediateNode(set + "False" + setCount, "", "", "");
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
        String temp = "ündefined";
        
        
        currentStatement = statement.childs.get(0);
         if(currentStatement.operation.matches(operators.pattern()))
            operator1 = getTemp(currentStatement);
         
        if(statement.childs.get(0).childs.size() != 0)
        {
            if(currentStatement.childs.get(0).operation.equals("Params"))
            {
                TreeNode callFunction = new TreeNode("Statement", new TreeNode("Call Function",new TreeNode(currentStatement.operation , currentStatement.childs.get(0))));
                System.out.println("The current statement is: " + callFunction.operation);
                traverseTree(callFunction);
                
                tempCount++;
                temp = "$t" + tempCount;
                interNode = new IntermediateNode("=", currentStatement.operation, "", temp);
                interTable.add(interNode);
                operator1 = temp;
            }
        }
        
        if(statement.childs.get(1).childs.size() != 0)
        {
            if(statement.childs.get(1).childs.get(0).operation.equals("Params"))
            {
                TreeNode callFunction = new TreeNode("Statement", new TreeNode("Call Function",new TreeNode(statement.childs.get(1).operation , statement.childs.get(1).childs.get(0))));
                System.out.println("The current statement is: " + callFunction.operation);
                traverseTree(callFunction);
                
                tempCount++;
                temp = "$t" + tempCount;
                interNode = new IntermediateNode("=", statement.childs.get(1).operation, "", temp);
                interTable.add(interNode);
                operator2 = temp;
            }
        }
         
         currentStatement = statement.childs.get(1);
         
         if(currentStatement.operation.matches(operators.pattern()))
             operator2 = getTemp(currentStatement);
        
        tempCount++;
        temp = "$t" + tempCount; 
        interNode = new IntermediateNode(statement.operation, operator1, operator2, temp);
        interTable.add(interNode);
        return temp;
    }
    
    public void printInterTable()
    {
        System.out.println("\n-> Intermediate Table <-\n");
        
        for (int i = 0; i < interTable.size(); i++) {
            IntermediateNode node = interTable.get(i);
            System.out.println("|    " + node.operator + "    |    " + node.operandum1 + "    |    " + node.operandum2 + "    |    " + node.result + "    |");
        }
    }
    
    public int processLogicalChilds(TreeNode statement, int concatCount)
    {
        TreeNode left = statement.childs.get(0);
        TreeNode right = statement.childs.get(1);
        String concatArg = statement.operation;
        boolean runRight = true;
        
        
        if(!(left.operation.equals("and") || left.operation.equals("or") || left.operation.equals("false") || left.operation.equals("true")))
            concatCount = genLogicalTags(left, concatArg, concatCount);
        
        if(left.operation.equals("true") || left.operation.equals("false"))
        {
            runRight = false;
            concatCount = genLogicalTags(left, concatArg, concatCount);
        }
            
        if(right.operation.equals("true") || right.operation.equals("false"))
        {
            runRight = false;
            concatCount = genLogicalTags(right, concatArg, concatCount);
        }
        
        if(runRight)
            concatCount = genLogicalTags(right, concatArg, concatCount);
        
        return concatCount;
        
    }
    
    public int genLogicalTags(TreeNode logicalStatement, String concatArg ,int concatCount)
    {
        System.out.println("\nGEN LOGICAL TAGS\n");
        IntermediateNode interNode;
        String tag;
        String logOp;
        
        interNode = new IntermediateNode(argTag + argCount, "", "", "");
        interTable.add(interNode);
        if(yet)
            tag = "goto " + trueTag + logResCount +  ", " + "goto yet" + conCount;
        else
            tag = "goto " + trueTag + logResCount +  ", " + "goto " + falseTag + logResCount;
        
        logOp = "if" + logicalStatement.operation;
        
        if(concatArg.equals("and"))
        {
            argCount++;
            if(concatCount != 0)
            {
                if(concatSize!=concatCount)
                {
                    if(nextOr.isEmpty())
                    tag = "goto " + argTag + argCount +  ", " + "goto " + falseTag + logResCount;
                    else
                        tag = "goto " + argTag + argCount +  ", " + "goto " + nextOr;
                }
                
                else
                    tag = "goto " + argTag + argCount +  ", " + "goto " + nextOr;
                
            }

            else
            {
                if(yet)
                    tag = "goto " + trueTag + logResCount +  ", " + "goto yet" + conCount;
                else
                    tag = "goto " + trueTag + logResCount +  ", " + "goto " + falseTag + logResCount;
            }
                
            
        }
        
        else if(concatArg.equals("or"))
        {
            argCount++;
            if(concatCount != 0)
                {
                    if(concatSize != concatCount)
                    {
                        if(nextAnd.isEmpty())
                        tag = "goto " + trueTag + logResCount + ", " + "goto " + argTag + argCount;
                    else
                        tag = "goto " + nextAnd + ", " + "goto " + argTag + argCount;
                    }
                    
                    else
                        tag = "goto " + nextAnd + ", " + "goto " + argTag + argCount;
                    
                }

                else
                {
                    if(yet)
                        tag = "goto " + trueTag + logResCount +  ", " + "goto yet" + conCount;
                    else
                        tag = "goto " + trueTag + logResCount +  ", " + "goto " + falseTag + logResCount;
                }
                    
        }
        
        else
            argCount++;
        
        String jumpTag[] = tag.split(",");
        if(!logicalStatement.childs.isEmpty())
        {
            TreeNode left = logicalStatement.childs.get(0);
            TreeNode right = logicalStatement.childs.get(1);
            interNode = new IntermediateNode(logOp, left.operation, right.operation, jumpTag[0]);
            interTable.add(interNode);
            interNode = new IntermediateNode(jumpTag[1].substring(1), "", "", "");
            interTable.add(interNode);
        }
        
        else
        {
            
            if(logicalStatement.operation.equals("true"))
            {
                tempCount++;
                interNode = new IntermediateNode("=", logicalStatement.operation, "", "$t" + tempCount);
                interTable.add(interNode);
                
                interNode = new IntermediateNode(jumpTag[0], "", "", "");
                interTable.add(interNode);
            }
            else if(logicalStatement.operation.equals("false"))
            {
                tempCount++;
                interNode = new IntermediateNode("=", logicalStatement.operation, "", "$t" + tempCount);
                interTable.add(interNode);
                
                interNode = new IntermediateNode(jumpTag[1].substring(1), "", "", "");
                interTable.add(interNode);
            }
            
            else if(!(logicalStatement.operation.equals("true") || logicalStatement.operation.equals("false")))
            {
                interNode = new IntermediateNode("if=", logicalStatement.operation, "true", jumpTag[0]);
                interTable.add(interNode);
                interNode = new IntermediateNode(jumpTag[1].substring(1), "", "", "");
                interTable.add(interNode);
            }
        }
        
        concatCount--;
        return concatCount;
    }
    
    public void assignmentConstruction(TreeNode currentStatement)
    {
        IntermediateNode interNode;
        String result;
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
            if(currentStatement.childs.size() != 0)
            {
                
                if(currentStatement.childs.get(0).operation.equals("Params"))
                {
                    
                    TreeNode callFunction = new TreeNode("Statement", new TreeNode("Call Function",new TreeNode(currentStatement.operation , currentStatement.childs.get(0))));
                    System.out.println("The current statement is: " + callFunction.operation);
                    traverseTree(callFunction);
                }
                    
            }
            
            tempCount++;
            String temp = "$t" + tempCount;

            interNode = new IntermediateNode("=", currentStatement.operation, "", temp);
            interTable.add(interNode);
            interNode = new IntermediateNode("=", temp, "", result);
            interTable.add(interNode);
        }
    }
    
    public void logicalExpressionConstruction(TreeNode currentStatement)
    {
        ArrayList<TreeNode> concatRuns = new ArrayList();
        int concatCount = 0;// Determines if it should continue traversing the arguments.
        TreeNode parentStatement;// Maintains the parent of the currentStatement
        
        if((currentStatement.operation.equals("and")) || (currentStatement.operation.equals("or")) )
                {
                    while( (currentStatement.operation.equals("and")) || (currentStatement.operation.equals("or")) )// Validates that this argument is not an AND or an OR
                    {
                        concatCount++;
                        parentStatement = currentStatement;
                        currentStatement = currentStatement.childs.get(0);
                        System.out.println(parentStatement.operation);
                        concatRuns.add(parentStatement);
                    }
                    
                    concatSize = concatRuns.size();
                    
                    for (int j = concatRuns.size() - 1; j > -1; j--) {
                        setNextConcat(concatRuns, j);
                        concatCount = processLogicalChilds(concatRuns.get(j), concatCount);
                    }
                    
                    
                }
                
                else// This is executed if their are no ANDs or ORs.
                    genLogicalTags(currentStatement, "",concatCount);
    }
    
    public void traverseOptions(TreeNode statement, String setArg, int optTot, boolean any)
    {
        
        for (int i = 0; i < statement.childs.size(); i++) {
            String tagTempFalse;
            IntermediateNode interNode;
            TreeNode currentStatement;
            currentStatement = statement.childs.get(i);
            optCount++;
            optTot--;
            String tagTempTrue = "true" + opt + optCount;
            if(optTot != 0)
                tagTempFalse = opt + (optCount + 1);
            else
            {
                if(any)
                    tagTempFalse = "any" + setCount;
                else
                    tagTempFalse = set + "False" + setCount;
            }
                

            interNode = new IntermediateNode(opt + optCount, "", "", "");
            interTable.add(interNode);
            TreeNode caseVal = currentStatement.childs.get(0);// Get option/case argument.
            tempCount++;
            String tempOptArg = "$t" + tempCount;
            interNode = new IntermediateNode("=", caseVal.operation, "", "$t" + tempCount);
            interTable.add(interNode);

            interNode = new IntermediateNode("if=", setArg, tempOptArg, "goto " + tagTempTrue);
            interTable.add(interNode);
            interNode = new IntermediateNode("goto " + tagTempFalse, "", "", "");
            interTable.add(interNode);
            
            interNode = new IntermediateNode(tagTempTrue, "", "", "");
            interTable.add(interNode);

            TreeNode caseBody = currentStatement.childs.get(1);// Option/Case body.
            traverseTree(caseBody);
            
            interNode = new IntermediateNode("goto " + set + "False" + setCount , "", "", "");
            interTable.add(interNode);
        }
        
    }
    
    public void setNextConcat(ArrayList<TreeNode> list, int position)
    {
        int tempArgCount = 0;
        for (int i = position; i > -1; i--) {
            if(list.get(i).operation.equals("or"))
            {
                tempArgCount = argCount + ((position - i) + 1);
                nextOr = argTag + tempArgCount;
            }
            
            else if(list.get(i).operation.equals("and"))
            {
                tempArgCount = argCount + ((position - i) + 1);
                nextAnd = argTag + tempArgCount;
            }
            
            else
            {
                nextAnd = "";
                nextOr = "";
            }
        }
    }
}
