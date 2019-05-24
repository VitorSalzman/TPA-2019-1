/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafodv2;

import java.util.LinkedList;
import dicionario.TADDicChain;
import java.util.ArrayList;
import java.util.Collections;
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
    private int geraIDvertex = 1;
    private int primeiroVertex = 0;
    private int ultimoVertex = 0;
    private LinkedList lstDeletados = null;
    TADDicChain dicLblVertex = new TADDicChain();
    TADDicChain dicLblEdge = new TADDicChain();
    
    public TADGrafoDV2(String nome){
        mat = new int[16][16];
        this.nome = nome;
        
        lstDeletados = new LinkedList();
    }
    
    public void printmat(){
        for(int i=primeiroVertex; i<ultimoVertex; i++){
            if(!lstDeletados.contains(i)){
                for(int k=0;k<mat[0].length; k++){
                    System.out.println(String.format("%d",mat[i][k]));
                }
            System.out.println();    
            }
        }
    }
    
     public void printgrafo() {
        ArrayList<String> al = new ArrayList<String>();
        String s;
        String lblOrigem="";
        String lblDestino = ""; 
        String lblEdge = "";
        
        Vertex v;
        Edge e;
        
        LinkedList<Object> lstV = dicLblVertex.keys();
        LinkedList<Object> lstE = dicLblEdge.keys();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            s = "";
            if(!lstDeletados.contains(i)){
                for(int j=0; j<lstV.size(); j++){
                    v = (Vertex)dicLblVertex.findElement(lstV.get(j));
                    if(v.getId()==i){
                        lblOrigem = v.getLabel();
                        break;
                    }
                }
                
                for(int k=primeiroVertex; k<=ultimoVertex; k++){
                    if(!lstDeletados.contains(k)){
                        for(int m=0; m<lstV.size(); m++){
                            v = (Vertex)dicLblVertex.findElement(lstV.get(m));
                            if(v.getId()==k){
                                lblDestino = v.getLabel();
                                break;
                            }
                        }
                        
                        int idEdge = mat[i][k];
                        
                        if(idEdge != 0){
                            for(int m=0; m<lstE.size(); m++){
                                e = (Edge)dicLblEdge.findElement(lstE.get(m));
                                if(e.getId()==idEdge){
                                    lblDestino = e.getLabel();
                                    break;
                                }
                            }
                            
                            s = lblOrigem + "--" + lblEdge + "-->" + lblDestino;
                            al.add(s);
                        }
                    }
                }
            }
        }
        
        
        for(int i=0; i<lstV.size(); i++){
            String lbl = (String)lstV.get(i);
            if(Degree(lbl) == 0){
                al.add(lbl);
            }
        }
        
        Collections.sort(al);
        
        for(int n = 0; n < al.size(); n ++) {
            System.out.println(al.get(n));
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
    
    
    public Edge getEdge(String origem, String destino){
        Vertex vD = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        Vertex vO = (Vertex)dicLblVertex.findElement(origem);
        
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        int idEdge = mat[vO.getId()][vD.getId()];
        if(idEdge == 0){
            return null;
        }
        else{
            LinkedList<Object> lstKeysE = dicLblEdge.keys();
            
            for(int i=0; i<lstKeysE.size(); i++){
                Edge e = (Edge)dicLblEdge.findElement(lstKeysE.get(i));
                if(vO.getId() == idEdge){
                    return e;
                }
            }
        }
        
        return null;
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
    
    public Vertex opposite(int v, int e){
        Vertex oV = (Vertex)dicLblVertex.findElement(v);
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        Vertex oE = (Vertex)dicLblVertex.findElement(e);
        
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
         for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if((!lstDeletados.contains(i)) && (mat[oV.getId()][i] == oE.getId())){
                LinkedList<Object> lstV = dicLblVertex.keys();
                
                for(int m=0; m<lstV.size(); m++){
                    Vertex oU = (Vertex)dicLblVertex.findElement(lstV.get(m));
                    if(oU.getId() == i){
                        return oU;
                    }
                }
            }
        }
        
        return null;
    }
    
    public Integer inDegree(String l){
        Vertex v = (Vertex)dicLblVertex.findElement(l);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else{
            int linha = v.getId();
            int grau=0;
            for(int i=primeiroVertex; i<=ultimoVertex;i++){
                if(mat[i][linha] != 0 && valido(i)){
                    grau++;
                }
            }
            return grau;
        }
    }
    public Integer outDegree(String l){
        Vertex v = (Vertex)dicLblVertex.findElement(l);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else{
            int grau = 0;
            int linha = v.getId();
            for(int i=primeiroVertex; i<=ultimoVertex; i++){
                if(mat[linha][i] != 0 && valido(i)){
                    grau++;
                }
            }
            return grau;
        }
    }
    
    public Integer Degree(String l) {
        Integer in = inDegree(l);
        Integer out = outDegree(l);
        
        if((in == null) || (out == null)) {
            return null;
        }
        else {
            return in + out;
        }
    }
    
    public Vertex insereVertex(String label, Object o){
        int idV = geraIDVertex();
        
        if(idV > ultimoVertex){
            ultimoVertex = idV;
        }
        if(idV < primeiroVertex){
            primeiroVertex = idV;
        }
        
        
        Vertex V = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()){
            V = new Vertex(label, o);
            V.setId(idV);
            dicLblVertex.insertItem(label,V);
            quantVertx++;
        }
        else{
            V.setDado(o);
        }
        
        return V;
    }
    
    public Edge insereEdge(String  origem, String destino, String l, Object o){
        Vertex vO = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vD = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Edge e = (Edge)dicLblEdge.findElement(l);
        
        if(dicLblEdge.NO_SUCH_KEY()){
            e = new Edge(l, o);
            e.setId(geraIDVertex());
            
            dicLblEdge.insertItem(l, e);
            
            mat[vO.getId()][vD.getId()] = e.getId();
            quantEdges++;
        }
        else{
            e.setDado(o);
        }
        return e;
    }
    
        
 
    
    public Object removeEdge(String edge){
        Edge e  = (Edge)dicLblEdge.findElement(edge);
        if(dicLblEdge.NO_SUCH_KEY()) {
            return null;
        }
        
        int idEdge = e.getId();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if(!lstDeletados.contains(i)){
                for(int k=primeiroVertex; k<=ultimoVertex; k++){
                    if(mat[i][k] == idEdge){
                        mat[i][k] = 0;
                        quantEdges--;
                        dicLblEdge.removeElement(edge);
                        return e.getDado();
                    }
                }
            }
        }
        return null;
    }
    
    public Object removeVertex(String l){
        Vertex v = (Vertex)dicLblVertex.findElement(l);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        int idV = v.getId();
        
        
            
        if(idV == primeiroVertex){
            for(int i=primeiroVertex+1; i<=ultimoVertex;i++){
                if(!lstDeletados.contains(i)){
                    primeiroVertex = i;
                    break;
                }

            }
        }
            
        if(idV == ultimoVertex){
            for(int i=ultimoVertex-1; i>=primeiroVertex; i--){
                if(!lstDeletados.contains(i)){
                    ultimoVertex = i;
                    break;
                }
            }
        }
            
        for(int i=primeiroVertex+1; i<=ultimoVertex; i++){
            if(mat[idV][i] != 0){
                mat[idV][i]=0;
                quantEdges--;
            }
                
            if(((mat[i][idV] != 0)) && ((mat[idV][i] != mat[i][idV]))){
                mat[i][idV]=0;
                quantEdges--;

            }
             
                
        }
            
            
            
        quantVertx--;
        lstDeletados.add(idV);
        return dicLblVertex.removeElement(l);    
    }
    
    public boolean areaAdjacent(int origem, int destino){
        Vertex vO = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return false;
        }
        
        Vertex vD = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return false;
        }
        return mat[vO.getId()][vD.getId()] != 0;
        
    }
    
    private int geraIDVertex() {
        int id;
        
        if(lstDeletados.size() == 0) {
            id = geraIDvertex++;
        }
        else {
            id = (int) lstDeletados.get(0);
            lstDeletados.remove();
        }
        
        if(id < primeiroVertex)
            primeiroVertex = id;
        
        if(id > ultimoVertex)
            ultimoVertex = id;
        
        return id;
    }


    
    
           
}


///TO DO 
///public vertez insertVertex(String label, Object o)
///public Edge insertEdge(String vu, String vv, String label, Object o)