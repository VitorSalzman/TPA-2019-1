/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.Random;

/**
 *
 * @author Salzman
 */
public class Sorts {
    
    public static int[] geraVetor(int tam){
        Random random = new Random();
        int n;
        int vet[] = new int[tam];
        for(int i=0;i<tam;i++){
            n = random.nextInt(100);
            vet[i]=n;
        }
        return vet;
    }
    public static int select(int[] vet){
        int n=vet.length;
        int i=0;
        int j=0;
        int aux=0;
        int menor=0;
        int trocas=0;
        
        for(i=0;i< n - 1;i++){
            menor = i;
            for(j=i+1; j<n;j++){
                if(vet[j]<vet[menor]){
                    menor = j;
                }
            }
            aux=vet[i];
            vet[i] = vet[menor];
            vet[menor] = aux;
            trocas+=1;
        }
        
        for(int k=0;k<n;k++){
            System.out.println("A posição "+k+"do vetor é: "+vet[k]);
        }
        
        return trocas;
    }
    
    public static int insert(int[] vet){
        int i=0;
        int j=0;
        int n=vet.length;
        int aux=0;
        int trocas=0;
        
        for(i=1;i <= (n-1);i++){
            aux=vet[i];
            
            j=i-1;
            while(j >= 0 && aux<vet[j]){
                vet[j+1]=vet[j];
                j--;
            }
            vet[j+1]=aux;
            trocas+=1;
            }
        
        return trocas;
    }
    
    public static int bubble(int[] vet){
        int aux=0;
        int i=0;
        int j=0;
        int n=vet.length;
        int trocas=0;
        
        for(i=0;i<n;i++){
            for(j=0;j<(n-1);j++){
                if(vet[j] > vet[j + 1]){
                    aux = vet[j];
                    vet[j] = vet[j+1];
                    vet[j+1] = aux;
                    trocas+=1;
                }
            }
        }
        
        
        System.out.println("o número de trocas é " +trocas);
        return trocas;
    }
    
    void shellSort(int vet[], int size) {
        int i , j , value;
        int gap = 1;
        while(gap < size) {
            gap = 3*gap+1;
        }
        while ( gap > 1) {
            gap /= 3;
            for(i = gap; i < size; i++) {
                value = vet[i];
                j = i - gap;
                while (j >= 0 && value < vet[j]) {
                    vet [j + gap] = vet[j];
                    j -= gap;
                }
                vet [j + gap] = value;
            }
        }
        
    }    
    
   public static int quicksort(int vet[], int ini, int fim,int trocas){
       int meio;
       if(ini<fim){
           meio = partition(vet,ini,fim,trocas);
           quicksort(vet,ini,meio,trocas);
           quicksort(vet,meio+1,fim,trocas);
       }
       
       return trocas;
   }
       
   public static int partition(int vet[], int ini, int fim, int trocas){
       int pivo, topo, i;
       
       pivo = vet[ini];
       
       topo = ini;
       
       for(i=ini+1;i<=fim;i++){
           if(vet[i] < pivo){
               vet[topo] = vet[i];
               trocas+=1;
               vet[i] = vet[topo+1];
               trocas+=1;
               topo++;
               trocas+=1;
           }
       }
       vet[topo]=pivo;
       return topo;
   }    
    
}
