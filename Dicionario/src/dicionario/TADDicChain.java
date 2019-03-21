/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario;

import java.util.LinkedList;

/**
 *
 * @author Salzman
 */


public class TADDicChain {
    private LinkedList[] vetBuckets = null;
    int quant_entradas;
    int quant;
    public TADDicChain(int tam){
        vetBuckets = new LinkedList[tam];
        quant = tam;
        for(int i=0;i<quant;i++){
            vetBuckets[i] = new LinkedList<RegDado>();
        }
    }
    public long hashCode(String str){
        int soma=0;
        for(int i=0;i<str.length();i++){
            soma = soma + (int)str.charAt(i);
        }
        return soma;
    }
    
    public void insertItem(String str,RegDado d){
        long hash_code = hashCode(str);
        int indice = (int)hash_code % vetBuckets.length;
        vetBuckets[indice].add(d);
        quant_entradas++;
    }
    
    public int size(){
        return quant_entradas;
    }
    
    public int getSizevetBuckets(){
        return vetBuckets.length;
    }
    
    /*public boolean findItemvetBuckets(String s, TADDicChain vet){
        long hash_code = hashCode(s);
        int indice = (int)hash_code % vet.getSizevetBuckets();
        if(vet.size() == 0){
            return false;
        }
        int i=0;
        
        while(i<vet.getSizevetBuckets()){
            if(vet[i].g)
        }
    }*/
            
}

