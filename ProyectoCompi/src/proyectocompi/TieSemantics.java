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
        
        if (actual.operation.isEmpty()){
            return;
        }else if(actual.operation.equals("Run")) { //Nodo inicial de todo el programa so es filler
            typeRun(actual.childs.get(0), table);
            
        }else if(actual.operation.equals("Statements")){//Chequeo de tipos para cada statement en el programa
            for (TreeNode statement : actual.childs) {
                typeRun(statement, table);
            }
            
        }else if (actual.operation.equals("=")) {//Chequeo para statement de asignacion
            //Verificando que la variable exista y qe los tipos de asignacion y variable concuerden
            TieSymbol id = table.findSymbol(actual.childs.get(0).operation);
            if(id == null){
                this.typeErrors.add("Variable "+ actual.childs.get(0).operation + " no esta declarada.");
            }else{
                TreeNode rightOperation = actual.childs.get(1);
                String leftType = id.type;
                String rightType = "";
                char rightOperationType = getOperationType(rightOperation);
                //Verificamos el lado derecho de la asignacion, si es id, valor, funcion, etc...
                switch(rightOperationType){
                    case 'o':
                    case 'b':
                    case 'c':
                    case 'e':
                        rightType = operationTypeCheck(rightOperation, rightOperationType, table);
                        break;
                    case 'l':
                        rightType = rightOperation.type;
                        break;
                    case 'i':
                        TieSymbol localId = table.findSymbol(rightOperation.operation);
                        if(localId != null)
                            rightType = localId.type;
                        else{
                            rightType = "";
                            this.typeErrors.add("Variable "+ rightOperation.operation + " no esta declarada.");
                        }
                        break;
                    case 'f':
                        rightType = functiontypeCheck(actual, table);
                        break;
                }
                
                if (! leftType.equals(rightType)) {
                    this.typeErrors.add("Tipos incompatibles: " + rightType + " no puede ser convertido a " + leftType);
                }
            }
        }else if(actual.operation.equals("Declare ID")){//Si se declara id con tipo solamente si es asignacion se verifica tipos
            if (actual.childs.get(0).operation.equals("=")) {
                typeRun(actual.childs.get(0), table);
            }
        }else if(actual.operation.equals("con") || actual.operation.equals("til")){
            //Getting the boolean condition statement
            TreeNode conditionNode = actual.childs.get(0);
            TreeNode elseNode = null;
            //Child Array Size determines if it is a CON(3 Childs) or a TIL(2 Childs)
            if(actual.childs.size() > 2){
                elseNode = actual.childs.get(2);
            }
            
            //Se verifica el tipo de la condicion del CON si es funcion, id, etc...
            switch( getOperationType( conditionNode ) ){
                case 'o':
                    this.typeErrors.add("Tipos incompatible: num no puede ser convertido a bin");
                    break;
                case 'b':
                case 'e':
                case 'c':
                    this.operationTypeCheck(actual, 'b', table);
                    break;
                case 'l':
                    if (! actual.type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+actual.type+" no puede ser convertido a bin");
                    }
                    break;
                case 'i':
                    TieSymbol localId = table.findSymbol(conditionNode.operation);
                    if(localId != null){
                        if(! localId.type.equals("bin")){
                            this.typeErrors.add("Tipos incompatible: "+localId.type+" no puede ser convertido a bin");                        
                        }
                    }else{
                        this.typeErrors.add("Variable "+ conditionNode.operation + " no esta declarada.");
                    }
                    break;
                case 'f':
                    String type = this.functiontypeCheck(actual, table);
                    if (! type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+type+" no puede ser convertido a bin");
                    }
                    break;
            }
            
            //Verificamos el cuerpo del CON este o no bien la condicion presentada
            //Solo llamamos typeRun ya que dentro del cuerpo de con solo se ecnuentra un nodo de Statements
            this.typeRun(actual.childs.get(1), actual.scope);
        
            //Si se presenta un nodo YET se verifica el cuerpo del else
            if(elseNode != null && elseNode.operation.equals("yet")){
                this.typeRun(elseNode.childs.get(0), elseNode.scope);
            }
        }else if(actual.operation.equals("rep")){
            this.typeRun(actual.childs.get(0), table);
            this.typeRun(actual.childs.get(2), table);
            switch( getOperationType(actual.childs.get(1)) ){
                case 'o':
                    this.typeErrors.add("Tipos incompatible: num no puede ser convertido a bin");
                    break;
                case 'b':
                case 'e':
                case 'c':
                    this.operationTypeCheck(actual, 'b', table);
                    break;
                case 'l':
                    if (! actual.type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+actual.type+" no puede ser convertido a bin");
                    }
                    break;
                case 'i':
                    TieSymbol localId = table.findSymbol(actual.operation);
                    if(localId != null){
                        if(! localId.type.equals("bin")){
                            this.typeErrors.add("Tipos incompatible: "+localId.type+" no puede ser convertido a bin");                        
                        }
                    }else{
                        this.typeErrors.add("Variable "+ actual.operation + " no esta declarada.");
                    }
                    break;
                case 'f':
                    String type = this.functiontypeCheck(actual, table);
                    if (! type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+type+" no puede ser convertido a bin");
                    }
                    break;
            }
            this.typeRun(actual.childs.get(3), actual.scope);
        }else if(actual.operation.equals("set")){
            TreeNode definerNode = actual.childs.get(0);
            TreeNode caseNodes = actual.childs.get(1);
            if ( definerNode.type == null ) {
                TieSymbol localId = table.findSymbol(definerNode.operation);
                if(localId != null){
                    if (! (localId.type.equals("sym") || localId.type.equals("num")) ) {
                        this.typeErrors.add("Tipos incompatible: "+localId.type+" no puede ser convertido a bin");
                    }
                }else{
                    this.typeErrors.add("Variable "+ definerNode.operation + " no esta declarada.");
                }
            }
        }else if(actual.operation.equals("Call Function")){
            this.functiontypeCheck(actual, table);  
        }else{
            System.out.println("Unknown type operation on tree: "+actual.operation);
        }
    }
    /*--------------------Type check for Operations--------------------*/
    public String operationTypeCheck(TreeNode operation, char type, SymbolTable table){
        System.out.println("Checking types on operation: "+operation.operation +" with operation char type: "+ type);
        TreeNode  left = operation.childs.get(0);
        TreeNode right = operation.childs.get(1);
        
        System.out.println("Doing left side!");
        String leftType = sideOperationType(left, table);
        System.out.println("Doing right side!");
        String rightType = sideOperationType(right, table);
        
        System.out.println("left side type -> "+leftType);
        System.out.println("right side type -> "+rightType);
        
        
        String finalType = "";
        switch(type){
            case 'o'://For arithmetic operations -> +, -, /, *
            case 'c'://For comparison operations -> <, >, <=, >=
                System.out.println("doing C operation types case?");
                if( leftType.equals("num") && rightType.equals("num") ){
                    finalType = leftType;
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permite en la operation: "+operation.operation);
                }
                System.out.println("operation type o or c " + finalType );
                break;
            case 'b'://For boolean operations -> a``nd, or
                if(leftType.equals("bin") && leftType.equals("bin")){
                    finalType = leftType;
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permite en operaciones booleanas");
                }
                System.out.println("operation type b "+ finalType);
                break;
            case 'e'://Equal operations -> ==, !=
                if( (leftType.equals("num") && rightType.equals("num"))
                    || (leftType.equals("bin") && rightType.equals("bin"))
                    || (leftType.equals("char") && rightType.equals("char"))
                ){
                    finalType = leftType;
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permiten en comparacion");
                }
                System.out.println("operation type e "+finalType);
                break;
        }
        System.out.println("returning operation type from check "+ finalType);
        //Maybe on error return nil?
        return finalType;
        
    }
    
    public char getOperationType(TreeNode sym)
    {
        if(sym.operation.equals("+")||sym.operation.equals("-")||sym.operation.equals("*")
                ||sym.operation.equals("/")){//Its an arithmetic operation
            return 'o';
        }else if(sym.operation.equals("and") || sym.operation.equals("or")){//Its a boolean operation
            return 'b';
        }else if( sym.operation.equals(">") || sym.operation.equals(">=") || sym.operation.equals("<")
                || sym.operation.equals("<=") ){//Its a comparison function
            return 'c';
        }else if(sym.operation.equals("==") || sym.operation.equals("!=")){//Its an equality function
            return 'e';
        }else if(sym.type != null){//Its a Literal
            return 'l';
        }else if(sym.childs.size()==0){// It's an Id
            return 'i';
        }else{// It's a call to function
            return 'f';
        }
    }
    
    public String sideOperationType(TreeNode node, SymbolTable table){
        String finalType = "";
        char operationType = getOperationType(node);
        switch( operationType ){
            case 'o':
            case 'b':
            case 'c':
            case 'e':
                finalType = operationTypeCheck(node, operationType, table);
                break;
            case 'l':
                finalType = node.type;
                break;
            case 'i':
                TieSymbol id = table.findSymbol(node.operation);
                if(id != null){
                    finalType = id.type;
                }else{
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
                            type = operationTypeCheck(temp, forOperationType, table);
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
