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
public class ProyectoCompi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Editor gui = new Editor();
        gui.setVisible(true);
        FileManager codeFile = new FileManager("", gui.getEditorPane(), gui.getStatusBar(), gui.getDocumentBar());
        gui.setFileManager(codeFile);
        
    }
    
}
