/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario;

/**
 *
 * @author Salzman
 */
public class TDicItem {
        private Object key;
        private Object dado;
        private long HashCache;
        
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
        
        public long getCache(){
            return HashCache;
        }
        
        public void setCache(long cache){
            this.HashCache = cache;
        }

  
        /**************mexendo aqui******************/

}
