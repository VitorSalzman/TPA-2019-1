/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

/**
 *
 * @author Salzman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TADGrafoDV2 grafo = new TADGrafoDV2("grafo alfabético");
        
        grafo.insereVertex("A", "Primeiro Vertice");
        grafo.insereVertex("B", "Segundo Vertice");
        grafo.insereVertex("C", "Terceiro Vertice");
        grafo.insereVertex("D", "Quarto Vertice");
        grafo.insereVertex("E", "Quinto Vertice");
        
       
        grafo.insereEdge("A", "B", "a", "Primeira Aresta");
        grafo.insereEdge("B", "A", "b", "Segunda Aresta");
        grafo.insereEdge("C", "A", "c", "Terceira Aresta");
        grafo.insereEdge("D", "C", "d", "Quarta Aresta");
        grafo.insereEdge("D", "A", "e", "Quinta Aresta");
        
        grafo.printmat();
        grafo.printgrafo();
        
        
        
        
        
    }
    
}
