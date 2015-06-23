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
public class SymbolTable {
    
    public SymbolTable parentTable;
    public ArrayList<TieSymbol> table;
    
    public SymbolTable(){
        table = new ArrayList();
    }
    
    public SymbolTable(SymbolTable parent){
        this.parentTable = parent;
    }
    
    public TieSymbol findSymbol(String id){
        TieSymbol sym = null;
        for(TieSymbol temp : table){
            if( temp.id.equals(id) ){
                sym = temp;
            }
        }
        if(sym != null){
            return sym;
        }
        
        if( this.parentTable != null){
            return this.parentTable.findSymbol(id);
        }else{
            return null;
        }     
    }
    
    public TieSymbol findLocalSymbol(String id){
        TieSymbol sym = null;
        for(TieSymbol temp : table){
            if( temp.id.equals(id) ){
                sym = temp;
            }
        }
        return sym;
    }
    
    public void addSymbol(String id, String type, int depth, int address){
        this.table.add(new TieSymbol(id, type, depth, address));
    }
    
    public void addSymbol(String id, String type, int depth){
        this.table.add(new TieSymbol(id, type, depth));
    }
    
    public void addSymbol(TieSymbol sym){
        this.table.add(sym);
    }
 
    public String toString(){
        String tableString =  "-> Symbol Table"+table.size()+" <-\n";
        for(TieSymbol row : table){
            tableString += row.toString() + "\n";
        }
        return tableString;
    }
    
}
