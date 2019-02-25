/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

/**
 *
 * @author Salzman
 */
public class Sorts {
    
    
    public int[] select(int[] vet){
        int n=vet.length;
        int i=0;
        int j=0;
        int aux=0;
        int menor=0;
        
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
        }
        
        return vet;
    }
    
    public int[] insert(int[] vet,int tam){
        int i=0;
        int j=0;
        int n=vet.length;
        int aux=0;
        
        for(i=1;i <= (n-1);i++){
            aux=vet[i];
            j=i-1;
            while(j >= 0 && aux<vet[j]){
                vet[j+1]=vet[j];
                j--;
            }
            vet[j+1]=aux;
            }
        return vet;
    }
    
    public int[] bubble(int[] vet){
        int aux=0;
        int i=0;
        int j=0;
        int n=vet.length;
        
        for(i=0;i<n;i++){
            for(j=0;j<(n-1);j++){
                if(vet[j] > vet[j + 1]){
                aux = vet[j];
                vet[j] = vet[j+1];
                vet[j+1] = aux;
                }
            }
        }
        return vet;
    }
    
    public int[] quickSort(int vet[], int inicio, int fim){
        int i=0; 
        int j=0;
        int meio=0;
        int aux=0;
 
        i = inicio;
        j = fim;
        meio = vet[(inicio + fim) / 2];
 
        do{
            while(vet[i] < meio){
                i++;
            }
            while(vet[j] > meio)
                j--;
            if(i <= j){
               aux = vet[i];
               vet[i] = vet[j];
               vet[j] = aux;
               i++;
               j--;
            }
        }while(i <= j);
 
        if(inicio < j){
            quickSort(vet, inicio, j);
        }
        if(i < fim){
            quickSort(vet, i, fim);
        }
        
        return vet;
    }
       
       
    
}
