/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;
import java.io.File;
/**
 *
 * @author carlo
 */
public class FlexCreator {
    
    public static void main(String[] args) {
        jflex.Main.generate(new File ("src\\proyectocompi\\LenguajeCompi.flex"));
    }
    
}
