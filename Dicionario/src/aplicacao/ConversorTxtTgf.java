/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Salzman
 */
public class ConversorTxtTgf{ ///tudo no inicio
    public static void main(String[] args){
    String nome_arq; 
    File arq = new File (nome_arq);
        Scanner s = new Scanner(arq);
        String line;
        LinkedList<String> lista = new LinkedList();
        int l=0,c=0;
        
        while(s.hasNextLine()) {
            l++;   
            line = s.nextLine(); 
            String[] vet = line.split(" ");    //Separação de valores
            
            for (int i = 0; i < vet.length; i++) 
                if (!vet[i].isEmpty())
                    lista.addLast(vet[i]);
            
            if (l == 1) {
                c = lista.size();
            }
        }
}
}
