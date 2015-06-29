/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author carlo
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    
    FileManager currentFileManager;
    boolean documentSaved = true;
    final JFileChooser fileChooser = new JFileChooser();
    ArrayList<String> past = new ArrayList<String>();
    ArrayList<String> future = new ArrayList<String>();
    HashMap<String, String> shortcuts = new HashMap<String, String>();
    JTree tree;
    DefaultMutableTreeNode ast;
    
    public Editor() {
        initComponents();
        
        //Disable All output tabs of code and tree
        this.tabs.setEnabledAt(0, true);
        this.tabs.setEnabledAt(1, false);
        this.tabs.setEnabledAt(2, false);
        this.tabs.setEnabledAt(3, false);
        
        //Parser Setup
        TieLexer.outputArea = this.outputPane;
        
        
        //Window Icon
        ImageIcon img = new ImageIcon("icon.png");
        
        //Editor Line Numbering
        this.setIconImage( img.getImage() );
        TextLineNumber tln = new TextLineNumber(editorTextArea);
        editorScroll.setRowHeaderView( tln );
        
        //File Chooser Conf
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;
                String extension = getExtension(f);
                if (extension != null) {
                    switch(extension){
                        case "tie":
                        case "txt":
                            return true;
                        default: return false;
                    }
                }else
                    return false;
            }
            @Override
            public String getDescription() {
                return  " *.tie & *.txt ";
            }
        });
        
        //Editor Area Change Events 
        this.editorTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { documentSaved = false; }
            @Override
            public void removeUpdate(DocumentEvent e) { documentSaved = false; }
            @Override
            public void changedUpdate(DocumentEvent e) {   }
        });
        
        //Program Close Events
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                onProgramExit();
            }
        });
        
        //Editor Shortcuts
        shortcuts.put("run", "\n\n\n\n/run");
        shortcuts.put("rep", "(i=0;i<10;i=i+1)\n\n/rep");
        shortcuts.put("set", "(id)\nopt (1)\n\n/opt\n\n/set");
        shortcuts.put("nil", " act funcion1()\n\n/act");
        shortcuts.put("#*", "\n\n*#");
        shortcuts.put("con", "(true)\n\n/con");
        shortcuts.put("til", "(true)\n\n/til");
        shortcuts.put("yet", "\n\n/yet");
        
    }
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
          ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
    public void onProgramExit(){
        if (documentSaved || this.currentFileManager.isDefaultFile()) {
            System.exit(0);
        }else{
            Object[] options = { "Guardar y Salir", "Salir", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(this, "El Documento actual no a sido guardado, desea...?", "Documento Sin Guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch(eleccion){
                case 0: this.currentFileManager.writeContent(); System.exit(0); break;
                case 1: System.exit(0); break;
                case 2: default:
            }
        }
    }
    
    public JTextArea getOutputPane(){
        return this.outputPane;
    }
    public JTextArea getEditorPane(){
        return this.editorTextArea;
    }
    public JLabel getStatusBar(){
        return this.statusBar;
    }
    public JLabel getDocumentBar(){
        return this.documentBar;
    }
    void setFileManager(FileManager current){
        this.currentFileManager = current;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        outputScroll = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextArea();
        outputLabel = new javax.swing.JLabel();
        statusBar = new javax.swing.JLabel();
        documentBar = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        editorScroll = new javax.swing.JScrollPane();
        editorTextArea = new javax.swing.JTextArea();
        treeScrollPane = new javax.swing.JScrollPane();
        intermediateScroll = new javax.swing.JScrollPane();
        intermediateTextArea = new javax.swing.JTextArea();
        finalScroll = new javax.swing.JScrollPane();
        finalTextArea = new javax.swing.JTextArea();
        topMenuBar = new javax.swing.JMenuBar();
        barFileMenu = new javax.swing.JMenu();
        barOpenButton = new javax.swing.JMenuItem();
        barSaveButton = new javax.swing.JMenuItem();
        barExitButton = new javax.swing.JMenuItem();
        barEditMenu = new javax.swing.JMenu();
        barUndoButton = new javax.swing.JMenuItem();
        barRedoButton = new javax.swing.JMenuItem();
        barClearButton = new javax.swing.JMenuItem();
        barCopyButton = new javax.swing.JMenuItem();
        barRunMenu = new javax.swing.JMenu();
        barCheckButton = new javax.swing.JMenuItem();
        barCompileButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TIE Editor");
        setLocationByPlatform(true);
        setResizable(false);

        outputPane.setBackground(new java.awt.Color(0, 0, 0));
        outputPane.setColumns(20);
        outputPane.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        outputPane.setForeground(java.awt.Color.green);
        outputPane.setRows(5);
        outputScroll.setViewportView(outputPane);

        outputLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        outputLabel.setText("Output");

        statusBar.setText("Ready");
        statusBar.setToolTipText("");

        documentBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tabs.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        editorTextArea.setColumns(20);
        editorTextArea.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        editorTextArea.setRows(5);
        editorTextArea.setTabSize(1);
        editorTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editorKeyPress(evt);
            }
        });
        editorScroll.setViewportView(editorTextArea);

        tabs.addTab("Editor", editorScroll);
        tabs.addTab("Tree", treeScrollPane);

        intermediateTextArea.setColumns(20);
        intermediateTextArea.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        intermediateTextArea.setRows(5);
        intermediateScroll.setViewportView(intermediateTextArea);

        tabs.addTab("Intermediate Code", intermediateScroll);

        finalTextArea.setColumns(20);
        finalTextArea.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        finalTextArea.setRows(5);
        finalScroll.setViewportView(finalTextArea);

        tabs.addTab("Final Code", finalScroll);

        barFileMenu.setText("File");

        barOpenButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        barOpenButton.setText("Open");
        barOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barOpenButtonActionPerformed(evt);
            }
        });
        barFileMenu.add(barOpenButton);

        barSaveButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        barSaveButton.setText("Save");
        barSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barSaveButtonActionPerformed(evt);
            }
        });
        barFileMenu.add(barSaveButton);

        barExitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        barExitButton.setText("Exit");
        barExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barExitButtonActionPerformed(evt);
            }
        });
        barFileMenu.add(barExitButton);

        topMenuBar.add(barFileMenu);

        barEditMenu.setText("Edit");

        barUndoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        barUndoButton.setText("Undo");
        barUndoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barUndoButtonActionPerformed(evt);
            }
        });
        barEditMenu.add(barUndoButton);

        barRedoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        barRedoButton.setText("Redo");
        barRedoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barRedoButtonActionPerformed(evt);
            }
        });
        barEditMenu.add(barRedoButton);

        barClearButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        barClearButton.setText("Clear Editor");
        barClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barClearButtonActionPerformed(evt);
            }
        });
        barEditMenu.add(barClearButton);

        barCopyButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        barCopyButton.setText("Copy Code");
        barCopyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barCopyButtonActionPerformed(evt);
            }
        });
        barEditMenu.add(barCopyButton);

        topMenuBar.add(barEditMenu);

        barRunMenu.setText("Run");

        barCheckButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        barCheckButton.setText("Check Code");
        barCheckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barCheckButtonActionPerformed(evt);
            }
        });
        barRunMenu.add(barCheckButton);

        barCompileButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        barCompileButton.setText("Compile");
        barRunMenu.add(barCompileButton);

        topMenuBar.add(barRunMenu);

        setJMenuBar(topMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs)
                    .addComponent(outputScroll)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(documentBar, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(documentBar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        tabs.getAccessibleContext().setAccessibleName("tabs");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barClearButtonActionPerformed
        this.editorTextArea.setText("");
    }//GEN-LAST:event_barClearButtonActionPerformed

    private void barCheckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barCheckButtonActionPerformed
        this.currentFileManager.writeContent();
        if (this.editorTextArea.getText().trim().isEmpty())
            return;
        
        TieLexer lexer;
        try{
            lexer = new TieLexer(this.currentFileManager.getCurrentFile());
            this.outputPane.setText("");
            TieParser parser = new TieParser(lexer);
            parser.setOutput(this.outputPane);
            parser.parse();
            if (parser.errors == 0 && TieLexer.lexErrors.isEmpty()) {
                this.outputPane.append("> Successful Code Parsing! < \n");
                this.createJtree(parser.AST);
                this.tabs.setEnabledAt(1, true);
                TieSemantics semanticCheck = new TieSemantics(parser.AST);
                semanticCheck.outputPane = this.outputPane;
                semanticCheck.typeCheck();

                if(semanticCheck.typeErrors.isEmpty()){
                    IntermediateCode interCode = new IntermediateCode();
                    interCode.traverseTree(parser.AST.childs.get(0));
                    this.tabs.setEnabledAt(2, true);
                    this.intermediateTextArea.setText("");
                    
                    for(IntermediateNode line : interCode.interTable){
                        this.intermediateTextArea.append( 
                                line.operator + "  " + line.operandum1 + "  " + line.operandum2 + "  " + line.result + "\n"
                        );
                    }
                    
                    this.finalTextArea.setText("");
                    FinalCode fin = new FinalCode(interCode.interTable, semanticCheck.allTables);
                    this.finalTextArea.setText( fin.generate() );
                    this.tabs.setEnabledAt(3, true);
                }
            }
        }catch(Exception ex){
            System.out.println("Parser Crashed Unexpectedly"+ ex.toString() );
            ex.printStackTrace();
        }
    }//GEN-LAST:event_barCheckButtonActionPerformed

    private void createJtree(TreeNode parent){
        //Tree Node icons
        ImageIcon node1 = new ImageIcon("node1.png");
        ImageIcon node2 = new ImageIcon("node2.png");
        
        DefaultTreeCellRenderer treeRenderer = new DefaultTreeCellRenderer();
        treeRenderer.setLeafIcon(node2);
        treeRenderer.setClosedIcon(node1);
        treeRenderer.setOpenIcon(node1);
        
        ast = new DefaultMutableTreeNode(parent.operation);
        createParentNode(ast, parent.childs.get(0));
        tree = new JTree(new DefaultTreeModel(ast));
        tree.setCellRenderer(treeRenderer);
        treeScrollPane.getViewport().removeAll();
        treeScrollPane.getViewport().add(tree);
    }
    
    private void createParentNode(DefaultMutableTreeNode guiNode, TreeNode parent){
        if (!parent.operation.isEmpty()) {
            DefaultMutableTreeNode actual = new DefaultMutableTreeNode(parent.operation);
            guiNode.add(actual);
            if (parent.childs.size() > 0) {
                for (TreeNode temp : parent.childs) {
                    createParentNode(actual, temp);
                }
            }
        }
    }
    
    private void printNode(TreeNode node, String space){
        System.out.println(space+"_"+node.toString());
        for(Object child : node.getChilds()){
            printNode((TreeNode)child, space+"____");
        }
    }
    
    private void barExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barExitButtonActionPerformed
        onProgramExit();
    }//GEN-LAST:event_barExitButtonActionPerformed

    private void barSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barSaveButtonActionPerformed
        this.currentFileManager.writeContent();
    }//GEN-LAST:event_barSaveButtonActionPerformed

    private void barOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barOpenButtonActionPerformed
        int eleccion = this.fileChooser.showOpenDialog(this);
        if(eleccion == JFileChooser.APPROVE_OPTION){
            this.currentFileManager.setFilePath( this.fileChooser.getSelectedFile().getPath() );
            this.currentFileManager.readFile();
            documentSaved = true;
        }
    }//GEN-LAST:event_barOpenButtonActionPerformed

    private void barCopyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barCopyButtonActionPerformed
        String editorContent = this.editorTextArea.getText();
        StringSelection contentSelection = new StringSelection(editorContent);
        Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
        board.setContents(contentSelection, null);
    }//GEN-LAST:event_barCopyButtonActionPerformed

    private void barUndoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barUndoButtonActionPerformed
        if(!past.isEmpty()){
            future.add( editorTextArea.getText() );
            editorTextArea.setText( past.remove(past.size()-1) );
        }
    }//GEN-LAST:event_barUndoButtonActionPerformed

    private void barRedoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barRedoButtonActionPerformed
        if (!future.isEmpty()) {
            past.add( editorTextArea.getText() );
            editorTextArea.setText( future.remove(future.size()-1));
        }
    }//GEN-LAST:event_barRedoButtonActionPerformed

    private void editorKeyPress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorKeyPress
        int keyCode = evt.getKeyCode();
        
        if (keyCode == java.awt.event.KeyEvent.VK_TAB) {
            int currentPosition = editorTextArea.getCaretPosition()-1;
            String lastWord = "";
            for(;currentPosition >= 0; currentPosition--){
                char currentChar = editorTextArea.getText().charAt(currentPosition);
                if ( currentChar != ' ' && currentChar != '\n')
                    lastWord += currentChar;
                else
                    break;
            }
            lastWord = (new StringBuilder(lastWord)).reverse().toString();
            if(shortcuts.containsKey(lastWord))
                editorTextArea.insert(shortcuts.get(lastWord), editorTextArea.getCaretPosition());
            
        }else if (!evt.isActionKey()) {
            past.add( editorTextArea.getText() );
            future.removeAll(future);
        }
    }//GEN-LAST:event_editorKeyPress

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem barCheckButton;
    private javax.swing.JMenuItem barClearButton;
    private javax.swing.JMenuItem barCompileButton;
    private javax.swing.JMenuItem barCopyButton;
    private javax.swing.JMenu barEditMenu;
    private javax.swing.JMenuItem barExitButton;
    private javax.swing.JMenu barFileMenu;
    private javax.swing.JMenuItem barOpenButton;
    private javax.swing.JMenuItem barRedoButton;
    private javax.swing.JMenu barRunMenu;
    private javax.swing.JMenuItem barSaveButton;
    private javax.swing.JMenuItem barUndoButton;
    private javax.swing.JLabel documentBar;
    private javax.swing.JScrollPane editorScroll;
    private javax.swing.JTextArea editorTextArea;
    private javax.swing.JScrollPane finalScroll;
    private javax.swing.JTextArea finalTextArea;
    private javax.swing.JScrollPane intermediateScroll;
    private javax.swing.JTextArea intermediateTextArea;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputPane;
    private javax.swing.JScrollPane outputScroll;
    private javax.swing.JLabel statusBar;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JMenuBar topMenuBar;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
}
