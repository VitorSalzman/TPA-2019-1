/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;


import dicionario.TADDicChain;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Salzman
 */
public class ProcessaGrafo {
    private TADGrafoD grafo;
    private LinkedList<Vertex> lstVertexGraph;
    private LinkedList<Edge> lstEdgeGraph;
    
    public ProcessaGrafo(TADGrafoD grafo) {
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
	
	public DSFloydW floydWarshallCost() {
		int[][] base_mat = this.custoBase();
		
		int id_vertex = 0;
		int[][] new_mat = null;
                TADDicChain dic = new TADDicChain(null);
        
                for( Vertex v : this.lstVertexGraph) {
                    dic.insertItem(v.getLabel(), v.getId());
                }
                
		while(id_vertex < this.lstVertexGraph.size()){
			new_mat = this.geraMenorValor(base_mat,id_vertex);
			base_mat = new_mat;
			id_vertex++;
		}
                
                DSFloydW dsf = new DSFloydW(new_mat, dic);
		return dsf;
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

	
	private int getIndexMenorCusto(int[] list_custos, LinkedList<Integer> nao_visitado) {
		int menor_valor = Integer.MAX_VALUE;
		int id_menor = -1;
		for(int i = 0; i < list_custos.length; i++) {
			boolean isSmaller = list_custos[i] < menor_valor;
			boolean isUnvisited = nao_visitado.contains((Integer)i);
			if(isSmaller && isUnvisited) {
				menor_valor = list_custos[i];
				id_menor = i;
			}
		}
		return id_menor;
	}
	
	public DSDijkstra dijkstraCost(String primeiroVertex) {
		
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
			LinkedList<Vertex> relacoes_vertex = this.grafo.adjacentVertices(this.lstVertexGraph.get(ind).getLabel());			
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
				ind = getIndexMenorCusto(intervalo,nao_visitado);
			}
		}
                
                DSDijkstra dsd = new DSDijkstra(intervalo, p);
		return dsd;
		
	}

	public int[] bellmanFord(String primeiroVertex) {
		int[] array_custos = new int[this.lstVertexGraph.size()];
		String[] p = new String[array_custos.length];
		String[] caminho = new String[array_custos.length];
		for(int i=0; i<array_custos.length; i++) { //modularizar
			array_custos[i] = Integer.MAX_VALUE;
		}
		int ind = getIndexVertex(primeiroVertex);
		array_custos[ind] = 0;
		caminho[ind] = primeiroVertex;
		for(int i=0; i<(this.lstVertexGraph.size()-1); i++) {
			int[] var_custo = array_custos.clone();
			for(int j=0; j<var_custo.length; j++) {
				if(var_custo[j] != Integer.MAX_VALUE) {
					Vertex vertex_recente = this.lstVertexGraph.get(j);
					int ind_recente = getIndexVertex(vertex_recente.getLabel());
					LinkedList<Vertex> relacionamentos = this.grafo.adjacentVertices(vertex_recente.getLabel());
					for(Vertex vertex_vizinho : relacionamentos) {
						Edge link_vertex = this.grafo.getEdge(vertex_recente.getLabel(), vertex_vizinho.getLabel());
						if (link_vertex != null) {
							int custo_relacionamento = var_custo[ind_recente]+link_vertex.getCusto();
							boolean menor_relacionamento = var_custo[getIndexVertex(vertex_vizinho.getLabel())] > custo_relacionamento;
							if(menor_relacionamento) {
								var_custo[getIndexVertex(vertex_vizinho.getLabel())] = custo_relacionamento;
								p[getIndexVertex(vertex_vizinho.getLabel())] = this.lstVertexGraph.get(ind_recente).getLabel();
								caminho[getIndexVertex(vertex_vizinho.getLabel())] = caminho[ind_recente] +';'+ vertex_vizinho.getLabel();
							}
						}
					}
				}
			}
			if(Arrays.equals(var_custo, array_custos)) {
				array_custos = var_custo;
				break;
			}
			else {
				array_custos = var_custo;
			}
		}
		return array_custos;
	}
}
