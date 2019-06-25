/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Salzman
 */
public class ProcessaGrafo {
    private TADGrafoDV2 grafo;
    private LinkedList<Vertex> lstVertexGraph;
    private LinkedList<Edge> lstEdgeGraph;
    
    public ProcessaGrafo(TADGrafoDV2 grafo) {
        this.grafo = grafo;
        this.lstVertexGraph = this.grafo.vertices();
        this.lstEdgeGraph = this.grafo.edges();
    }
    
    public LinkedList<Vertex> bsf(String vertexLabel) {
        Vertex mainVertex = this.grafo.getVertice(vertexLabel);
        Queue fila = new LinkedList<Vertex>();
        Queue filaSaida = new LinkedList<Vertex>();
        
        fila.add(mainVertex);
        
        while(!fila.isEmpty()) {
            Vertex headQueue = (Vertex)fila.remove();
            LinkedList<Vertex> destinyVertex = this.grafo.outAdjacenteVertices(headQueue.getLabel());
            if(!destinyVertex.isEmpty()) {
                if(!filaSaida.contains(headQueue)) {
                    filaSaida.add(headQueue);
                }
                
                for(Vertex destiny : destinyVertex) {
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
        
        return (LinkedList<Vertex>) filaSaida;
        
    }
    
    public LinkedList<Vertex> dsf(String vertexLabel) {
        Vertex mainVertex = this.grafo.getVertice(vertexLabel);
        LinkedList<Vertex> stack = new LinkedList<Vertex>();
        LinkedList<Vertex> stackDFS = new LinkedList<Vertex>();
        
        stack.add(mainVertex);
        
        while(!stack.isEmpty()) {
            Vertex headQueue = (Vertex)stack.remove();
            LinkedList<Vertex> destinyVertex = this.grafo.outAdjacenteVertices(headQueue.getLabel());
            if(!destinyVertex.isEmpty()) {
                if(!stackDFS.contains(headQueue)) {
                    stackDFS.add(headQueue);
                }
                
                for(Vertex destiny : destinyVertex) {
                    if(!stackDFS.contains(destiny)) {
                        stack.add(destiny);
                    }
                }
            }
            else {
                if(!stackDFS.contains(headQueue))
                    stackDFS.add(headQueue);
            }
        }
        
        return (LinkedList<Vertex>) stackDFS;
        
    }
    
    
}
