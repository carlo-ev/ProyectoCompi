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
    
    private SymbolTable parentTable;
    private ArrayList<TieSymbol> table;
    
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
}
