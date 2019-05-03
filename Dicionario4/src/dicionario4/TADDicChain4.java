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
    public long hash_func(Object o){
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
        private Object dado;
        
        public TDicItem(Object k, Object d){
            this.key = k;
            this.dado = d;
        }
        
        public Object getKey(){
            return key;
        }
        
        public void setKey(Object o){
            this.key = o;
        }
        
        public void setDado(Object o){
            this.dado = o;
        }
        
        public Object getDado(){
            return dado;
        }
        
        /**************mexendo aqui******************/

} 

public class TADDicChain4 {
    
       
    
    private LinkedList[] vetBuckets = null;
    private double fator_carga = 0.75;
    private int quant_entradas = 0;
    private hash_engine he = null;
    private boolean achou = false;
    
    public TADDicChain4(int quantEntradas){
        int tam = (int)(quantEntradas/fator_carga);
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        
        he = new HashEngineDefault();
        
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
            }    
            posList++;
               
        }
        
        return -1;
        
    }
    
    public int size(){
        return quant_entradas;
    }
    
    public int getSizeVetBuckets(){
        return vetBuckets.length;
    }
    
    public boolean isEmpty(){
        return (this.quant_entradas > 0)? false: true;
    }
    
    private void redimensiona(){
        
        int newTam = 2*vetBuckets.length;
        LinkedList[] newVetBuckets = new LinkedList[newTam];
        
        for( int i = 0; i < newTam; i++) {
            newVetBuckets[i] = new LinkedList<TDicItem>();
        }
        
        
        for(int i=0; i<vetBuckets.length;i++){
            if(vetBuckets[i] != null){
                for(int j=0;j<vetBuckets[i].size();j++){
                    Object aux = (TDicItem)vetBuckets[i].get(j);
                    
                    long cod_hash = he.hash_func(((TDicItem)vetBuckets[i].get(j)).getKey());
                    int indice = (int)cod_hash % newVetBuckets.length;
                    newVetBuckets[indice].add(aux);
                }
            }
        }
        
        vetBuckets = newVetBuckets;
        
    }
    
    private int lenMaiorLst(){
        int maior=0;
        
        for(int i=0; i<vetBuckets.length;i++){
            if(vetBuckets[i] != null){
                if(vetBuckets[i].size() > maior){
                    maior = vetBuckets[i].size();
                }
            }
        }
        return maior;
    }    
               
    public void insertItem(Object k,Object o){
        if(lenMaiorLst() >= (int)vetBuckets.length * 0.30) {
            System.out.println("Redimensionando...");
            System.out.println("Tamanho atual vetBuckets: " + vetBuckets.length);
            System.out.println("Tamanho maior lista vetBuckets original: " + lenMaiorLst());
            
            redimensiona();
            
            System.out.println("Novo tamanho atual vetBuckets: " + vetBuckets.length);
            System.out.println("Tamanho maior lista novo vetBuckets: " + lenMaiorLst());
        }
        Object aux = findElement(k);
        long hash_code = he.hash_func(k);
        int indice = (int)hash_code % vetBuckets.length;
        
        if(aux==null){
            
            vetBuckets[indice].add(new TDicItem(k,o));
            quant_entradas++;
        }
        else{
            int pos = buscaDicItem(vetBuckets[indice],k);
            if(pos!=-1){
                ((TDicItem)(vetBuckets[indice].get(pos))).setDado(o);
            }
            
        }
        
    }
    
    public Object findElement(Object o){
        long hash_code = he.hash_func(o);
        int indice = (int)hash_code % vetBuckets.length;
        
        
        int i=0;
        
        while(i < vetBuckets[indice].size()){
            if(((TDicItem)(vetBuckets[indice].get(i))).getKey().equals(o)){
                
                achou=true;
                return ((TDicItem)(vetBuckets[indice].get(i))).getDado();
                
                
            }
            i=i+1;
        }
        achou=false;
        return null;
    }
     
    public boolean NO_SUCH_KEY(){
        return !achou;
    }
    
    public Object removeElement(Object o){
        Object aux = findElement(o);
        if(NO_SUCH_KEY()){
            
            achou=false;
            return null;
        }
        else{
            long hash_code = he.hash_func(o);
            int indice = (int)hash_code % vetBuckets.length;
            
            int posList=buscaDicItem(vetBuckets[indice],o);
            
            vetBuckets[indice].remove(posList);
            /*
            while(posList<(vetBuckets[indice].size()) && ((TDicItem)(vetBuckets[indice].get(posList))).getKey().equals(o)){
                posList++;
            }*/
            
            
            quant_entradas--;
            achou=true;
            return aux;
        }
    }    
    
    public boolean equals(TADDicChain4 anotherDic) {
        if(he == anotherDic.he) {
            if(this.size() == anotherDic.size()) {
                for(int posVet = 0; posVet < vetBuckets.length; posVet++) {
                    for(int posList = 0; posList < vetBuckets[posVet].size(); posList++) {
                        Object chave = ((TDicItem)(vetBuckets[posVet].get(posList))).getKey();
                        Object dado = ((TDicItem)(vetBuckets[posVet].get(posList))).getDado();
                        
                        Object outroDado = anotherDic.findElement(chave);
                        if(anotherDic.NO_SUCH_KEY() || (dado != outroDado)) {
                            return false;
                        } 
                    } 
                } 
            }
        }
        
        return true;
    } 
     
    
    public LinkedList<Object> keys(){///refazer
        if(quant_entradas == 0){
            return null;
        }
        else {
            LinkedList<Object> i = new LinkedList<Object>();
            
            for(int posVet = 0; posVet < vetBuckets.length; posVet++) {
                if(vetBuckets[posVet].size() > 0) {
                    for(int posList = 0; posList < vetBuckets[posVet].size(); posList++) {
                        i.add(((TDicItem)vetBuckets[posVet].get(posList)).getKey());
                    } 
                } 
            } 
            return i;
        } // else {   
    } 
    
     public TADDicChain4 clone() {
        TADDicChain4 dicClone = new TADDicChain4(he);
        
        for(int posVet = 0; posVet < vetBuckets.length; posVet++) {
            for(int posList = 0; posList < vetBuckets[posVet].size(); posList++) {
                Object chave = ((TDicItem)vetBuckets[posVet].get(posList)).getKey();
                Object valor = ((TDicItem)vetBuckets[posVet].get(posList)).getDado();
                dicClone.insertItem(chave, valor);
            }
        }
        
        return dicClone;
    }
    
    
}
