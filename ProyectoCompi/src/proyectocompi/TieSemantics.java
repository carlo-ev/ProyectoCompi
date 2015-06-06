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
public class TieSemantics {

    private TreeNode parseTree;
    private SymbolTable symbolTable;
    
    public TieSemantics(){}
    
    public TieSemantics(TreeNode node){
        this.parseTree = node;
    }
    
    public TreeNode getTree(){
        return this.parseTree;
    }
    
}
