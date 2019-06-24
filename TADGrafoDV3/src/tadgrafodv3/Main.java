/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import Conversor.Conversor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Salzman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
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
        */
        
        String s = "tes1te";
        String t = "teste1";
        
        String nome_arq = "movies.txt";
        String caminho_arq = "C:\\Users\\vitorsalzman\\Documents\\GitHub\\TPA-2019-1\\TADGrafoDV3\\src\\Conversor\\";
        LinkedList filmesatores = new LinkedList();
        LinkedList relacionamentos = new LinkedList();
        Conversor conv = new Conversor(caminho_arq+nome_arq);
        conv.converte(caminho_arq+nome_arq);
        
        filmesatores=conv.filmesEAtores();
        for(int i=0; i<filmesatores.size();i++){
            System.out.println("Key "+i+": "+filmesatores.get(i));
            
        }
        
        relacionamentos=conv.relacionamentos();
        for(int i=0; i<relacionamentos.size();i++){
            System.out.println("Relação "+i+": "+relacionamentos.get(i));
            
        }
        
        
        
        
    }
    
}
