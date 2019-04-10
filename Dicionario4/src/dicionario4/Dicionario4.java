/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicionario4;

/**
 *
 * @author vitorsalzman
 */
public class Dicionario4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Boa noite!");
        
        TADDicChain4 dic = new TADDicChain4(100);
        
        dic.insertItem(587864, new RegHost(7867,"Gislaine",64,1));
        System.out.println("feito!");
        
        RegHost dado = (RegHost)dic.findElement(587864);
        
        if(dado != null){
            System.out.println(dado.getIp()+", "+dado.getNome());
        }
        else{
            System.out.println("Erro 404 not found!");
        }
        
    }
    
}
