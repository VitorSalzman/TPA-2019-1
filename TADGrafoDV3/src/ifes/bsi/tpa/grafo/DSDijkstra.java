/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

import java.util.LinkedList;
import tadgrafodv3.Vertex;

/**
 *
 * @author Salzman
 */
public class DSDijkstra extends DataSet{
    private int[] vet_custos;
    private String[] vet_antecessores;

    public DSDijkstra(int[] custos, String[] p) {
        this.vet_antecessores = vet_antecessores;
        this.vet_custos = vet_custos;
    }

    @Override
    public int custo(String origem, String destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<ifes.bsi.tpa.grafo.Vertex> caminho(String origem, String destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int[] getCustos() {
        return vet_custos;
    }

    public String[] getPredecessores() {
        return vet_antecessores;
    }
   
}

