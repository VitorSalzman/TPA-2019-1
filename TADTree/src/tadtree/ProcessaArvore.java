/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadtree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serenna
 */
public class ProcessaArvore {
    private TreeTPAV1 tree = null;

    public ProcessaArvore(TreeTPAV1 t) {
        this.tree = t;
    }

    public boolean equals_tree(TreeTPAV1 otherTree) {
        return tree.toString().equals(otherTree.toString());
    }

    public TreeTPAV1 getT() {
        return tree;
    }

    public void setT(TreeTPAV1 t) {
        this.tree = t;
    }
    //Povoa arvore com todas possibilidades
    public TreeTPAV1<String> povoaVelha() {
        TreeTPAV1 newTree = new TreeTPAV1();  //nova arvore
        Node root = new Node(); //primeiro nó
        String iniTab = "---|---|---";
        root.setData(iniTab);     // conteúdo da raíz é o tabuleiro vazio
        newTree.setRootElement(root);   
        LinkedList<Node<String>> nao_visitados = new LinkedList<>();
        nao_visitados.add(root); 
        
        while(!nao_visitados.isEmpty()){
            Node current_root = nao_visitados.remove();   //retira e recebe o nó corrente
            for (int i=0; i<11; i++) {
                
                char[] tab = ((String)current_root.getData()).toCharArray(); 
                //Apenas espaços vazios são usados para o X ou O
                if(tab[i] == '-'){
                    tab[i] = decideJogadas(tab);
                }
                
                else continue; 
                
                //Novo nó para guardar o tabuleiro gerado
                Node f = new Node(new String(tab));
                current_root.addChild(f);
                nao_visitados.add(f);
            }
        }
        this.tree = newTree; //aponta a árvore da classe pra essa toda povoada
        return newTree;
    }
    
    //Função para decidir próxima jogada
    private static char decideJogadas(char[] board) {
        int pX = 0; //X player
        int pO = 0; //O player
        
        for(int i=0; i<board.length; i++) {
            if(board[i] == 'X') 
                pX++;
            else if(board[i] == 'O') 
                pO++;
        }
        if(pX>pO){
            return 'O';
        }
        else{
            return 'X';
        }
        
    }
    
    //salva o conteúdo da árvore em um rquivo texto de nome nome_arq
    public String salva_tree(String nome_arq) {
        String output = "";
        BufferedWriter bfw;
        try {
            bfw = new BufferedWriter(new FileWriter(nome_arq, true));
            Node root = this.tree.getRootElement();    
            
            output += "(";
            if(root.getNumberOfChildren() != 0){
                for(int i=0;i<root.getChildren().size();i++){
                    output += salvaNodes((Node)root.getChildren().get(i));
                }
            }
            
            output = output.substring(0,output.length()-1);
            bfw.write(output+")");
            bfw.write((String) root.getData()+";");
            bfw.close();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return nome_arq;
    }
    
    //Método recursivo que percorre os filhos dos nós da árvore
    public String salvaNodes(Node root){
        String output ="";
        if (root.getNumberOfChildren() != 0) {
            output += "(";
            for (int i=0; i<root.getNumberOfChildren(); i++) {
                output += salvaNodes((Node) root.getChildren().get(i));
            }
            Character c = output.charAt(output.length() - 1);
            if (c == ',') output = output.substring(0, output.length() - 1);
            
            output += ")";
            output += (String) root.getData();
            output += ",";
        } else {
            output += (String) root.getData();
            output += ",";
        }
         return output;
    }
    
    // carrega um arquivo de nome nome_arq
    public TreeTPAV1<String> carrega_tree(String nome_arq) throws FileNotFoundException, IOException {
       
            BufferedReader brr = new BufferedReader(new FileReader(nome_arq));
            String s = brr.readLine();
            s = s.substring(0, s.length() - 1);
            this.tree = new TreeTPAV1();
            
            Node node = new Node();
            this.tree.setRootElement(node);
            this.tree.setRootElement(NodeTree(s));
       
        return tree;
    }
    //Recursivo que percorre e carrega os nós da árvore
    private Node NodeTree(String s) {
        int Esq = s.indexOf('('); //Verifica se há uma dupla de parenteses ()
        int Dir = s.lastIndexOf(')'); //Verifica se há uma dupla de parenteses ()
        
        if (Esq != -1 && Dir != -1) {
            String tab = s.substring(Dir + 1); //Pega o tabuleiro escrito
            String[] tabChild = separaPal(s.substring(Esq + 1, Dir));

            Node node = new Node(tab);
            node.children = new LinkedList<>();
            for (String sub : tabChild) {
                Node child = NodeTree(sub);
                node.children.add(child);
            }
            this.tree.getRootElement().addChild(node);
            return node;
        } else if (Esq == Dir) {
            Node node = new Node(s);
            this.tree.getRootElement().addChild(node);
            return node;

        } else {
            //Numero errado de parenteses
            throw new RuntimeException("unbalanced ()'s");
        }
    }
    //Método auxiliar para separar as palavras
    private String[] separaPal(String s) {
        LinkedList<Integer> splitIndices = new LinkedList<>();
        int nSplit;
        String[] splits;
        int quant_par_dir = 0;
        int quant_par_esq = 0;
      
        for (int i=0; i <s.length(); i++){
            if(s.charAt(i) == '(' ){
                quant_par_esq++;
            }else if (s.charAt(i) == ')'){
                quant_par_dir++;
            }else if (s.charAt(i) == ',' && quant_par_esq == quant_par_dir){ 
                splitIndices.add(i);
            }
        }

        nSplit = splitIndices.size() + 1; //pegar a ultima posição
        splits = new String[nSplit];

        if (nSplit==1){ 
            splits[0] = s;
        } else {
            splits[0] = s.substring(0, splitIndices.get(0));
            
            for (int i = 1; i < splitIndices.size(); i++) {
                splits[i] = s.substring(splitIndices.get(i - 1) + 1, splitIndices.get(i));
            }
            splits[nSplit - 1] = s.substring(splitIndices.get(splitIndices.size()-1) +1);
        }
        return splits;
    }

}
