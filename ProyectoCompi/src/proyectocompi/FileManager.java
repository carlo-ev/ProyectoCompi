/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class FileManager {
    
    JTextArea textPane;
    JLabel statusOutput;
    JLabel documentOutput;
    String filePath = "";
    
    public FileManager(){}
    public FileManager(String path){
        this.filePath = path; 
    }
    public FileManager(String path, JTextArea origin){
        this.filePath = path;
        this.textPane = origin;
    }
    public FileManager(String path, JTextArea origin,  JLabel statusBar){
        this.filePath = path;
        this.textPane = origin;
        this.statusOutput = statusBar;
    }
    public FileManager(String path, JTextArea origin, JLabel statusBar, JLabel documentBar){
        this.filePath = path;
        this.textPane = origin;
        this.statusOutput = statusBar;
        this.documentOutput = documentBar;
        if(path.isEmpty())
            documentBar.setText("Archivo Actual: editorCode.tie");
    }
    
    public boolean writeContent(){
        if(textPane != null)
            return writeFile(textPane.getText());
        return false;
    }
    
    public boolean writeContent(String content){
        return writeFile(content);
    }
    
    public boolean writeFile(String content){
        boolean wroteFile;
        BufferedWriter writer = null;
        String path = getFilePath();
        try{
            setStatus("Opening File... "+path);
            File codeFile = new File(path);
            writer = new BufferedWriter(new FileWriter(codeFile));
            setStatus("Writing File...");
            writer.write(content);
            wroteFile = true;
            setStatus("Done");
        }catch(Exception ex){
            ex.printStackTrace();
            setStatus("Unable to Write File!");
            wroteFile = false;
        }finally{
            try{
                if(writer != null)
                    writer.close();
            }catch(Exception exClose){}
        }
        return wroteFile;
    }
    
    public String readFile(){
        boolean openedFile;
        BufferedReader reader = null;
        String path = getFilePath();
        String content = "";
        try{
            setStatus("Opening File... "+path);
            File codeFile = new File(path);
            setStatus("Reading File...");
            reader = new BufferedReader(new FileReader(codeFile));
            String current = "";
            while( (current = reader.readLine()) != null )
                content += (current + "\n");
            setStatus("Done");
            if(textPane != null){
                textPane.setText("");
                textPane.append(content);
            }
        }catch(Exception ex){
            setStatus("Unable to Read File!");
            ex.printStackTrace();
        } finally{
            try{
                if(reader != null)
                    reader.close();
            }catch(Exception exClose){}
        }
        return content;
    }
    
    public String getFilePath(){
        if (filePath.isEmpty()) {
            return "editorCode.tie";
        }
        return filePath;
    }
    public void setFilePath(String path){
        filePath = path;
        if(documentOutput != null)
            documentOutput.setText("Archivo Actual: "+path);
    }
    public void setStatus(String status){
        if (statusOutput != null) {
            statusOutput.setText(status);
        }else{
            System.out.println(status);
        }
    }
    public String getStatus(){
        if(statusOutput != null)
            return statusOutput.getText();
        return "";
    }
    public void setTextPane(JTextArea origin){
        textPane = origin;
    }
    public JTextArea getTextPane(){
        return textPane;
    }
    
}
