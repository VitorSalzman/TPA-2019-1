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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TADDicChain vet = new TADDicChain(256);
        RegDado dado = new RegDado("Laranja", "Orange");
        
        vet.insertItem(dado.getKey(), dado);
        System.out.println(vet.size());
        
    }  
    
}
