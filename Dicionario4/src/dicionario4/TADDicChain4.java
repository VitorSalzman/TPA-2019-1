/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario4;

import java.util.LinkedList;

/**
 *
 * @author vitorsalzman
 */


public class TADDicChain4 {
    
    class TDicItem {
        private Object key;
        private Object conteudo;
        
        public TDicItem(Object k, Object cont){
            this.key = k;
            this.conteudo = cont;
        }

    }    
    
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
        he = new HashFuncDefault();
    }
        
    
    
    
}
