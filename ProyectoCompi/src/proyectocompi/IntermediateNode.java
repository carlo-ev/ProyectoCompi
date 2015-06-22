/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class IntermediateNode {
    String operator;
    String operandum1;
    String operandum2;
    String result;
    
    public IntermediateNode(String operator, String operandum1, String operandum2, String result)
    {
        this.operator = operator;
        this.operandum1 = operandum1;
        this.operandum2 = operandum2;
        this.result = result;
    }
    
}
