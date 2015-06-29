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
                    finalCode += "\tmove " + instruction.result + ", " + instruction.operandum1 + "\n";
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
                case "if<=":
                    finalCode += "\tble " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                case "if>":
                    finalCode += "\tbgt " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                case "if>=":
                    finalCode += "\tbge " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                case "if==":
                    finalCode += "\tbeq " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                case "if!=":
                    finalCode += "\tbne " + instruction.operandum1 + ", " + instruction.operandum2 + ", " + instruction.result.split("\\s+")[1] + "\n";
                default:
                    if(instruction.operator.startsWith("goto")){
                        finalCode += "\tj " + instruction.operator.split("\\s+")[1] + "\n";
                    }else{
                        finalCode += instruction.operator + ":\n";
                    }
           }
            
        }
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
    
    public void ocupy(String reg){
        this.available.put(reg, true);
    }
    
}
