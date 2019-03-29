/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario;

import java.io.FileWriter;
import java.io.IOException;
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
    
    public int exponencial31(int expo){
        int tot=0;
        for(int i=0;i<expo;i++){
            tot=tot*31;
        }
        
        return tot;
    }
    
    public long hashCodePol(String str){
        int soma=0;
        int pol=0;
        int expo=0;
        for(int i=0;i<str.length();i++){
            pol=exponencial31(expo);
            expo++;
            soma = (soma + (int)str.charAt(i))*pol;
        }
        return soma;
    }
    
    public void insertItem(String str,RegDado d){
        RegDado aux = findElement(str);
        
        if(aux == null){
            long hash_code = hashCode(str);
            int indice = (int)hash_code % vetBuckets.length;
            vetBuckets[indice].add(d);
            quant_entradas++;
        }
        else{
            aux.setWrt(str);
        }
        
    }
    
    public int size(){
        return quant_entradas;
    }
    
    
    public RegDado findElement(String s){
        long hash_code = hashCode(s);
        int indice = (int)hash_code % vetBuckets.length;
        
        
        int i=0;
        
        while(i < vetBuckets[indice].size()){
            if(((RegDado)(vetBuckets[indice].get(i))).getKey().equals(s)){
                return (RegDado)vetBuckets[indice].get(i);
            }
        }
        
        return null;
    }
   
    public RegDado removeElement(String str){
        RegDado aux = findElement(str);
        if(aux==null){
            return null;
        }
        else{
            long hash_code = hashCode(str);
            int indice = (int)hash_code % vetBuckets.length;
            
            int posList=0;
            while(posList<(vetBuckets[indice].size()) && ((RegDado)(vetBuckets[indice].get(posList))).getKey().equals(str)){
                posList++;
            }
            
            vetBuckets[indice].remove(posList-1);
            quant_entradas--;
            
            return aux;
        }
    }
    
    public int[] getColisoes(){
        int vet[] = new int[vetBuckets.length];
        for(int i=0;i<vetBuckets.length;i++){
            vet[i]=vetBuckets[i].size();
        }
        
        return vet;
    }
    
    
    public void exibeDiagrama(int[] vet) throws IOException{ //cria um arquivo CSV com as colisões
        FileWriter writer = null;
        
        try{
            writer = new FileWriter(".\\diagrama.csv",true);
            
            for(int i=0;i<vet.length;i++){
                writer.append((char) i + ",0");
                writer.append((char) vet[i] + " \n");
            }
            
            
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            writer.close();
        }
        
    }
    
    
    
    
    
}

