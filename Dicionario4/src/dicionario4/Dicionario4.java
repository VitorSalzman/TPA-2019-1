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
        dic.insertItem(498254, new RegHost(4824,"Gladstone",32,1));
        dic.insertItem(798814, new RegHost(4248,"DisGladstone",32,1));
        dic.insertItem(687675, new RegHost(7985,"Jobson",64,1));
        System.out.println("feito!");
        
        RegHost dado = (RegHost)dic.findElement(587864);
        
        if(dado != null){
            System.out.println(dado.getIp()+", "+dado.getNome());
        }
        else{
            System.out.println("Erro 404 not found!");
        }
        
        System.out.println("Tamanho do dicionario antes de alterar Gladstone" + dic.size());
        dic.insertItem(498254, new RegHost(4824,"Gladstone",16,1));
        System.out.println("Tamanho do dicionario após alterar chave Gladstone" + dic.size());
        
        System.out.println("Removendo host Jobson do dicionario..");
        dic.removeElement(687675);
        System.out.println("Tamanho do dicionario após a remoção do host Jobson" + dic.size());
        dado = (RegHost)dic.findElement(687675);
        
        if(dado != null){
            System.out.println(dado.getIp()+", "+dado.getNome());
        }
        else{
            System.out.println("Erro 404 not found!");
        }
        
        
        
        
        
    }
    
}
