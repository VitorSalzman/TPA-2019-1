/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversor;

import dicionario.TADDicChain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Object;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Salzman
 */


public class Conversor {
    
    class arrayAtoresF{
        private String nome;
        
        private ArrayList list;
        //aplicar geraID
        private arrayAtoresF(String nome){
            this.nome=nome;
            list = new ArrayList();
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public ArrayList getList() {
            return list;
        }

        public void setList(ArrayList list) {
            this.list = list;
        }
        
        
        
        
    }
    
    public  TADDicChain dicElements = new TADDicChain();
    public TADDicChain dicRelationships = new TADDicChain();
    private arrayAtoresF IDAtoresF;
    private int geraID=0;
    public Conversor(String caminho_arq, String nome_arq) throws IOException{
       
        converte(caminho_arq+nome_arq);
    }
    
    public int geraIDVertex(){
        int id = geraID++;
        return id;
    }
    
    public LinkedList filmesEAtores(){
        return dicElements.keys();
    }
    
    public LinkedList relacionamentos(){
        return dicRelationships.elements();
    }
    
    private void write() throws IOException{
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("saidafinal.tgf", true));
        String str;
        LinkedList<Object> keys = new LinkedList<Object>();
        
        keys = dicElements.keys();
        
        for (Object object : keys) {
            str = dicElements.findElement(object).toString()+" "+ object+"\n";
            bufferWriter.write(str);
        }
        
        bufferWriter.write("# \n");
        
        keys = dicRelationships.keys();
        
        for (Object object : keys) {
            ArrayList<Integer> lista = (ArrayList<Integer>) dicRelationships.findElement(object);
            
            for (Integer cell : lista) {
                str = dicElements.findElement(object).toString()+" "+cell.toString()+"\n";
                bufferWriter.write(str);
            }

            
        }
        
        bufferWriter.close();

    }
    public void converte(String nomearq) throws FileNotFoundException, IOException{
        File arq = new File (nomearq);
        Scanner s = new Scanner(arq);
        String line;
        IDAtoresF = new arrayAtoresF(null);
        
        
        while(s.hasNextLine()) {  
            line = s.nextLine(); 
            String[] vet = line.split("/");    //Separação de valores
            
             
            for (int i = 0; i < vet.length; i++){ 
               // System.out.println(vet[i]);
                Integer intAux = (Integer) dicElements.findElement(vet[i]);
                if(dicElements.NO_SUCH_KEY()){
                    dicElements.insertItem(vet[i], geraIDVertex()); // Insere item de chave filme/ator e valor id;
                }
                if (!vet[i].isEmpty()){
                    if(i!=0){  // Caso seja ator
                        IDAtoresF.getList().add(dicElements.findElement(vet[i])); //adiciona o ator encontrado à lista do filme vinculado a este.
                    }
                    else{ //Caso seja filme 
                        
                        if(!IDAtoresF.getList().isEmpty()){
                            dicRelationships.insertItem(IDAtoresF.getNome(), IDAtoresF.getList()); 
                            IDAtoresF = new arrayAtoresF(null);
                        }    
                        IDAtoresF.setNome(vet[i]); //Renomeia a lista de relacionamentos para o novo filme encontrado;
                                
            
                    }    
                         // Adiciona o relacionamento filme_atores no dicionario de relacionamentos
                       
                }
                else{
                    System.out.println("Vazio");
                }
            }            
        }
        dicRelationships.insertItem(IDAtoresF.getNome(), IDAtoresF.getList());  
       write();
    
    }
        
}
