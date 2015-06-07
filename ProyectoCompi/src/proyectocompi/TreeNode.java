/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author carlo
 */
public class TreeNode {
    public String operation;
    public ArrayList<TreeNode> childs;
    Object value;
    public String type;
    public SymbolTable scope;
    public TreeNode(){
        operation = "";
        childs = new ArrayList();
    }
    public TreeNode(String o){
        operation = o;
        childs = new ArrayList();
    }
    public TreeNode(String o, String t){
        operation = o;
        type = t;
        childs = new ArrayList();
    }
    public TreeNode(String o, TreeNode... child){
        operation = o;
        childs = new ArrayList();
        childs.addAll(Arrays.asList(child));
    }
    public TreeNode(String o, String t, TreeNode... child){
        operation = o;
        type = t;
        childs = new ArrayList();
        childs.addAll(Arrays.asList(child));
    }
    public TreeNode(String o, Object... child){
        operation = o;
        childs = new ArrayList();
        for(Object c : child){
            childs.add((TreeNode)c);
        }
    }
    public TreeNode(String o, String t, Object... child){
        operation = o;
        type = t;
        childs = new ArrayList();
        for(Object c : child){
            childs.add((TreeNode)c);
        }
        
    }
    public TreeNode(Object val){
        operation = "";
        childs = new ArrayList();
        value = val;
    }
    public TreeNode(Object val, String type, TreeNode... child){
        operation = "";
        childs = new ArrayList();
        childs.addAll(Arrays.asList(child));
        value = val;
    }
    public TreeNode(Object val, TreeNode... child){
        operation = "";
        childs = new ArrayList();
        childs.addAll(Arrays.asList(child));
        value = val;
    }
    public void addChild(TreeNode newChild){
        childs.add(newChild);
    }
    public void addChild(Object newChild){
        childs.add((TreeNode)newChild);
    }
    public ArrayList getChilds(){
        if(childs == null) childs = new ArrayList();
        return childs;
    }
    public Object empty(){
        return childs.removeAll(childs);
    }
    public String toString(){
        if(this.operation == null)
            return this.value.toString();
        else
            return this.operation;
    }
    public String getOperation(){
        return this.operation;
    }
}
