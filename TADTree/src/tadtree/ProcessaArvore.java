/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadtree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Salzman
 */
public class ProcessaArvore {
    private TreeTPAV1 tree;
    
    public ProcessaArvore(TreeTPAV1 t){
        this.tree = t;
    }
    
    
    
    public void write() throws IOException{
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("saidafinal.tgf", true));
        String str;
        LinkedList<Object> keys = new LinkedList<Object>(); 
        boolean writing = true;
        Node root = this.tree.getRootElement();
        Node nodeToWrite = root;
        
        /* EM PRODUÇÃO 
        while(writing){
            str = nodeToWrite.toString();
            bufferWriter.write(str);
            if (root.getNumberOfChildren() !=0){
                String output = "";
            }
        
    }
    
    
    
    public void writeNode(Node root){
        if (root.getNumberOfChildren() !=0){
            String output = "";
            output += "(";
            output += inOrderNewick(root.child1);
            output += ",";
            output += inOrderNewick(root.child2);
            output += ")";
            return output;
        } 
        else {
            return root.getSeq();
        }
    }*/
}
