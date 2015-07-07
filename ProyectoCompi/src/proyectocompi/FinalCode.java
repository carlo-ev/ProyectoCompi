/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class FinalCode {
    
    public ArrayList<IntermediateNode> intermediateCode; 
    public ArrayList<SymbolTable> symbolTables;        
    public String registers[] = {"$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7","$t8","$t9"};
    public Map<String, Boolean>available;
    
    public FinalCode(){}
    
    public FinalCode(ArrayList intermediate){
        this.intermediateCode = intermediate;
        this.symbolTables = new ArrayList();
        
        available = new HashMap();
        for (String reg : registers) {
            available.put(reg, false);
        }
    }
    
    public FinalCode(ArrayList intermediate, ArrayList tables){
        this.intermediateCode = intermediate;
        this.symbolTables = tables;
        
        available = new HashMap();
        for (String reg : registers) {
            available.put(reg, false);
        }
    }
    
    public String generate(){
        String finalCode = "";
        finalCode += ".data\n";
        for(SymbolTable table : this.symbolTables){
            for(TieSymbol var : table.table){
                finalCode += "\t" + var.id + ": .word\n";
            }
        }
        finalCode += "\n.text\n\n";
        for(IntermediateNode instruction : this.intermediateCode){
           switch(instruction.operator){
               case "=":
                   //finalCode += "\tmove " + instruction.result + ", " + instruction.operandum1 + "\n";
                   boolean move = false;
                   if(instruction.result.substring(0, 1).equals("$") && instruction.operandum1.substring(0, 1).equals("$"))
                       move = true;
                   
                   String tempFinalCode = findSymbol(instruction);
                    if(tempFinalCode.isEmpty())
                    {
                        if(move)
                            finalCode += "\tmove " + instruction.result + ", " + instruction.operandum1 + "\n";
                        else
                            finalCode += "\tli " + instruction.result + ", " + instruction.operandum1 + "\n";
                    }
                    else
                        finalCode += tempFinalCode;
                   break;
               case "+":
                    finalCode += "\tadd " + instruction.result + ", " + instruction.operandum1 + ", " + instruction.operandum2 + "\n"; 
                   break;
               case "-":
                    finalCode += "\tsub " + instruction.result + ", " + instruction.operandum1 + ", " + instruction.operandum2 + "\n";
                   break;
                case "*":
                    finalCode += "\tmul " + instruction.result + ", " + instruction.operandum1 + ", " + instruction.operandum2 + "\n";
                   break;
                case "/":
                    finalCode += "\tdiv " + instruction.result + ", " + instruction.operandum1 + ", " + instruction.operandum2 + "\n";
                   break;
                case "if<":
                    finalCode += "\tblt " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                case "if<=":
                    finalCode += "\tble " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                case "if>":
                    finalCode += "\tbgt " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                case "if>=":
                    finalCode += "\tbge " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                case "if==":
                    finalCode += "\tbeq " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                case "if!=":
                    finalCode += "\tbne " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                    break;
                default:
                    if(instruction.operator.startsWith("goto")){
                        String tempString[] =  instruction.operator.split("\\s+");
                        if(tempString.length > 1)
                            finalCode += "\tj " + tempString[1] + "\n";
                        else
                            finalCode += "\tj " + tempString[0] + "\n";
                            
                    }else if(instruction.operator.equals("call")){
                        finalCode += "\tjal " + instruction.operandum1 + "\n";
                    }
                    else if(instruction.operator.equals("ret"))
                    {
                        finalCode += "\tjr $ra\n";
                    }
                    else {
                        finalCode += instruction.operator + ":\n";
                    }
                    break;
           }
                
        }
        finalCode += "\tli $v0, 10\n";
        finalCode += "\tsyscall\n";
        return finalCode;
    }
    
    public String getFreeRegistry(){
        String free = null;
        for(String reg : registers){
            if(!this.available.get(reg)){
                free = reg;
                break;
            }
        }
        return free;
    }
    
    public String findSymbol(IntermediateNode instruction)
    {
        String finalCode = "";
        for(SymbolTable table : this.symbolTables){
            System.out.println(table);
            for(TieSymbol var : table.table){
                if(instruction.result.equals(var.id))
                    finalCode = "\tsw " + instruction.operandum1 + ", " + instruction.result + "\n";
            }
        }
        return finalCode;
    }
    
    public void ocupy(String reg){
        this.available.put(reg, true);
    }
    
}
