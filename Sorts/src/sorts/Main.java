/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.Scanner;

/**
 *
 * @author Salzman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int tam;
        
        
        /*Scanner ler = new Scanner(System.in);
        System.out.printf("Informe um número para a tabuada: ");
        tam = ler.nextInt();
        */
        int vet10[];
        int a=10;
        int trocas=0;
        vet10=Sorts.geraVetor(a);
        
        
        /*for(int i=0;i<vet10.length;i++){
            System.out.println("O elemento "+i+" do vetor é :"+vet10[i]);
        }
        */
        trocas=Sorts.quickSort(vet10,vet10[0],vet10[9]);
        System.out.println("Trocas: "+trocas);
        
        
        
        
    }
    
}
