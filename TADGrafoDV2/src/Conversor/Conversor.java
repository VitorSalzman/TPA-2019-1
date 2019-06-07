/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversor;

import dicionario.TADDicChain;
import java.io.File;
import java.io.FileNotFoundException;
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
    private String nome_arq;
    public  TADDicChain dicElements = new TADDicChain();
    public TADDicChain dicRelationships = new TADDicChain();
    private arrayAtoresF IDAtoresF;
    private int geraID=0;
    public Conversor(String nome_arq){
        this.nome_arq=nome_arq;
    }
    
    //retorna false caso a string possua algum caracter numérico.
    public boolean onlyChar(String s){
        String o = "a a (a) 2";
        /*if(o.matches("[A-Z a-z Çç]{"+o.length()+"}")){
            System.out.println("true");
            return true;
        }*/
        
        for (int i = 0; i < s.length(); i++) {
          if (Character.isDigit(s.charAt(i))==true)
          {
              //System.out.println("Possui numeros");
              return false;
          }
        }
        
        return true;
    }
    
    public int geraIDVertex(){
        int id = geraID++;
        return id;
    }
    
    public LinkedList filmesEAtores(){
        return dicElements.keys();
    }
    
    public LinkedList relacionamentos(){
        return dicRelationships.getElements();
    }
    public void converte(String nomearq) throws FileNotFoundException{
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
                    if(onlyChar(vet[i])){  // Caso seja ator
                        IDAtoresF.getList().add(dicElements.findElement(vet[i])); //adiciona o ator encontrado à lista do filme vinculado a este.
                    }
                    else{ //Caso seja filme 
                        IDAtoresF.setNome(vet[i]); //Renomeia a lista de relacionamentos para o novo filme encontrado;
                        dicRelationships.insertItem(IDAtoresF.getNome(), IDAtoresF.getList()); 
                        IDAtoresF.getList().removeAll(IDAtoresF.getList()); //esvazia a lista de relacionamentos para novos inserts
                        /*for(int j=0; j<dicRelationships.getSizeVetBuckets();j++){
                            for(int k=0; k<dicRelationships.size();k++){
                                System.out.println("Posição  "+i+": "+dicRelationships.findElement(vet[i]));
                            }*/
                                
            
                    }    
                         // Adiciona o relacionamento filme_atores no dicionario de relacionamentos
                       
                }
                else{
                    System.out.println("Vazio");
                }
            }
        } 
    }        
}
