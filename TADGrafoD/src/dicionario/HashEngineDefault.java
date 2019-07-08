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

/**
 *
 * @author Salzman
 */
public class HashEngineDefault extends Hash_engine{
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
        return Math.abs(soma);
    
        
    }    
    
}