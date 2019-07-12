/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.caminhamento;

import dicionario.TADDicChain;
import ifes.bsi.tpa.grafo.Edge;
import ifes.bsi.tpa.grafo.TADGrafoDV3;
import ifes.bsi.tpa.grafo.Vertex;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Salzman
 */
public class ProcessaGrafo {
    private TADGrafoDV3 grafo;
    private LinkedList<Vertex> lstVertexGraph;
    private LinkedList<Edge> lstEdgeGraph;
    
    public ProcessaGrafo(TADGrafoDV3 grafo) {
        this.grafo = grafo;
        this.lstVertexGraph = this.grafo.vertices();
        this.lstEdgeGraph = this.grafo.edges();
    }
    
    
    //FIFO queue
    public LinkedList<Vertex> bfs(String vertexLabel) {
        Vertex mainVertex = this.grafo.getVertex(vertexLabel);
        Queue fila = new LinkedList<Vertex>();
        Queue filaSaida = new LinkedList<Vertex>();
        
        fila.add(mainVertex);
        
        while(!fila.isEmpty()) {
            Vertex mainQueue = (Vertex)fila.remove();
            LinkedList<Vertex> lstVizinhosVertex = this.grafo.outAdjacenteVertices(mainQueue.getLabel());
            
            if(!lstVizinhosVertex.isEmpty()) {
                if(!filaSaida.contains(mainQueue)) {
                    filaSaida.add(mainQueue);
                }
                
                for(Vertex neighbor : lstVizinhosVertex) {
                    if(!filaSaida.contains(neighbor)) {
                        fila.add(neighbor);
                    }
                }
            }
            else {
                if(!filaSaida.contains(mainQueue))
                    filaSaida.add(mainQueue);
            }
        }
        
        return (LinkedList<Vertex>) filaSaida;
                
        
    }
    
    public LinkedList<Vertex> dfs(String vertexLabelInicial){ 
        Vertex mainVertex = this.grafo.getVertex(vertexLabelInicial);
        LinkedList<Vertex> stackVisited = new LinkedList<Vertex>();
        LinkedList<Vertex> stackSaida = new LinkedList<Vertex>();
        
        stackVisited.add(mainVertex);
        
        while(!stackVisited.isEmpty()) {
            Vertex topVertex = stackVisited.pollLast();
            LinkedList<Vertex> lstVizinhosVertex = this.grafo.outAdjacenteVertices(topVertex.getLabel()); 
            
            if(!lstVizinhosVertex.isEmpty()) {
                if(!stackSaida.contains(topVertex)) {
                    stackSaida.add(topVertex);
                }
                
                for(Vertex neighbor : lstVizinhosVertex) {
                    if(!stackSaida.contains(neighbor)) {
                        stackVisited.add(neighbor);
                    }
                }
            }
            else {
                if(!stackSaida.contains(topVertex)) {
                    stackSaida.add(topVertex);
                }
            }
            
        }
 
        return stackSaida;
    }   
    
    public DSFloydW cmFWarshall() {
        int quantVertex = this.grafo.numVertices(); 
        int[][] stdCustoMatrix = getCustoPadrao();
        int[][] newCustoMatrix = new int[quantVertex][quantVertex];
        TADDicChain dicLabels = dicLabelsGrafo();
        
        for(int k=0; k<quantVertex; k++){
            for(int i=0; i<quantVertex; i++){
                for(int j=0; j<quantVertex; j++){
                    if(stdCustoMatrix[i][k] + stdCustoMatrix[k][j] < stdCustoMatrix[i][j]&&(stdCustoMatrix[i][k] != Integer.MAX_VALUE && stdCustoMatrix[k][j] != Integer.MAX_VALUE )){
                        stdCustoMatrix[i][j] = stdCustoMatrix[i][k] + stdCustoMatrix[k][j];
                        newCustoMatrix[i][j] = k;
                    }
                    
                }
            }
        }

        return new DSFloydW(stdCustoMatrix, dicLabels);
    }
    
    public DSDijkstra cmDijkstra(String origem) {
        Vertex original_vertex = this.grafo.getVertex(origem);
        int pos = original_vertex.getId(); 
        LinkedList<Vertex> lstVertex = this.lstVertexGraph;
        
        int quantVertex = this.grafo.numVertices();
        int[] peso = new int[quantVertex];
        
        String[] caminho = new String[quantVertex];
        LinkedList<Integer> vizinhos = new LinkedList<>();
        
        for(int i=0; i<quantVertex; i++){
            peso[i] = Integer.MAX_VALUE;
            vizinhos.add(i);
        }
        
        
        peso[pos] = 0;
        caminho[pos] = original_vertex.getLabel();
        
        while(!vizinhos.isEmpty()){
            vizinhos.remove((Integer)pos);
            int[] pesoAux = peso.clone();
            String[] caminhoAux = caminho.clone();
            LinkedList<Vertex> todos_vizinhos = this.grafo.adjacentVertices(lstVertex.get(pos).getLabel());
            
            for(int i=0; i<todos_vizinhos.size(); i++){
                Vertex vizinho = todos_vizinhos.get(i);
                Edge e = this.grafo.getEdge(lstVertex.get(pos).getLabel(), vizinho.getLabel());
                
                if(e != null&&vizinhos.contains(vizinho.getId())){
                    if(e.getCusto() + peso[pos] < peso[vizinho.getId()]){
                        pesoAux[vizinho.getId()] = e.getCusto() + peso[pos];
                        caminhoAux[vizinho.getId()] = caminho[pos]+':'+vizinho.getLabel();
                    }
                }
            }
            
            peso = pesoAux;
            caminho = caminhoAux;
            

            if(vizinhos.size() == 1){
                pos = vizinhos.get(0);
            }
            else if(vizinhos.size() > 1){
                int menor_custo = Integer.MAX_VALUE;
                int newPos = 0;
                for(int i=0; i<pesoAux.length; i++){
                    if(pesoAux[i] < menor_custo && vizinhos.contains(i)){
                        menor_custo = pesoAux[i];
                        newPos = i;
                    }
                }
                pos = newPos;
            }
        }
        return new DSDijkstra(peso, caminho);
    }
    
    public DSDijkstra cmBFord(String origem){
//vazio        
        
        return null;
    }
    
    
    private int[][] getCustoPadrao() {
        int quantVertex = this.grafo.numVertices(); 
        int[][] stdCustoMatrix = new int[quantVertex][quantVertex];
        
        
        int line = -1;
        for(Vertex origin : this.lstVertexGraph){
            line++;
            int column = -1;
            for(Vertex destiny : this.lstVertexGraph){
                column++;
                if(origin.getId() != destiny.getId()){
                    Edge e = this.grafo.getEdge(origin.getLabel(), destiny.getLabel());
                    
                    if(e != null){
                        stdCustoMatrix[line][column] = e.getCusto();
                    }
                    else {
                        
                        stdCustoMatrix[line][column] = Integer.MAX_VALUE;
                    }
                    
                }
                else {
                    stdCustoMatrix[line][column] = 0;
                }
            }
        }
        
        return stdCustoMatrix;
    } 
    
    private TADDicChain dicLabelsGrafo(){
        TADDicChain dic = new TADDicChain(null);
        
        for( Vertex v : this.lstVertexGraph) {
            dic.insertItem(v.getLabel(), v.getId());
        }
        
        return dic;
    }
    
}
