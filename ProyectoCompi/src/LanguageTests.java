/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */

import java.util.Scanner;
public class LanguageTests {

    public static void main(String[] args) {
        //inverseString();
        ltSixty();
    }
    
    static public void ltSixty()
    {
        Scanner in = new Scanner(System.in);
        int count = 0;
        System.out.println("Ingrese la cantidad de numeros a comparar.");
        int numTot = in.nextInt();
        
        for (int i = 0; i < numTot; i++) {
            System.out.println("Ingrese un numero");
            int num = in.nextInt();
            
            if(num < 60)
                count++;
        }
        
        System.out.println("la cantidad de numeros menores de 60 es: " + count);
    }
    
    static public void inverseString()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la palabra a invertir");
        String message = in.nextLine();
        
        for (int i = message.length()-1; i >= 0; i--) {
            System.out.print(message.charAt(i));
        }
        
    }
}
