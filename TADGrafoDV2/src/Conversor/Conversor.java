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
    private  TADDicChain dicElements;
    private TADDicChain dicRelationships;
    private arrayAtoresF listAtoresF;
    private int geraID=0;
    public Conversor(String nome_arq){
        this.nome_arq=nome_arq;
    }
    
    //retorna false caso a string possua algum caracter numérico.
    public boolean onlyChar(String s){
        return s.matches("[A-Z a-z Çç]{"+s.length()+"}");
    }
    
    public int geraIDVertex(){
        int id = geraID++;
        return id;
    }
    
    public void converte(String nomearq) throws FileNotFoundException{
        File arq = new File (nomearq);
        Scanner s = new Scanner(arq);
        String line;
        ArrayList atores = new ArrayList();
        listAtoresF = new arrayAtoresF(null);
        
        
        while(s.hasNextLine()) {  
            line = s.nextLine(); 
            String[] vet = line.split("/");    //Separação de valores
            
            
               
            
            
             
            for (int i = 0; i < vet.length; i++){ 
                dicElements.insertItem(vet[i], geraIDVertex()); // Insere item de chave filme/ator e valor id;
                if (!vet[i].isEmpty()){
                    if(!onlyChar(vet[i])){ //Se for filme...
                        if(listAtoresF.getList()!=null){
                            dicRelationships.insertItem(listAtoresF.getNome(), listAtoresF.getList()); 
                        } // Adiciona o relacionamento filme_atores no dicionario de relacionamentos
                        atores.removeAll(atores); //esvazia a lista de relacionamentos para novos inserts
                        listAtoresF.setNome(vet[i]); //Renomeia a lista de relacionamentos para o novo filme encontrado;
                        
                    }
                    else{ //FALTA VERIFIFICAR SE O ATOR JÁ ESTEVE EM FILMES ENCONTRADOS ANTES DESSE
                        listAtoresF.getList().add(vet[i]); //adiciona o ator encontrado à lista do filme vinculado a este.
                    }
                }
                else{
                    System.out.println("Vazio");
                }
            }
        } 
    }        
}
