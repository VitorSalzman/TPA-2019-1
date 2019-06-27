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
public class ProcessaGrafo {
    private TADGrafoDV3 grafo;
    private LinkedList<Vertex> lstVertexGraph;
    private LinkedList<Edge> lstEdgeGraph;
    
    public ProcessaGrafo(TADGrafoDV3 grafo) {
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
	/*
	private int[][] getBaseCost(){
		// the adjacencyMatrix is the size of the number of vertices
		int[][] adjacencyMatrixBase = new int[this.verticesGrafo.length][this.verticesGrafo.length];
		int lineIndex = -1;
		//is vertice out
		for (Vertice verticeOut : this.verticesGrafo) {
			lineIndex++;
			int columnIndex = -1;
			//is vertice In
			for(Vertice verticeIn : this.verticesGrafo) {
				columnIndex++;
				if(verticeOut.getIndexMatrix() != verticeIn.getIndexMatrix()) {
					Edge currentEdge = this.grafo.getEdge(verticeOut.getLabel(),verticeIn.getLabel());
					if(currentEdge != null) {
						adjacencyMatrixBase[lineIndex][columnIndex] = currentEdge.getCost();
					}
					else {
						// for int values the equivalent to infinity is the MAX_VALUE, infinity value is only avalibe in double/float values
						adjacencyMatrixBase[lineIndex][columnIndex] = Integer.MAX_VALUE;
					}
				}
				else {
					adjacencyMatrixBase[lineIndex][columnIndex] =  0;
				}
			}
		}
		return adjacencyMatrixBase;
	}
	
	public int[][] floydWarshallCost() {
		// represent the matrix used to build the new one, the focusMatrix 
		int[][] passedMatrix = this.getBaseCost();
		// we start in focus at the first vertice avalibe from the list and then we update the values until to the end
		int focusVertice = 0;
		// represent the newest cost matrix
		int[][] focusMatrix = null;
		while(focusVertice < this.verticesGrafo.length){
			focusMatrix = this.makeLessValue(passedMatrix,focusVertice);
			passedMatrix = focusMatrix;
			focusVertice++;
		}
		return focusMatrix;
	}
    
    private int[][] makeLessValue(int[][]martixPass,int focusIndex){
		int[][] newValues = new int[this.verticesGrafo.length][this.verticesGrafo.length];
		for(int i= 0; i < this.verticesGrafo.length; i++) {
			//case the interator is the same value of the focus we just need to change 1 parameter
			if(focusIndex != i) {
				newValues[focusIndex][i] = martixPass[focusIndex][i];
				newValues[i][focusIndex] = martixPass[i][focusIndex];
			}
			else {
				newValues[focusIndex][i] = martixPass[focusIndex][i];
			}
		}
		// walking through the old matrix
		for(int l = 0; l < this.verticesGrafo.length; l++) {
			// when is the line of the focus index we just pass by
			if(l != focusIndex) {
				for(int c = 0; c < this.verticesGrafo.length; c++) {
					// geting the cost of the 2 possibilitys
					int onePass = martixPass[l][c];
					int throughStepOne = martixPass[l][focusIndex];
					int throughStepTwo = martixPass[focusIndex][c];
					
					// when we sum the max value with another value the data became negative
					if( (throughStepOne == Integer.MAX_VALUE || throughStepTwo == Integer.MAX_VALUE) && onePass <= Integer.MAX_VALUE) {
						newValues[l][c] = onePass;
					}
					else if(onePass < ( throughStepOne + throughStepTwo )) {
						newValues[l][c] = onePass;
					}
					else {
						newValues[l][c] = throughStepOne + throughStepTwo;
					}
					
				}
			}
		}
		return newValues;
	}

	private int findIndexVerticeList(String labelVertice) {
		for (int i = 0; i < this.verticesGrafo.length; i++) {
			if(this.verticesGrafo[i].getLabel().equals(labelVertice)) {
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
	
	public int[] dijkstraCost(String beginVertice) {
		// an arry with the cost and the vertice index ref.
		int[]distance = new int[this.verticesGrafo.length];
		String[] path = new String[distance.length];
		String[] predecessor = new String[distance.length];
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			unvisited.add(i);
		}
		distance[findIndexVerticeList(beginVertice)] = 0;
		int currentIndex = findIndexVerticeList(beginVertice);
		path[currentIndex] = beginVertice;
		while(unvisited.size() > 0) {
			unvisited.remove((Integer)currentIndex);
			int[] distanceCurrent = distance.clone();
			String[] pathCurrent = path.clone();
			LinkedList<Vertice> connectionVertices = this.grafo.adjacenteVertices(this.verticesGrafo[currentIndex].getLabel());			
			for (Vertice vertice : connectionVertices) {
				boolean isEdgeNotNull = this.grafo.getEdge(this.verticesGrafo[currentIndex].getLabel(), vertice.getLabel()) != null;
				boolean isUnvisited = unvisited.contains((Integer)findIndexVerticeList(vertice.getLabel()));
				if(isEdgeNotNull && isUnvisited) {
					int costFromcurrent = this.grafo.getEdge(this.verticesGrafo[currentIndex].getLabel(), vertice.getLabel()).getCost() + distance[currentIndex];
					int currentCost = distance[findIndexVerticeList(vertice.getLabel())];
					if(currentCost > costFromcurrent) {
						distanceCurrent[findIndexVerticeList(vertice.getLabel())] = costFromcurrent;
						predecessor[findIndexVerticeList(vertice.getLabel())] = this.verticesGrafo[currentIndex].getLabel();
						pathCurrent[findIndexVerticeList(vertice.getLabel())] = path[currentIndex] +'-'+ vertice.getLabel();
						 
					}
				}
			}
			distance = distanceCurrent;
			path = pathCurrent;
			if(unvisited.size() > 0) {
				currentIndex = getIndexLessCost(distance,unvisited);
			}
		}
		return distance;
		
	}

	public int[] bellmanFord(String beginVertice) {
		int[] costList = new int[this.verticesGrafo.length];
		String[] predecessor = new String[costList.length];
		String[] path = new String[costList.length];
		for(int i = 0; i < costList.length; i++) {
			costList[i] = Integer.MAX_VALUE;
		}
		int indexSource = findIndexVerticeList(beginVertice);
		costList[indexSource] = 0;
		path[indexSource] = beginVertice;
		for(int i = 0; i < (this.verticesGrafo.length - 1); i++) {
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
	}*/
}
