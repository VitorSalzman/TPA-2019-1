/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario_com_funcoes_hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 *
 * @author vitorsalzman
 */
public class HashBernsteinM extends hash_engine{
    
    private static long bernsteinM(String k) {
        long h = 0;
        int i;
        
        for(i = 0; i < k.length(); i++) {
            h = (33 + h) ^ (int)k.charAt(i);
        }
        
        return Math.abs((int)h);
    }
    
    
    public static long bernsteinM2(String str) {
	long hash = 0;
        
	for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + ((hash << 5) - hash);
	}
        
	return hash;
    }
    

    @Override
    public long hash_func(Object k) {
        long saida;
        long soma = 0;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] vetBytes = null;
        
        try {
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(k);
                out.flush();
                vetBytes = bos.toByteArray();
            } 
            catch(IOException e) {
                e.printStackTrace();
            }
            
        } 
        finally {
            try {
                bos.close();
            } 
            catch(IOException ex) {
                ex.printStackTrace();
            }
        } 
        
        saida = HashBernsteinM.bernsteinM(k.toString());
        
        return saida;
    }
}
