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
public class Token extends java_cup.runtime.Symbol{
    
    public int line;
    public int column;
    public String text;
    
    public Token(int type, int line, int column, String text){
        this(type, line, column, line, column, text, null);
    }
    public Token(int type, int line, int column, String text, Object value){
        this(type, line, column, line, column, text, value);
    }
    public Token(int type, int line, int column,  int left, int right, String text, Object value){
        super(type, left, right, value);
        this.line = line;
        this.column = column;
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
}
