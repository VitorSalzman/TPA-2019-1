/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.caminhamento;

import ifes.bsi.tpa.grafo.Vertex;
import java.util.LinkedList;

/**
 *
 * @author Salzman
 */
public class DSDijkstra extends DataSet{
    private int[] vet_custos;
    private String[] vet_ant;

    public DSDijkstra(int[] vet_custos, String[] vet_ant) {
        this.vet_ant = vet_ant;
        this.vet_custos = vet_custos;
    }

    @Override
    public LinkedList<Vertex> caminho(String origem, String destino){
        return null;

    }

    @Override
    public int custo (String origem, String destino){
        return 0;
    }

    public int[] getVet_custos(){
        return vet_custos;
    }

    public String[] getVet_antecessores(){
        return vet_ant;
    }
    
    


}