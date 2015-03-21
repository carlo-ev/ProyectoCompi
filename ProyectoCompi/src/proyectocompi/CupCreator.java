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
public class CupCreator {
    
    public static void main(String[] args) {
        String opciones [] = new String[5];
        //Direccion Destino
        opciones[0] = "-destdir";
        opciones[1] = "src\\ProyectoCompi\\";
        //Nombre Archivo
        opciones[2] = "-parser";
        opciones[3] = "Analizador";
        opciones[4] = "src\\ProyectoCompi\\analizador.cup";
        
        try{
            java_cup.Main.main(opciones);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
