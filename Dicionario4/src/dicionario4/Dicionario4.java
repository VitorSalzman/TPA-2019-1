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
        
        TADDicChain4 dic = new TADDicChain4(5);
        System.out.println("AQUI");
        dic.insertItem(587864, new RegHost(7867,"Gislaine",64,1));
        System.out.println("AQUI2");
        dic.insertItem(498254, new RegHost(4824,"Gladstone",32,1));
        System.out.println("AQUI3");
        dic.insertItem(798814, new RegHost(4248,"DisGladstone",32,1));
        System.out.println("AQUI4");
        dic.insertItem(28478, new RegHost(7985,"Jobson",64,1));
        System.out.println("AQUI5");
        dic.insertItem(68767, new RegHost(5682,"Jenilson",64,1));
        System.out.println("AQUI6");
        dic.insertItem(89784, new RegHost(2656,"Donald",64,1));
        
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
        /*
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
        */
        
        
        
        
    }
    
}
