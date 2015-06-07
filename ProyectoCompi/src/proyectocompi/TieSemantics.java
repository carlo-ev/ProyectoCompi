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
    
    public TieSemantics(){}
    
    public TieSemantics(TreeNode node){
        this.parseTree = node;
    }
    
    public TreeNode getTree(){
        return this.parseTree;
    }
    
    public void typeCheck(){
        SymbolTable inicial = new SymbolTable();
        
        declarationRun(this.parseTree, inicial);
        
        this.typeErrors = new ArrayList();
        
        typeRun(this.parseTree, this.symbolTable);
        
    }
    
    public void declarationRun(TreeNode actual, SymbolTable table){
        if(actual.operation.isEmpty())
            return;
        
        System.out.println("Current Operation: "+ actual.operation );
        
        if( actual.operation.equals("Declare ID") ){
            System.out.println("Declaring a variable");
            if (actual.childs.size() == 1) {
                TreeNode equalsNode = actual.childs.get(0);
                TreeNode idNode = equalsNode.childs.get(0);
                table.addSymbol(idNode.operation , actual.type, 0);
            }else{
                for (TreeNode child : actual.childs) {
                    table.addSymbol(child.operation, actual.type, 0);
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
            table.addSymbol(id, functionSignature, 0);
            SymbolTable functionTable = new SymbolTable();
            functionTable.parentTable = table;
            actual.scope = functionTable;
            declarationRun(actual.childs.get(2), functionTable);
            
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
        if (actual.operation.equals("=")) {
            TieSymbol id = table.findSymbol(actual.childs.get(0).operation);
            if(id == null){
                this.typeErrors.add("Variable "+ actual.childs.get(0).operation + " is not defined.");
            }else{
                
            }
        }else if(actual.operation.equals("Declare ID")){
            typeRun(actual.childs.get(0), table);
        }else if(actual.operation.equals("con")){
            
        }else if(actual.operation.equals("til")){
        }else if(actual.operation.equals("rep")){
        }else if(actual.operation.equals("set")){
        }else if(actual.operation.equals("Call Function")){}
    }
    
}
