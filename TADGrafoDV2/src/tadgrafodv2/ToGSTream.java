/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.util.LinkedList;
import org.graphstream.graph.*; //Não consigo importar isso
import org.graphstream.graph.implementations.*;//Não consigo importar isso

/**
 *
 * @author 20161bsi0403
 */
public class ToGSTream { // De acordo com o prof, ta dando erro
    private Graph graph  = null;
    
    
    public ToGSTream(TADGrafoDV2 g, String idnome){
        graph = new SingleGraph(idnome); //Não consigo importar isso
        
        LinkedList<Vertex> listVs = g.vertices();
        LinkedList<Edge> listEs = g.edges();
        
        for(int i=0; i<listVs.size(); i++){
            graph.addNode(listVs.get(i).getLabel());
        }
        
        for(int i=0; i<listEs.size(); i++){
            Edge e = listEs.get(i);
            Vertex[] terminais = g.endVertices(e.getLabel());
            graph.addEdge(e.getLabel(), terminais[0].getLabel(), terminais[1].getLabel());
        }
    }
    
    public void exibe(){
        graph.display();
    }    
     /*   graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB")
       */ 
}

