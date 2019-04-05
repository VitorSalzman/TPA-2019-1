/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author vitorsalzman
 */

class HashEngineDefault extends hash_engine{
    public long hash_fun(Object o){
        long soma=0;
        
        ByteArrayOutputStream bit = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] vetBytes = null;
        
        try{
            try{
                out = new ObjectOutputStream(bit);
                out.writeObject(o);
                out.flush();
                vetBytes = bit.toByteArray();
            } catch(IOException e){
                e.printStackTrace();
            }
            
        } finally{
            try{
                bit.close();
            } catch(IOException ex){
            }
        }    
         
        for(int i=0;i<vetBytes.length;i++){
            soma = soma + (int)vetBytes[i];
            
        }
        return soma;
    
        
    }    
    
}
class TDicItem {
        private Object key;
        private Object conteudo;
        
        public TDicItem(Object k, Object cont){
            this.key = k;
            this.conteudo = cont;
        }
        
        /**************mexendo aqui******************/

} 

public class TADDicChain4 {
    
       
    
    private LinkedList[] vetBuckets = null;
    private double fator_carga = 0.75;
    private int quant_entradas = 0;
    private hash_engine he = null;
    
    public TADDicChain4(int quantEntradas){
        int tam = (int)(quantEntradas/fator_carga);
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        
        he = new HashFuncDefault();
        
    }
    
    public TADDicChain4(hash_engine h){
        int tam = 100;
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        
        he = h;
    }
    
    public TADDicChain4(){
        int tam = 100;
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        he = new HashEngineDefault();
    }
    
    private int buscaDicItem(LinkedList<TDicItem> lst, Object o){
        int posList=0;
        
        while(posList<lst.size()){
            if(((TDicItem)(lst.get(posList))).getKey().equals(o)){
                return posList;
            posList++;
            }    
        }
        
        return -1;
        
    }
        
    
    
    
}
