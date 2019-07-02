/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Salzman
 */
public class NewProcessaGrafo {
    private TADGrafoDV3 grafo;
    private LinkedList<Vertex> lstVertexGraph;
    private LinkedList<Edge> lstEdgeGraph;
    
    public NewProcessaGrafo(TADGrafoDV3 grafo) {
        this.grafo = grafo;
        this.lstVertexGraph = this.grafo.vertices();
        this.lstEdgeGraph = this.grafo.edges();
    }
    
    public LinkedList<Vertex> bsf(String vertexLabel) {
        Vertex mainVertex = this.grafo.getVertex(vertexLabel);
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
        Vertex mainVertex = this.grafo.getVertex(vertexLabel);
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
	
	private int[][] custoBase(){
		// the adjacencyMatrix is the size of the number of vertices
		int[][] matAdjacent = new int[this.lstVertexGraph.size()][this.lstVertexGraph.size()];
		int l = -1;//id linha
		
                for(int i=0; i<lstVertexGraph.size(); i++){
                        Vertex vetOut = lstVertexGraph.get(i);
			l++;
			int c = -1;
			for(int j=0; j<lstVertexGraph.size(); j++){
                                Vertex vetIn = lstVertexGraph.get(i);
				c++;
				if(vetOut.getId() != vetIn.getId()) {
					Edge recent_edge = this.grafo.getEdge(vetOut.getLabel(),vetIn.getLabel());
					if(recent_edge != null) {
						matAdjacent[l][c] = recent_edge.getCusto();
					}
					else {
						
						matAdjacent[l][c] = Integer.MAX_VALUE; // Define-se o equivalente a "Infinito" no java.
					}
				}
				else {
					matAdjacent[l][c] =  0;
				}
			}
		}
		return matAdjacent;
	}
	
	public int[][] floydWarshallCost() {
		int[][] base_mat = this.custoBase();
		
		int id_vertex = 0;
		int[][] new_mat = null;
                
		while(id_vertex < this.lstVertexGraph.size()){
			new_mat = this.geraMenorValor(base_mat,id_vertex);
			base_mat = new_mat;
			id_vertex++;
		}
		return new_mat;
	}
    
    private int[][] geraMenorValor(int[][] mat,int id_vertex){
		int[][] mat_values = new int[this.lstVertexGraph.size()][this.lstVertexGraph.size()];
                
		for(int i=0; i <this.lstVertexGraph.size(); i++){
			
			mat_values[id_vertex][i] = mat[id_vertex][i];
                        if(id_vertex != i){ //se i for diferente de id_vertex, muda-se os 2 valores
                            mat_values[i][id_vertex] = mat[i][id_vertex];
			}
		}
                
		for(int l=0; l<this.lstVertexGraph.size(); l++) {
			if(l != id_vertex) {
				for(int c = 0; c < this.lstVertexGraph.size(); c++) {
					// geting the cost of the 2 possibilitys
					int variavel_referencia = mat[l][c];
					int variavelUm = mat[l][id_vertex];
					int variavelDois = mat[id_vertex][c];
					
					
					if((variavelUm==Integer.MAX_VALUE || variavelDois==Integer.MAX_VALUE)&& variavel_referencia <= Integer.MAX_VALUE) {
						mat_values[l][c] = variavel_referencia;
					}
					else if(variavel_referencia < ( variavelUm + variavelDois )) {//pega-se o menor valor
						mat_values[l][c] = variavel_referencia;
					}
					else {
						mat_values[l][c] = variavelUm + variavelDois;
					}
					
				}
			}
		}
		return mat_values;
	}

	private int getIndexVertex(String labelVertice) {
		for (int i = 0; i < this.lstVertexGraph.size(); i++) {
			if(this.lstVertexGraph.get(i).getLabel().equals(labelVertice)) {
				return i;
			}
		}
		return -1;
	}

	//return the index of the vertice with the less cost and remove from the list
	private int getIndexLessCost(int[] costList, LinkedList<Integer> unvisited) {
		int smallerVallue = Integer.MAX_VALUE;
		int indexSmaller = -1;
		for(int i = 0; i < costList.length; i++) {
			boolean isSmaller = costList[i] < smallerVallue;
			boolean isUnvisited = unvisited.contains((Integer)i);
			if(isSmaller && isUnvisited) {
				smallerVallue = costList[i];
				indexSmaller = i;
			}
		}
		return indexSmaller;
	}
	
	public int[] dijkstraCost(String primeiroVertex) {
		// an arry with the cost and the vertice index ref.
		int[]intervalo = new int[this.lstVertexGraph.size()];
		String[] caminho = new String[intervalo.length];
		String[] p = new String[intervalo.length];
		LinkedList<Integer> nao_visitado = new LinkedList<Integer>();
		for (int i = 0; i <p.length; i++) {
			intervalo[i]=Integer.MAX_VALUE;
			nao_visitado.add(i);
		}
                intervalo[getIndexVertex(primeiroVertex)]=0;
		int ind = getIndexVertex(primeiroVertex);
		caminho[ind] = primeiroVertex;
		while(nao_visitado.size() > 0) {
			nao_visitado.remove((Integer)ind);
			int[] intervalo_recente = intervalo.clone();
			String[] caminho_recente = caminho.clone();
			LinkedList<Vertex> relacoes_vertex = this.grafo.adjacenteVertices(this.lstVertexGraph.get(ind).getLabel());			
			for (Vertex vertice : relacoes_vertex) {
				boolean edge_nao_nulo = this.grafo.getEdge(this.lstVertexGraph.get(ind).getLabel(), vertice.getLabel()) != null;
				boolean is_naovisitado = nao_visitado.contains((Integer)getIndexVertex(vertice.getLabel()));
				if(edge_nao_nulo && is_naovisitado) {
					int custo_recente_orig = this.grafo.getEdge(this.lstVertexGraph.get(ind).getLabel(), vertice.getLabel()).getCusto() + intervalo[ind];
					int custo_recente = intervalo[getIndexVertex(vertice.getLabel())];
					if(custo_recente > custo_recente_orig) {
						intervalo_recente[getIndexVertex(vertice.getLabel())] = custo_recente_orig;
						p[getIndexVertex(vertice.getLabel())] = this.lstVertexGraph.get(ind).getLabel();
						caminho_recente[getIndexVertex(vertice.getLabel())] = caminho[ind] +'-'+ vertice.getLabel();
						 
					}
				}
			}
			intervalo = intervalo_recente;
			caminho = caminho_recente;
			if(nao_visitado.size() > 0) {
				ind = getIndexLessCost(intervalo,nao_visitado);
			}
		}
		return intervalo;
		
	}

	public int[] bellmanFord(String beginVertice) {
		int[] costList = new int[this.lstVertexGraph.size()];
		String[] predecessor = new String[costList.length];
		String[] path = new String[costList.length];
		for(int i = 0; i < costList.length; i++) {
			costList[i] = Integer.MAX_VALUE;
		}
		int indexSource = findIndexVerticeList(beginVertice);
		costList[indexSource] = 0;
		path[indexSource] = beginVertice;
		for(int i = 0; i < (this.lstVertexGraph.size()-1); i++) {
			int[] interationCost = costList.clone();
			for(int j = 0; j < interationCost.length; j++) {
				if(interationCost[j] != Integer.MAX_VALUE) {
					Vertice currentVertice = this.verticesGrafo[j];
					int currentIndex = findIndexVerticeList(currentVertice.getLabel());
					LinkedList<Vertice> connections = this.grafo.adjacenteVertices(currentVertice.getLabel());
					for(Vertice neighborV : connections) {
						Edge linkVertices = this.grafo.getEdge(currentVertice.getLabel(), neighborV.getLabel());
						if (linkVertices != null) {
							int connectionCost = interationCost[currentIndex]+linkVertices.getCost();
							boolean isConnectionSmaller = interationCost[findIndexVerticeList(neighborV.getLabel())] > connectionCost;
							if(isConnectionSmaller) {
								interationCost[findIndexVerticeList(neighborV.getLabel())] = connectionCost;
								predecessor[findIndexVerticeList(neighborV.getLabel())] = this.verticesGrafo[currentIndex].getLabel();
								path[findIndexVerticeList(neighborV.getLabel())] = path[currentIndex] +';'+ neighborV.getLabel();
							}
						}
					}
				}
			}
			if(Arrays.equals(interationCost, costList)) {
				costList = interationCost;
				break;
			}
			else {
				costList = interationCost;
			}
		}
		return costList;
	}
}
