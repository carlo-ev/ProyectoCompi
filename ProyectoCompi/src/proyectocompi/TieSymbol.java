/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

/**
 *
 * @author carlo
 */
public class TieSymbol {
    
    public String id;
    public String type;
    public int depth;
    public int address;
    
    public TieSymbol(){}
    
    public TieSymbol(String id, String type, int depth){
        this.id = id;
        this.type = type;
        this.depth = depth;
        this.address = 0;
    }
    
    public TieSymbol (String id, String type, int depth, int address){
        this.id = id;
        this.type = type;
        this.depth = depth;
        this.address = address;
    }
    
    public String toString(){
        return "Name: " + this.id + " Type: "+ this.type + " Depth: " + Integer.toString(this.depth) + " Address: "+Integer.toString(this.address);
    }
    
}
