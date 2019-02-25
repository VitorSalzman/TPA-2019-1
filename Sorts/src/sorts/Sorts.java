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
    
     private static void quickSort(int[] vetor, int inicio, int fim) {
             if (inicio < fim) {
                    int posicaoPivo = separar(vetor, inicio, fim);
                    quickSort(vetor, inicio, posicaoPivo - 1);
                    quickSort(vetor, posicaoPivo + 1, fim);
             }
       }
  
       private static int separar(int[] vetor, int inicio, int fim) {
             int pivo = vetor[inicio];
             int i = inicio + 1, f = fim;
             while (i <= f) {
                    if (vetor[i] <= pivo)
                           i++;
                    else if (pivo < vetor[f])
                           f--;
                    else {
                           int troca = vetor[i];
                           vetor[i] = vetor[f];
                           vetor[f] = troca;
                           i++;
                           f--;
                    }
             }
             vetor[inicio] = vetor[f];
             vetor[f] = pivo;
             return f;
       }
       
       
    
}
