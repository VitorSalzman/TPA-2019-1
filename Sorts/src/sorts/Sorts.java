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
    
    public static int quick(int vet[], int esq, int dir){
        int pivo = esq, i,ch,j;         
        int trocas=0;
        for(i=esq+1;i<=dir;i++){        
            j = i;                      
            if(vet[j] < vet[pivo]){     
                ch = vet[j];               
                while(j > pivo){           
                    vet[j] = vet[j-1];      
                    trocas+=1;
                    j--;                    
                }
                vet[j] = ch;               
                pivo++;                    
            }
        }
        if(pivo-1 >= esq){              
            quick(vet,esq,pivo-1);      
        }
        if(pivo+1 <= dir){              
            quick(vet,pivo+1,dir);      
        }
        
        return trocas;
        
        
    }
       
       
    
}
