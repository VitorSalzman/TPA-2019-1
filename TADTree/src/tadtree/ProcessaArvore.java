/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadtree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Salzman
 */
public class ProcessaArvore {
    private TreeTPAV1 tree;
    BufferedWriter bufferWriter;
    String output="";
    public ProcessaArvore(TreeTPAV1 t) throws IOException{
        bufferWriter = new BufferedWriter(new FileWriter("saidafinal.newick", true));
        this.tree = t;
    }
    
    
    
    public void write() throws IOException{
        
        String str;
        LinkedList<Object> keys = new LinkedList<Object>(); 
        boolean writing = true;
        Node root = this.tree.getRootElement();
          
      
         
        output += "(";
        if (root.getNumberOfChildren() !=0){
                for(int i=0;i<root.getChildren().size();i++){
                    
                    writeNode((Node) root.getChildren().get(i));
                    //output += ",";
                }
        }
        output=output.substring(0,output.length()-1);
        bufferWriter.write(output);
        bufferWriter.write(")");
        bufferWriter.write((String) root.getData());
       
        bufferWriter.close();
    }
       
    
    
    
    public void writeNode(Node root) throws IOException{
            if(root.getNumberOfChildren()!=0){
                output += "(";
                for(int i=0;i<root.getNumberOfChildren();i++){
                    writeNode((Node) root.getChildren().get(i));
                }
                Character c = output.charAt(output.length()-1);
                if(c==',') output=output.substring(0,output.length()-1);
                output += ")";
                output+=(String) root.getData();
                output += ",";              
            }
                
            else{
                output+=(String) root.getData();
                output += ",";
                return;
            }
}
    
    public ArrayList<Node> bsf(Node root) {
        
        Queue fila = new LinkedList<Node>();
        ArrayList filaSaida = new ArrayList<Node>();
        
        fila.add(root);
        
        while(!fila.isEmpty()) {
            Node headQueue = (Node)fila.remove();
            ArrayList<Node> folhas = (ArrayList<Node>) root.getChildren();
            if(!folhas.isEmpty()) {
                if(!filaSaida.contains(headQueue)) {
                    filaSaida.add(headQueue);
                }
                
                for(Node destiny : folhas) {
                    if(!filaSaida.contains(destiny)) {
                        fila.add(destiny);
                    }
                }
            }
            else {
                if(!filaSaida.contains(headQueue))
                    filaSaida.add(headQueue);
            }
        }
        
        return (ArrayList<Node>) filaSaida;
    }
}


 /*
        String strSaltaLinha = "\n     |    \n    +----"+root.toString();
        bufferWriter.write(strSaltaLinha);
        
        if(root.getNumberOfChildren()==1){ // tentar encaixar em cima desse if
            Node next_child = (Node)root.getChildren().get(0);
            String str = "----+----"+next_child.toString();
            bufferWriter.write(str);
        }
        
        else if (root.getNumberOfChildren() !=0){
            for(int i=0;i<root.getNumberOfChildren();i++){
                writeNode((Node) root.getChildren().get(i));
            }*/