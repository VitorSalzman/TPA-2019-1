/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv3;

/**
 *
 * @author 20161bsi0403
 */
public class Vertex {
    private String label;
    private int id;
    private Object dado;
    
    public Vertex(String label, Object dado){
        this.label = label;
        this.dado = dado;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }
    
    
}
