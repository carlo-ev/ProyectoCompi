/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class TieSemantics {

    private TreeNode parseTree;
    private SymbolTable symbolTable;
    public ArrayList<String> typeErrors;
    public ArrayList<String> declarationErrors;
    
    public TieSemantics(){}
    
    public TieSemantics(TreeNode node){
        this.parseTree = node;
    }
    
    public TreeNode getTree(){
        return this.parseTree;
    }
    
    public void typeCheck(){
        SymbolTable inicial = new SymbolTable();
        this.declarationErrors = new ArrayList();
        
        declarationRun(this.parseTree, inicial);
        
        if (this.declarationErrors.size() > 0) {
            System.out.println(this.declarationErrors);
        }else{
            this.typeErrors = new ArrayList();    
            typeRun(this.parseTree, inicial);
            System.out.println(this.typeErrors);
        }
        
        
    }
    
    public void declarationRun(TreeNode actual, SymbolTable table){
        if(actual.operation.isEmpty())
            return;
        
        System.out.println("Current Operation: "+ actual.operation );
        
        if( actual.operation.equals("Declare ID") ){
            System.out.println("Declaring a variable");
            if (actual.childs.get(0).operation.equals("=")) {
                System.out.println("declaring variable with value");
                TreeNode equalsNode = actual.childs.get(0);
                TreeNode idNode = equalsNode.childs.get(0);
                if (table.findLocalSymbol(idNode.operation) != null) {
                    this.declarationErrors.add("Variable "+ idNode.operation + " ya esta declarada.");
                }else{
                    table.addSymbol(idNode.operation , actual.type, 0);
                }
            }else{
                System.out.println("declaring variable array with no value");
                for (TreeNode child : actual.childs) {
                    if (table.findLocalSymbol(child.operation) != null) {
                        this.declarationErrors.add("Variable "+ child.operation + " ya esta declarada.");
                    }else{
                        table.addSymbol(child.operation, actual.type, 0);
                    }
                }
            }
            System.out.println(table);
        }else if(actual.operation.equals("con")){
            System.out.println("Con declaration");
            SymbolTable conTable = new SymbolTable();
            conTable.parentTable = table;
            actual.scope = conTable;
            declarationRun(actual.childs.get(1), conTable);
            
            if(!actual.childs.get(2).operation.isEmpty()){
                SymbolTable elseTable = new SymbolTable();
                elseTable.parentTable = table;
                actual.scope = elseTable;
                declarationRun(actual.childs.get(2).childs.get(0), elseTable);
            } 
            System.out.println(table);
        }else if(actual.operation.equals("til")){
            System.out.println("Til declaration");
            SymbolTable conTable = new SymbolTable();
            conTable.parentTable = table;
            actual.scope = conTable;
            declarationRun(actual.childs.get(1), conTable);
            System.out.println(table);
        }else if(actual.operation.equals("rep")){
            System.out.println("Rep declaration");
            SymbolTable repTable = new SymbolTable();
            repTable.parentTable = table;
            actual.scope = repTable;
            declarationRun(actual.childs.get(3), repTable);
            System.out.println(table);
            
        }else if(actual.operation.equals("act")){
            System.out.println("Act Declaration");
            String returnType = actual.type;
            String id = actual.childs.get(0).operation;
            String argumentString = "";
            if(actual.childs.get(1).childs.size() > 0)
            {
                argumentString += actual.childs.get(1).childs.get(0).type;
                for (int i = 1; i < actual.childs.get(1).childs.size() - 1; i++) {
                    argumentString += "X" + actual.childs.get(1).childs.get(i).type; 
                }
                 
            }
            String functionSignature = argumentString+"->"+returnType;
            if(table.findLocalSymbol(id)!=null)
            {
                this.typeErrors.add("Funcion "+ id + " ya ha sido declarada.");
            }
            else
            {
                table.addSymbol(id, functionSignature, 0);
                SymbolTable functionTable = new SymbolTable();
                functionTable.parentTable = table;
                actual.scope = functionTable;
                declarationRun(actual.childs.get(2), functionTable);
            }
            System.out.println(table);
        }else if(actual.operation.equals("set")){
            System.out.println("Set Declaration");
            for (int i = 0; i < actual.childs.get(1).childs.size() - 1; i++) {
                SymbolTable optionTable = new SymbolTable();
                optionTable.parentTable = table;
                actual.childs.get(1).childs.get(i).scope = optionTable;
                declarationRun( actual.childs.get(1).childs.get(i).childs.get(1), optionTable );
            }
            System.out.println(table);
        }else if(actual.operation.equals("Statements")){
            System.out.println("Statements Declaration");
            for(TreeNode node : actual.childs){
                declarationRun(node, table);
            }
        }else if(actual.operation.equals("Run")){
            System.out.println("Run declaration");
            declarationRun(actual.childs.get(0), table);
        }
    }
    
    public void typeRun(TreeNode actual, SymbolTable table){
        System.out.println("Current Operation on type check " + actual.operation);
        if (actual.operation.equals("Run")) {
            typeRun(actual.childs.get(0), table);
        }else if(actual.operation.isEmpty()){
            return;
        }else if(actual.operation.equals("Statements")){
            for (TreeNode statement : actual.childs) {
                typeRun(statement, table);
            }
        }else if (actual.operation.equals("=")) {
            System.out.println(actual.childs.size());
            TieSymbol id = table.findSymbol(actual.childs.get(0).operation);
            if(id == null){
                this.typeErrors.add("Variable "+ actual.childs.get(0).operation + " no esta declarada.");
            }else{
                TreeNode rightOperation = actual.childs.get(1);
                String leftType = id.type;
                String rightType = "";
                char rightOperationType = getOperationType(rightOperation);
                switch(rightOperationType)
                {
                    case 'o':
                        rightType = operationTypeCheck(rightOperation, table);
                        break;
                    case 'l':
                        rightType = rightOperation.type;
                        break;
                    case 'i':
                        TieSymbol localId = table.findSymbol(rightOperation.operation);
                        if(localId != null)
                            rightType = localId.type;
                        else
                        {
                            rightType = "";
                            this.typeErrors.add("Variable "+ rightOperation.operation + " no esta declarada.");
                        }
                        break;
                    case 'f':
                        rightType = functiontypeCheck(actual, table);
                        break;
                }
                
                if (! leftType.equals(rightType)) {
                    this.typeErrors.add("Tipos incompatibles: " + leftType + " no puede ser convertido a " + rightType);
                }

            }
        }else if(actual.operation.equals("Declare ID")){
            if (actual.childs.get(0).operation.equals("=")) {
                typeRun(actual.childs.get(0), table);
            }
        }else if(actual.operation.equals("con")){
            
        }else if(actual.operation.equals("til")){
        }else if(actual.operation.equals("rep")){
        }else if(actual.operation.equals("set")){
        }else if(actual.operation.equals("Call Function")){}
    }
    /*--------------------Type check for arithmetic operations--------------------*/
    public String operationTypeCheck(TreeNode operation, SymbolTable table){
        TreeNode  left = operation.childs.get(0);
        TreeNode right = operation.childs.get(1);
        char leftOperationType = getOperationType(left);
        char rightOperationType = getOperationType(right);
        
        String leftType = sideOperationType(leftOperationType, left, table);
        String rightType = sideOperationType(rightOperationType, right, table);
        
        if(leftType.equals(rightType))
            return leftType;
        else
        {
            this.typeErrors.add("Tipos incompatibles: " + leftType + " no puede ser convertido a " + rightType);
            return "";
        }
            
        /*
        if (operation.operation.equals("!")) {
            return operationTypeCheck(operation.childs.get(0), table);
        }
        */
    }
    
    public char getOperationType(TreeNode sym)
    {
        if(sym.operation.equals("+")||sym.operation.equals("-")||sym.operation.equals("*")
                ||sym.operation.equals("/"))
        {
            return 'o';
        }
        else if(sym.type != null)
        {
            return 'l';
        }
        else if(sym.childs.size()==0)// It's an Id
        {
            return 'i';
        }
        else// It's a call to function
        {
            return 'f';
        }
    }
    
    public String sideOperationType(char leftOperationType, TreeNode node, SymbolTable table)
    {
        String finalType = "";
        switch(leftOperationType)
        {
            case 'o':
                finalType = operationTypeCheck(node, table);
                break;
            case 'l':
                finalType = node.type;
                break;
            case 'i':
                TieSymbol id = table.findSymbol(node.operation);
                if(id != null)
                    finalType = id.type;
                else
                {
                    finalType = "";
                    this.typeErrors.add("Variable "+ node.operation + " no esta declarada.");
                }   
                break;
            case 'f':
                finalType = functiontypeCheck(node, table);
                break;
        }
        return finalType;
    }
    
    public String functiontypeCheck(TreeNode functionNode, SymbolTable table){
        TieSymbol functionId = table.findSymbol(functionNode.operation);
        if (functionId != null) {
            String[] functionFirmSplit = functionId.type.split("->");
            String firm = "";
            if (functionNode.childs.get(0).operation.equals("Params")) {
                for(TreeNode temp:functionNode.childs.get(0).childs){
                    String type = "";
                    char forOperationType = getOperationType(temp);
                    switch(forOperationType){
                        case 'o':
                            type = operationTypeCheck(temp, table);
                            break;
                        case 'i':
                            TieSymbol idTemp = table.findSymbol(temp.operation);
                            if(idTemp != null){
                                type = idTemp.type;
                            }else{
                                this.typeErrors.add("Variable "+ temp.operation + " no esta declarada.");
                            }
                            break;
                        case 'l':
                            type = temp.type;
                            break;
                    }
                    firm += type + "X";
                }
                firm = firm.substring(0, firm.length()-1);
                if (!functionFirmSplit[0].equals(firm)) {
                    typeErrors.add("Tipos de Argumentos invalidos se esperaba "+functionFirmSplit[0]+" en lugar de "+firm );
                    return "";
                }else{
                    return functionFirmSplit[1];
                }
            }else{
                if(functionFirmSplit[0].isEmpty()) {
                    return functionFirmSplit[1];
                }else{
                    typeErrors.add("Tipos de Argumentos invalidos se esperaba "+functionFirmSplit[0] );
                    return "";
                }
            }
        }else{
            this.typeErrors.add("Function "+ functionNode.operation + " no esta declarada.");
            return "";
        }
    }
    
}
