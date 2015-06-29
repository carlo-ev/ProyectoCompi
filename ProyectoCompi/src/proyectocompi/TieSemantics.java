/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

/**
 *
 * @author carlo
 */
public class TieSemantics {

    private TreeNode parseTree;
    private SymbolTable symbolTable;
    public ArrayList<String> typeErrors;
    public ArrayList<String> declarationErrors;
    public JTextArea outputPane;
    public boolean inFunction;
    public int functionTypeCount;
    public ArrayList<SymbolTable> allTables;
    
    public TieSemantics(){}
    
    public TieSemantics(TreeNode node){
        this.parseTree = node;
    }
    
    public TreeNode getTree(){
        return this.parseTree;
    }
    
    public void typeCheck(){
        SymbolTable inicial = new SymbolTable();
        //inicial.addSymbol("put", "str->nil", 0, 0);
        this.declarationErrors = new ArrayList();
        this.typeErrors = new ArrayList();  
        
        allTables = new ArrayList();
        allTables.add(inicial);
        
        this.inFunction = false;
        this.functionTypeCount = 0;
        
        declarationRun(this.parseTree, inicial);
        
        if (this.declarationErrors.size() > 0) {
            this.outputPane.append("> Variable Declaration Errors <\n");
            for(String line : this.declarationErrors){
                this.outputPane.append(line + "\n");
            }
        }else{
            typeRun(this.parseTree, inicial);
            if (this.typeErrors.size() > 0 ) {
                this.outputPane.append("> Type Errors <\n");
                for(String line : this.typeErrors){
                    this.outputPane.append(line + "\n");
                }
            }
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
                    if (inFunction) {
                        this.functionTypeCount += this.getTypeSize(idNode.operation);
                    }
                    table.addSymbol(idNode.operation , actual.type, 0, this.functionTypeCount);
                }
            }else{
                System.out.println("declaring variable array with no value");
                if (inFunction) {
                    this.functionTypeCount += this.getTypeSize(actual.type);
                }
                for (TreeNode child : actual.childs) {
                    if (table.findLocalSymbol(child.operation) != null) {
                        this.declarationErrors.add("Variable "+ child.operation + " ya esta declarada.");
                    }else{        
                        table.addSymbol(child.operation, actual.type, 0, this.functionTypeCount);
                    }
                }
            }
            System.out.println(table);
        }else if(actual.operation.equals("con")){
            System.out.println("Con declaration");
            SymbolTable conTable = new SymbolTable();
            conTable.parentTable = table;
            actual.scope = conTable;
            this.allTables.add(conTable);
            declarationRun(actual.childs.get(1), conTable);
            
            if(!actual.childs.get(2).operation.isEmpty()){
                SymbolTable elseTable = new SymbolTable();
                elseTable.parentTable = table;
                actual.childs.get(2).scope = elseTable;
                this.allTables.add(elseTable);
                declarationRun(actual.childs.get(2).childs.get(0), elseTable);
            } 
            System.out.println(table);
        }else if(actual.operation.equals("til")){
            System.out.println("Til declaration");
            SymbolTable conTable = new SymbolTable();
            conTable.parentTable = table;
            actual.scope = conTable;
            this.allTables.add(conTable);
            declarationRun(actual.childs.get(1), conTable);
            System.out.println(table);
        }else if(actual.operation.equals("rep")){
            System.out.println("Rep declaration");
            SymbolTable repTable = new SymbolTable();
            repTable.parentTable = table;
            actual.scope = repTable;
            this.allTables.add(repTable);
            declarationRun(actual.childs.get(3), repTable);
            System.out.println(table);
        }else if(actual.operation.equals("act")){
            System.out.println("Act Declaration");
            String returnType = actual.type;
            String id = actual.childs.get(0).operation;
            String argumentString = "";
            ArrayList<TreeNode> argumentList = new ArrayList();
            if(actual.childs.get(1).childs.size() > 0){
                argumentString += actual.childs.get(1).childs.get(0).type;
                argumentList.add(actual.childs.get(1).childs.get(0));
                for (int i = 1; i < actual.childs.get(1).childs.size(); i++) {
                    argumentString += "X" + actual.childs.get(1).childs.get(i).type;
                    argumentList.add(actual.childs.get(1).childs.get(i));
                }              
            }
            String functionSignature = argumentString+"->"+returnType;
            if(table.findLocalSymbol(id)!=null){
                this.declarationErrors.add("Funcion "+ id + " ya ha sido declarada.");
            }else{
                table.addSymbol(id, functionSignature, 0);
                SymbolTable functionTable = new SymbolTable();
                for(TreeNode tempNode : argumentList){
                    functionTable.addSymbol(tempNode.operation, tempNode.type, 0);
                }
                System.out.println(functionTable);
                functionTable.parentTable = table;
                actual.scope = functionTable;
                this.allTables.add(functionTable);
                boolean pastFunctionState = this.inFunction;
                int pastFunctionCount = this.functionTypeCount;
                this.inFunction = true;
                this.functionTypeCount = 0;
                declarationRun(actual.childs.get(2), functionTable);
                this.inFunction = pastFunctionState;
                this.functionTypeCount = pastFunctionCount;
            }
            if (!returnType.equals("nil") && actual.childs.get(3).operation.isEmpty()) {
                this.declarationErrors.add("Valor de retorno faltante en la funcion: "+ id );
            }else if(returnType.equals("nill") && !actual.childs.get(3).operation.isEmpty() ){
                this.declarationErrors.add("Funcion "+id+" tipo nil tiene valor de retorno");
            }
            System.out.println(table);
        }else if(actual.operation.equals("set")){
            System.out.println("Set Declaration");
            
            TreeNode casesNode = actual.childs.get(1);
            for (int i = 0; i < casesNode.childs.size(); i++) {
                SymbolTable optionTable = new SymbolTable();
                optionTable.parentTable = table;
                System.out.println("appending scope to case "+casesNode.childs.get(i).operation);
                casesNode.childs.get(i).scope = optionTable;
                this.allTables.add(optionTable);
                declarationRun( casesNode.childs.get(i).childs.get(1), optionTable );
            }
            
            TreeNode elseNode = actual.childs.get(2);
            if (! elseNode.operation.isEmpty() ) {
                SymbolTable anyTable = new SymbolTable();
                anyTable.parentTable = table;
                elseNode.scope = anyTable;
                this.allTables.add(anyTable);
                declarationRun( elseNode.childs.get(0), anyTable );
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
                        rightType = functiontypeCheck(rightOperation, table);
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
            char conditionType = getOperationType( conditionNode );
            switch( conditionType ){
                case 'o':
                    String operantType = this.operationTypeCheck(conditionNode, conditionType, table);
                    this.typeErrors.add("Tipos incompatible: "+operantType+" no puede ser convertido a bin");
                    break;
                case 'b':
                case 'e':
                case 'c':
                    this.operationTypeCheck(conditionNode, conditionType, table);
                    break;
                case 'l':
                    if (! conditionNode.type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+conditionNode.type+" no puede ser convertido a bin");
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
            TreeNode firstAssign = actual.childs.get(0);
            TreeNode conditional = actual.childs.get(1);
            TreeNode secondAssign = actual.childs.get(2);
            //Verificamos condiciones de asiganacion dentro del REP
            if (firstAssign.operation.equals("=")) {
                this.typeRun(firstAssign, table);
            }
            //Siendo estas dos, la inicial y la que se repite luego de cada ciclo
            if (secondAssign.operation.equals("=")) {
                this.typeRun(secondAssign, table);
            }
            //Luego verificamos los tipos dentro de la condicion de enmedio del REP
            //Condicion booleano
            char midOperationType = getOperationType(conditional);
            switch( midOperationType ){
                case 'o':
                    String operantType = this.operationTypeCheck(conditional, midOperationType, table);
                    this.typeErrors.add("Tipos incompatible: "+operantType+" no puede ser convertido a bin");
                    break;
                case 'b':
                case 'e':
                case 'c':
                    this.operationTypeCheck(conditional, midOperationType, table);
                    break;
                case 'l':
                    if (! actual.type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+actual.type+" no puede ser convertido a bin");
                    }
                    break;
                case 'i':
                    TieSymbol localId = table.findSymbol(conditional.operation);
                    if(localId != null){
                        if(! localId.type.equals("bin")){
                            this.typeErrors.add("Tipos incompatible: "+localId.type+" no puede ser convertido a bin");                        
                        }
                    }else{
                        this.typeErrors.add("Variable "+ conditional.operation + " no esta declarada.");
                    }
                    break;
                case 'f':
                    String type = this.functiontypeCheck(conditional, table);
                    if (! type.equals("bin")) {
                        this.typeErrors.add("Tipos incompatible: "+type+" no puede ser convertido a bin");
                    }
                    break;
            }
            //Luego continuamos con la validacion de tipos dentro del cuerpo del REP
            this.typeRun(actual.childs.get(3), actual.scope);
        }else if(actual.operation.equals("set")){
            //Obtenemos los nodos de casos y definicion inicial
            TreeNode definerNode = actual.childs.get(0);
            TreeNode caseNodes = actual.childs.get(1);
            TreeNode defaultNode = actual.childs.get(2);
            
            //Verificamos si es un literal o un id el nodo de decision inicial
            if ( definerNode.type == null ) {
                TieSymbol localId = table.findSymbol(definerNode.operation);
                if(localId != null){
                    //Verificamos si es SYM o NUM ya que solo esos tipos se pueden en un SET
                    if (! (localId.type.equals("sym") || localId.type.equals("num")) ) {
                        this.typeErrors.add("Tipos incompatible: Se permite sym o num, tipo encontrado: "+localId.type);
                    }
                }else{
                    this.typeErrors.add("Variable "+ definerNode.operation + " no esta declarada.");
                }
            }
            
            //Verificamos los casos y los valores de decision dentro de los casos
            for(TreeNode oneCase : caseNodes.childs){
                TreeNode definerCaseNode = oneCase.childs.get(0);
                //Check the type for an id that defines the case
                if (! (definerCaseNode.type != null) ) {
                    TieSymbol caseId = table.findSymbol(definerCaseNode.operation);
                    if (caseId != null) {
                        if (! (caseId.type.equals("sym") || caseId.type.equals("num")) ) {
                            this.typeErrors.add("Tipos incompatible: "+caseId.type+" no puede ser convertido a bin");
                        }
                    }
                }
                //We check type for the body of the case
                this.typeRun(oneCase.childs.get(1), oneCase.scope);
            }
            
            //If there is an ANY case then check type for its body
            if (! defaultNode.operation.isEmpty() ) {
                this.typeRun(defaultNode.childs.get(0), defaultNode.scope);
            }
        }else if(actual.operation.equals("Call Function")){
            this.functiontypeCheck(actual.childs.get(0), table);  
        }else if(actual.operation.equals("act")){
            TreeNode returnNode = actual.childs.get(3);
            
            this.typeRun(actual.childs.get(2), actual.scope);
            
            if (! returnNode.operation.isEmpty()) {
                char returnOperationType = this.getOperationType(returnNode.childs.get(0));
                switch( returnOperationType ){
                    case 'o':
                        String operantType = this.operationTypeCheck(returnNode.childs.get(0), returnOperationType, actual.scope);
                        if(!actual.type.equals(operantType)){
                            this.typeErrors.add("Tipos incompatible: "+operantType+" no puede ser convertido a "+actual.type);
                        }
                        break;
                    case 'b':
                    case 'c':
                    case 'e':
                        if(!actual.type.equals("bin")){
                            this.typeErrors.add("Tipos incompatible: bin no puede ser convertido a "+actual.type);
                        }
                        this.operationTypeCheck(returnNode.childs.get(0), returnOperationType, actual.scope);
                        break;
                    case 'l':
                        if ( !actual.type.equals(returnNode.childs.get(0).type) ) {
                            this.typeErrors.add("Tipos incompatible: "+returnNode.childs.get(0).type+" no puede ser convertido a "+actual.type);
                        }
                        break;
                    case 'i':
                        TieSymbol returnId = actual.scope.findSymbol(returnNode.childs.get(0).operation);
                        if(returnId != null){
                            if(! returnId.type.equals(actual.type)){
                                this.typeErrors.add("Tipos incompatible: "+returnId.type+" no puede ser convertido a "+actual.type);                        
                            }
                        }else{
                            this.typeErrors.add("Variable "+ returnNode.childs.get(0).operation + " no esta declarada.");
                        }
                        break;
                    case 'f':
                        String type = this.functiontypeCheck(returnNode.childs.get(0), actual.scope);
                        if (! type.equals(actual.type)) {
                            this.typeErrors.add("Tipos incompatible: "+type+" no puede ser convertido a "+actual.type);
                        }
                        break;
                }
            }
        }else{
            System.out.println("Unknown type operation on tree: "+actual.operation);
        }
    }
    /*--------------------Type check for Operations--------------------*/
    public String operationTypeCheck(TreeNode operation, char type, SymbolTable table){
        System.out.println("Checking types on operation: "+operation.operation +" with operation char type: "+ type);
        TreeNode  left = operation.childs.get(0);
        TreeNode right = operation.childs.get(1);
        
        String leftType = sideOperationType(left, table);
        String rightType = sideOperationType(right, table);
        
        System.out.println("left side type -> "+leftType);
        System.out.println("right side type -> "+rightType);
        
        
        String finalType = "";
        switch(type){
            case 'o'://For arithmetic operations -> +, -, /, *
                if( (leftType.equals("num") && rightType.equals("num")) || (leftType.equals("dec") || rightType.equals("dec")) ){
                    finalType = leftType;
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permite en la operation: "+operation.operation);
                }
                break;
            case 'c'://For comparison operations -> <, >, <=, >=
                if( (leftType.equals("num") && rightType.equals("num")) || (leftType.equals("dec") || rightType.equals("dec")) ){
                    finalType = "bin";
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permite en la operation: "+operation.operation);
                }
                break;
            case 'b'://For boolean operations -> and, or
                if(leftType.equals("bin") && rightType.equals("bin")){
                    finalType = leftType;
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permite en operaciones booleanas");
                }
                break;
            case 'e'://Equal operations -> ==, !=
                if( (leftType.equals("num") && rightType.equals("num"))
                    || (leftType.equals("bin") && rightType.equals("bin"))
                    || (leftType.equals("char") && rightType.equals("char"))
                    || (leftType.equals("dec") && rightType.equals("dec"))
                ){
                    finalType = "bin";
                }else{
                    this.typeErrors.add("Operandos tipo: "+leftType+" y "+rightType+" no se permiten en comparacion");
                }
                break;
        }
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
    
    public int getTypeSize(String type){
        if (type.equals("num")) {
            return 4;
        }else if (type.equals("bin")){
            return 1;
        }else if (type.equals("dec")){
            return 4;
        }else if (type.equals("sym")){
            return 1;
        }else{
            return 0;
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
                    typeErrors.add(functionId.id+": Tipos de Argumentos invalidos se esperaba "+functionFirmSplit[0]+" en lugar de "+firm );
                    return "";
                }else{
                    return functionFirmSplit[1];
                }
            }else{
                if(functionFirmSplit[0].isEmpty()) {
                    return functionFirmSplit[1];
                }else{
                    typeErrors.add(functionId.id+": Tipos de Argumentos invalidos se esperaba "+functionFirmSplit[0] );
                    return "";
                }
            }
        }else{
            this.typeErrors.add("Funcion "+ functionNode.operation + " no esta declarada.");
            return "";
        }
    }
    
}
