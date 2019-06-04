/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author vitorsalzman
 */



public class TADDicChain {
    
       
    
    private LinkedList[] vetBuckets = null;
    private double fator_carga = 0.75;
    private int quant_entradas = 0;
    private Hash_engine he = null;
    private boolean achou = false;
    
    public TADDicChain(int quantEntradas){
        int tam = (int)(quantEntradas/fator_carga);
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        
        he = new HashEngineDefault();
        
    }
    
    public TADDicChain(){
        int tam = 1024;
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        he = new HashEngineDefault();
    }
    
    public TADDicChain(Hash_engine he){
        int tam = 1024;
        vetBuckets = new LinkedList[tam];
        
        for(int i=0;i<tam;i++){
            vetBuckets[i] = new LinkedList<TDicItem>();
        }
        if(he == null) this.he = new HashEngineDefault();
        else this.he=he;
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
        
        if(NO_SUCH_KEY()){
            TDicItem item = new TDicItem(k,o);
            item.setCache(hash_code);
            vetBuckets[indice].add(item);
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
            
            
            
            quant_entradas--;
            achou=true;
            return aux;
        }
    }    
    
    public boolean equals(TADDicChain anotherDic) {
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
     
    
    public LinkedList keys(){
        LinkedList<TDicItem> itens = getItens(); 
        LinkedList chaves = null;
        
        
        if(!isEmpty()){
            chaves = new LinkedList();
            
            for(TDicItem it : itens){
                chaves.add(it.getKey());
            }
        }
        return chaves;   
    } 
    
    public LinkedList<Object> elements() {
        LinkedList<TDicItem> itens = getItens(); 
        LinkedList elems = null;
        
        
        if(!isEmpty()){
            elems = new LinkedList();
            
            for(TDicItem it : itens){
                elems.add(it.getDado());
            }
        }
        return elems;
    }
    
    public LinkedList getItens() {
        LinkedList<Object> i = new LinkedList<Object>();
        
        if(isEmpty()) {
            return i;
        }
        else {
            for(int posVet = 0; posVet < getSizeVetBuckets(); posVet++) {
                if(vetBuckets[posVet].size() > 0) {
                    for(int posList = 0; posList < vetBuckets[posVet].size(); posList++) {
                        i.add(((TDicItem)vetBuckets[posVet].get(posList)).getDado());
                    }
                }
            }
            
            return i;
        }
    }
    
    
     public TADDicChain clone() {
        TADDicChain dicClone = new TADDicChain(he);
        
        for(int posVet = 0; posVet < vetBuckets.length; posVet++) {
            for(int posList = 0; posList < vetBuckets[posVet].size(); posList++) {
                Object chave = ((TDicItem)vetBuckets[posVet].get(posList)).getKey();
                Object valor = ((TDicItem)vetBuckets[posVet].get(posList)).getDado();
                dicClone.insertItem(chave, valor);
            }
        }
        
        return dicClone;
    }
    
     public LinkedList<Integer> getVetColisoes(){
         LinkedList<Integer> list = new LinkedList();
         for(int i=0; i<this.getSizeVetBuckets(); i++){
             list.add(vetBuckets[i].size());
         }
         
         return list;
     }
     
     
    
}
