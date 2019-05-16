/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import java.util.LinkedList;

/**
 *
 * @author Salzman
 */
public class TADGrafoDV2 {
    private int [][] mat = null;
    private String nome;
    private int quantVertx = 0;
    private int quantEdges = 0;
    private int geraIDedge = 1;
    private int primeiroVertex = 0;
    private int ultimoVertex = 0;
    private LinkedList lstDeletados = null;
    
    public TADGrafoDV2(String nome){
        mat = new int[10][10];
        this.nome = nome;
        
        lstDeletados = new LinkedList();
    }
    
    public void printmat(){
        for(int i=primeiroVertex; i<ultimoVertex; i++){
            if(lstDeletados.contains(i)){
                for(int k=0;k<mat[0].length; k++){
                    System.out.println(String.format("%d",mat[i][k]));
                }
            System.out.println();    
            }
        }
    }
    
    public int numVertices(){
        return quantVertx;
    }
    
    public int numEdges(){
        return quantEdges;
    }
    
    public boolean valido(int v){
        return((v >= primeiroVertex) && (v<=ultimoVertex) && !(lstDeletados.contains(v)));
    }
    
    public Integer getEdge(int u, int v){
        if(valido(u) && valido(v)){
            return mat[u][v];
        }
        else{
            return null;
        }
    }
    
    public int[] endVertices(int e){
        for(int i =primeiroVertex;i<=ultimoVertex;i++){
            if(valido(i)){
                for(int k=primeiroVertex;k<=ultimoVertex;k++){
                    if(mat[i][k] == e){
                        int[] v = new int[2];
                        v[0] = i;
                        v[1] = k;
                        return v;
                    }
                }
            }
        }
        
        return null;
    }
    
    
}
