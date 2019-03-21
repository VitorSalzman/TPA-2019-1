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
public class RegDado{
    private String portugues;
    private String ingles;
    
    public RegDado(String port, String eng){
        portugues = port;
        ingles = eng;
        
    }
    public String getWrt(){
        return ingles;
        
    }
    public String getKey(){
        return portugues;
    }
}
