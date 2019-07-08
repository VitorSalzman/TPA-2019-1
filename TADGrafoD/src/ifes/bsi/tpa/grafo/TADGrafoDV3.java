/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

import dicionario.TADDicChain;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Salzman
 */
public class TADGrafoDV3{
    private int [][] mat = null;
    private String nome;
    private int quantVertex = 0;
    private int quantEdges = 0;
    private int geraIDedge = 1;
    private int geraIDvertex = 0;
    private TADDicChain dicLblVertex = new TADDicChain(null);
    private TADDicChain dicLblEdge = new TADDicChain(null);
    private int primeiroVertex = 0;
    private int ultimoVertex = 0;
    private LinkedList<Integer> lstEliminados = new LinkedList<Integer>();
    
 
    public TADGrafoDV3(String nome){
        mat = new int[9000][9000]; //tamanho padrão
        this.nome = nome;
    }
    
    
    public TADGrafoDV3(String nome, int totalNos){
        mat = new int[totalNos][totalNos];
        this.nome = nome;
    }

    
    //Imprime a matriz de adjacencia
    public void printmat(){
        for(int i = primeiroVertex; i < ultimoVertex+1; i++) {
            if(!lstEliminados.contains(i)) {
                for(int k = primeiroVertex; k <= ultimoVertex; k++) {
                    if(!lstEliminados.contains(i)) {
                        System.out.print(String.format("%d ", mat[i][k]));
                    }
                }
                
            System.out.println(); //Organização   
            }
        }
    }
    
    //Imprime o grafo em formato texto
    public void printgrafo() {
        
        if(numVertices() > 1) {
            ArrayList<String> al = new ArrayList<String>();
            String s, labelOrigem = "", labelDestino = "", labelEdge = "";

            Vertex v;
            Edge e;

            LinkedList<Object> lstVs = dicLblVertex.keys();
            LinkedList<Object> lstEs = dicLblEdge.keys();

            for(int i=primeiroVertex; i<=ultimoVertex; i++){
                s = "";

                if(!lstEliminados.contains(i)){
                    for(int j=0; j<lstVs.size(); j++){
                        v = (Vertex)dicLblVertex.findElement(lstVs.get(j));
                        
                        if(v.getId()==i){
                            labelOrigem = v.getLabel();
                            break;
                        }
                    }

                    for(int k=primeiroVertex; k<=ultimoVertex;k++){
                        if(!lstEliminados.contains(k)){
                            for(int m=0; m<lstVs.size(); m++){
                                v=(Vertex)dicLblVertex.findElement(lstVs.get(m));
                                
                                if(v.getId()==k){
                                    labelDestino = v.getLabel();
                                    break;
                                }
                            }

                            int idEdge=mat[i][k];

                            if(idEdge!=0){
                                for(int m=0; m<lstEs.size(); m++){
                                    e = (Edge)dicLblEdge.findElement(lstEs.get(m));
                                    
                                    if(e.getId()==idEdge){
                                        labelEdge = e.getLabel();
                                        break;
                                    }
                                }

                                s = labelOrigem + "--" + labelEdge + "-->" + labelDestino;
                                al.add(s);
                            }
                        }
                    }
                }
            } 

            
            for(int i=0; i<lstVs.size(); i++){
                String lbl = (String)lstVs.get(i);
                if(degree(lbl)==0){
                    al.add(lbl);
                }
            }
        
            Collections.sort(al);

            for(int n=0; n<al.size(); n++){
                System.out.println(al.get(n));
            }
        }
        else {
            System.out.println("Numero de vertices insuficiente");
        }
    }
    
    //Lista com todos os vértices do grafo
    public LinkedList<Vertex> vertices() {
        LinkedList<Vertex> vx = new LinkedList();
        LinkedList<Object> allvertex = (LinkedList<Object>) dicLblVertex.elements();
        
        for (int i = 0; i < allvertex.size(); i++) {
            vx.add((Vertex) allvertex.get(i));
        }
        return vx;
    }
    
    //reetorna lista de edges
    public LinkedList<Edge> edges() {
        LinkedList<Edge> e = new LinkedList<Edge>();
        int pos = 0;
        
        LinkedList<Object> objE = dicLblEdge.elements();
        
        for (Object object : objE){
            e.add((Edge)object);
        }
        
        return e;
    }
    
    //retorna numero de vertices
    public int numVertices(){
        return this.quantVertex;
    }
    
    //retorna numero de edges
    public int numEdges(){
        return this.quantEdges;
    }
    
    //retorna nome do grafo
    public String getNome(){
        return nome;
    }
    
    //define nome do grafo
    public void setNome(String nome){
        this.nome = nome;
    }
    
    //retorna verdadeiro caso o vertice esteja entre o primeiro e o ultimo vertice, além de não estar dentro da lista de eliminados
    public boolean valido(int v){
        return((v >= primeiroVertex) && (v<=ultimoVertex) && !(lstEliminados.contains(v)));
    }
    
    //retorna o edge através de um label
    public Edge getEdge(String label){
        Edge e = (Edge)dicLblEdge.findElement(label);
        
        if(dicLblEdge.NO_SUCH_KEY()){
            return null;
        }
        return e;
    }
    
    //retorna o edge que encontra-se entre os vertices origem e destino
    public Edge getEdge(String origem, String destino) {
        Vertex vD = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vO = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        int idEdge = this.mat[vO.getId()][vD.getId()];
        
        if(idEdge==0){
            return null;
        }
        else {
            Edge e = this.intToEdge(idEdge);
            return e;
        }
    }
    
    //retorna o vertice através do label como parâmetro
    public Vertex getVertex(String label){
        Vertex vertice = (Vertex)this.dicLblVertex.findElement(label);
        
        if(dicLblVertex.NO_SUCH_KEY())return null;
        else return vertice;
        
    }
    
    //transforma um inteiro em um vertice
    public Vertex intToVertex(int id){
        LinkedList<Object> lst = dicLblVertex.elements();
        
        for(int i=0; i<lst.size(); i++){
            Vertex v = (Vertex)lst.get(i);
            if(id == v.getId()){
                return v;
            }
        }
        return null;
    }
    
    //transforma um inteiro em um edge
    public Edge intToEdge(int id){
        LinkedList<Object> lst = dicLblEdge.elements();
        
        for(int i=0; i<lst.size(); i++){
            Edge e = (Edge)lst.get(i);
            if(id == e.getId()){
                return e;
            }
        }
        return null;
    }
    
    //Retorna vertices terminais de um edge de labelE.(Nulo caso não haja nenhum vertice
    public Vertex[] endVertices(String labelE){ 
        Edge oE = (Edge)dicLblEdge.findElement(labelE);
        
        if(dicLblEdge.NO_SUCH_KEY()){
            return null;
        }
        
        int idE = oE.getId();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if(!(lstEliminados.contains(i))){
                for(int j=primeiroVertex; j<=ultimoVertex; j++){
                    if(mat[i][j] == idE){
                        Vertex[] v = new Vertex[2];
                        v[0] = intToVertex(i);
                        v[1] = intToVertex(j);
                        return v; 
                    }
                }
            }
        }
        return null;
    }
    
    //retorna o vertice correspondente ao conjunto "vertice Origem, edge intermediário, vertice destino"
    public Vertex opposite(String v, String e){
        Vertex objV = (Vertex)dicLblVertex.findElement(v);
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        Edge objE = (Edge)dicLblEdge.findElement(e);
        if(dicLblEdge.NO_SUCH_KEY()){
            return null;
        }
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if((!lstEliminados.contains(i)) && (mat[objV.getId()][i] == objE.getId())){
                LinkedList<Object> lstVs = dicLblVertex.keys();
                
                for(int m=0; m<lstVs.size(); m++){
                    Vertex oU = (Vertex)dicLblVertex.findElement(lstVs.get(m));
                    if(oU.getId() == i) {
                        return oU;
                    }
                }
            }
        }
        
        return null;
    }
    
    

    //retorna a quantidade de arestas que saem de um vertice de label "label"
    public Integer outDegree(String label){
        // v, linha, i, coluna: todos as arestas saindo de v.
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int line = v.getId();
            int grade = 0;
            
            for(int i=primeiroVertex; i<=ultimoVertex; i++){
                if((mat[line][i]!=0) && !lstEliminados.contains(i)){
                    grade++;
                }
            }    
            return grade;
        }
            
    }

    //retorna a quantidade de arestas que chegam no vertice de label "label"
    public Integer inDegree(String label){

        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int column = v.getId();
            int grade = 0;
            
            for(int i=primeiroVertex; i<=ultimoVertex; i++){
                if((mat[i][column] != 0) && !lstEliminados.contains(i)){
                    grade++;
                }
            }
            
            return grade;
        }
    }
    
    //Insere edge no grafo. Sobrescreve caso ja exista.
    public Edge insertEdge(String origem, String destino, String label, Object obj) {
        
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        
        Edge edgeToInsert = (Edge)dicLblEdge.findElement(label);
        

        if(dicLblEdge.NO_SUCH_KEY()) {
            edgeToInsert = new Edge(label, obj);
            edgeToInsert.setId(geraIDedge++);
            
            dicLblEdge.insertItem(label, edgeToInsert);
            
            mat[vOrigem.getId()][vDestino.getId()] = edgeToInsert.getId();
            quantEdges++;
        } 
        else {
            edgeToInsert.setDado(obj);
        }
        
        return edgeToInsert; 
    }
    
    
    //remove edge de grafo. Retorna nulo caso não exista
    public Object removeEdge(String edge){
        Edge e  = (Edge)dicLblEdge.findElement(edge);
        if(dicLblEdge.NO_SUCH_KEY()) {
            return null;
        }
        
        int idE = e.getId();
        
        for(int i = primeiroVertex; i <= ultimoVertex; i++) {
            if(!lstEliminados.contains(i)) {
                for(int j = primeiroVertex; j <= ultimoVertex; j++) {
                    if(mat[i][j] == idE) {
                        mat[i][j] = 0;
                        quantEdges--;
                        dicLblEdge.removeElement(edge);
                        return e.getDado();
                    } 
                } 
            }      
        }

        return null;
    }
    
    //remove vertice de grafo. Retorna nulo caso não exista.
    public Object removeVertex(String label) {
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        int idV = v.getId();

        if(idV == primeiroVertex){
            for(int i=primeiroVertex+1; i<=ultimoVertex; i++){
                if(!lstEliminados.contains(i)){
                    primeiroVertex = i;
                    break;
                }
            }
        }


        if(idV == ultimoVertex){
            for(int i=ultimoVertex-1; i<=primeiroVertex; i--){
                if(!lstEliminados.contains(i)){
                    ultimoVertex = i;
                    break;
                }
            }
        }
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if(mat[idV][i] != 0){
                mat[idV][i] = 0;
                quantEdges--;
            }
            
            
            if((mat[i][idV] != 0) && (mat[idV][i] != mat[i][idV])){
                quantEdges--;
                mat[i][idV] = 0;
                
            }
        }
        
        quantVertex--;
        lstEliminados.add(idV);
        return dicLblVertex.removeElement(label);   
    }
    
    //Verifica se dois vértices estão conectados por uma aresta.
    public boolean areAdjacent(String origem, String destino){
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return false;
        }
        
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()){
            return false;
        }
        
        return mat[vOrigem.getId()][vDestino.getId()] != 0;
    }
    
    private int geraIDVertex(){
        int id;
        
        if(lstEliminados.size()==0){
            id = geraIDvertex++;
        }
        else {
            id = lstEliminados.get(0);
            lstEliminados.remove();
        }
        
        
        if(id < primeiroVertex)
            primeiroVertex = id;
        
        if(id > ultimoVertex)
            ultimoVertex = id;
        
        return id;
    }
    
    //insere vertice em grafo. Sobrescreve caso já exista.
    public Vertex insertVertex(String label, Object dado){
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        
       
        if(dicLblVertex.NO_SUCH_KEY()){
            int idVertex = geraIDVertex();
            v = new Vertex(label, dado);
            v.setId(idVertex);
            dicLblVertex.insertItem(label, v);
            quantVertex++;
        }
        else {
            v.setDado(dado);
        }
        
        return v;
    }
    
   
    public Integer degree(String label){
        Integer in = inDegree(label);
        Integer out = outDegree(label);
        
        if((in == null) || (out == null)) {
            return null;
        }
        else {
            return in + out;
        }
    }
    
    // Descobre e retorna o objeto vértice onde entra a aresta de label.
    public Vertex destination(String labelE){ 
        Vertex[] vet = endVertices(labelE);
        
        if(vet != null) return vet[1];
        else return null;
    }
    
    
    public Vertex origin(String labelE){ 
        Vertex[] vet = endVertices(labelE);
        
        if(vet != null) return vet[0];
        else return null;
    }
    
    //Descobre e retorna todas as arestas que chegam ao vértice de label "labelV"
    public LinkedList<Edge> inIncidentEdges(String labelV){ 
        Vertex v = (Vertex)dicLblVertex.findElement(labelV);
        
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        LinkedList<Edge> lst = new LinkedList<Edge>();
        int id = v.getId();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if((!lstEliminados.contains(i)) && (mat[id][i] != 0)){
                lst.add(intToEdge(mat[id][i]));
            }
        }
        
        return lst;
    
    }
    
    //Descobre e retorna todas as arestas que saem do vértice de label
    public LinkedList<Edge> outIncidentEdges(String labelV){ 
        Vertex v = (Vertex)dicLblVertex.findElement(labelV);
        
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        LinkedList<Edge> lst = new LinkedList<Edge>();
        int id = v.getId();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if((!lstEliminados.contains(i)) && (mat[i][id] != 0)){
                lst.add(intToEdge(mat[i][id]));
            }
        }
        
        return lst;
    }
    
    //Descobre e retorna os vértices origem das arestas que entram no vértice de label  
    public LinkedList<Vertex> inAdjacenteVertices(String labelV){ 
        Vertex v = (Vertex)dicLblVertex.findElement(labelV);
        
        if(dicLblVertex.NO_SUCH_KEY()){
            return null;
        }
        
        LinkedList<Vertex> lstInVertex = new LinkedList<Vertex>();
        int id = v.getId();
        
        for(int i=primeiroVertex; i<=ultimoVertex; i++){
            if((!lstEliminados.contains(i))&&(mat[i][id] != 0)){
                lstInVertex.add(intToVertex(i));
            }
        }
        return lstInVertex;
    }
    
    //Descobre e retorna os vértices destino das arestas que saem no vértice de label "labelV"
    public LinkedList<Vertex> outAdjacenteVertices(String labelV){ 
        Vertex v = (Vertex)dicLblVertex.findElement(labelV);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        LinkedList<Vertex> lstOutVertex = new LinkedList<Vertex>();
        int id = v.getId();
        
        for(int i = primeiroVertex; i <= ultimoVertex; i++) {
            if((!lstEliminados.contains(i))&&(mat[id][i] != 0)){
                lstOutVertex.add(intToVertex(i));
            }
        }
        
        return lstOutVertex;
    
    }
    
    // Descobre e retorna todas as arestas conectadas ao vértice de label
    public LinkedList<Edge> incidentEdges(String labelV){ 
        LinkedList<Edge> lst = inIncidentEdges(labelV);
        lst.addAll(outIncidentEdges(labelV));
        
        return lst;
    }
    
    // Descobre e retorna os vértices adjacentes ao vértice de label
    public LinkedList<Vertex> adjacentVertices(String labelV){ 
        LinkedList<Vertex> lst = inAdjacenteVertices(labelV);
        lst.addAll(outAdjacenteVertices(labelV));
        
        return lst;
    }
    
    //retorna true caso os dois grafos sejam iguais. Falso, caso contrário 
    public boolean equals​(TADGrafoDV3 graph){
        if(this.numEdges() == graph.numEdges()&&this.numVertices() == graph.numVertices()){
            for(int i=primeiroVertex; i<=ultimoVertex; i++){
                for(int k=primeiroVertex; k<ultimoVertex; k++){
                    Vertex key = intToVertex(k);
                    Object outroV = graph.dicLblVertex.findElement(key.getLabel());
                    Edge e = intToEdge(k);
                    Object outroE = graph.dicLblEdge.findElement(e.getLabel());
                    
                    if(graph.dicLblVertex.NO_SUCH_KEY()){
                        return false;
                    }
                    if(graph.dicLblEdge.NO_SUCH_KEY()){
                        return false;
                    }
                    if(key.getDado() != outroV){
                        return false;
                    }
                    if(e.getDado() != outroE){
                        return false;
                    }
				 
                }
            }
	}
	
	return true;
    }
    
    // Clona o tad grafo corrente em um novo grafo com a mesma estrutura e mesmo conteúdo
    public TADGrafoDV3 clone(){
	TADGrafoDV3 grafoClone = new TADGrafoDV3(this.nome+", O clone", this.numVertices());
    
	for(int i=primeiroVertex; i<=ultimoVertex; i++){
	    for(int j=primeiroVertex; j<=ultimoVertex; j++){
                if(!lstEliminados.contains(j) && (mat[i][j] != 0)){
                    Vertex chave = intToVertex(j);
                    grafoClone.insertVertex(chave.getLabel(), chave.getDado());
                    
                    Edge e = intToEdge(mat[i][j]);
                    String origem = this.intToVertex(i).getLabel();
                    String destino = this.intToVertex(j).getLabel();
                    
                    grafoClone.insertEdge(origem, destino, e.getLabel(), e.getDado());
                }
	    }
        }
	
	return grafoClone;
    }
    
    /// Carrega um grafo através de um arquivo de formato TGF
    public static TADGrafoDV3 carregaTGF(String nome_arq_tgf, int quant_vertices) throws IOException{
        TADGrafoDV3 grafo = new TADGrafoDV3("grafo", quant_vertices);
        try {
            FileReader fileReader = new FileReader(nome_arq_tgf);
            BufferedReader arq = new BufferedReader(fileReader);
            String linha = arq.readLine();
            boolean arestas = false;
            while(linha != null){
                if(linha.contains("#")){
                    arestas = true;
                    linha = arq.readLine();
                }
                String[] l = linha.split(" ");
                if(!arestas){    
                    String value = "";
                    for(int i=1;i<l.length;i++){
                        value += " " + l[i];
                    }
                    grafo.insertVertex(l[0], value);
                }
                else{
                    String edgeLabel = l[0]+'-'+l[1];
                    grafo.insertEdge(l[0], l[1], edgeLabel, "");
                }
                linha = arq.readLine();
            }
            arq.close();
            return grafo;
            
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return null;
}
    
}
