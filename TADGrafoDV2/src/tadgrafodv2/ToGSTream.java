/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import SingleGraph;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author 20161bsi0403
 */
public class ToGSTream {
    private Graph graph  = null;
    
    
    public ToGSTream(TADGrafoDV2 g, String idnome){
        graph = new SingleGraph(idnome);
        
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB")
        
    }
}
