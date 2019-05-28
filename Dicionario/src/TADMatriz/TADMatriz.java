/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADMatriz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Salzman
 */
public class TADMatriz {
    Float mat[][];
    int linhas;
    int colunas;
    
    public TADMatriz(int linhas, int colunas){
        this.linhas=linhas;
        this.colunas=colunas;
        this.mat=cria_mat(linhas, colunas);
    }
    
    public int quantLinhas(){
        return linhas;
    }    
    
    public int quantColunas(){
        return colunas;
    }
    public Float[][] cria_mat(int linhas, int colunas){
        mat = new Float[linhas][colunas];
        
        for(int i=0;i<linhas;i++){
            for(int j=0;j<colunas;j++){
                mat[i][j]= null;
            }
        }
        return mat;
    }
    
    public Float getElem(int i, int j){
        if((i<=linhas) && (i>=0)){
            if((j<=linhas) && (j>=0)){
                for(int k=0;k<=linhas;k++){
                    for(int l=0;l<=colunas;l++){
                        if((i==k)&&(j==l)){
                            return mat[i][j];///se a posição passada não tiver nenhum elemento, será retornado nulo
                        }    
                    }
                }    
            }
            else{
                return null;
            }    
        }
        else{
            return null;
        }
        return null;
    }  ///retorna null somente se as posições passadas extrapolarem as dimensões da matriz
    
    public Float setElem(int i, int j, Float valor){
        if((i<=linhas) &&(i>=0)){
            if((j<=colunas)&&(j>=0)){
                mat[i][j]=valor;
                return valor;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
    
    public TADMatriz soma(TADMatriz m){
        if((m.linhas == this.linhas) && (m.colunas == this.colunas)){
            TADMatriz mAux = new TADMatriz(this.linhas,this.colunas);
            
            for(int i=0; i<this.linhas; i++){
                for(int j=0; j<this.colunas; j++){
                    mAux.setElem(i,j,((this.getElem(i, j))+(m.getElem(i,j))));
                }
            }
            return mAux;
        }
        else{
            return null;
        }
    }
    
    public void vezesK(float k){
        float produto;
        for(int i=0; i<this.quantLinhas(); i++){
            for(int j=0; j<this.quantColunas(); j++){
                produto=(this.getElem(i,j)*k);
                this.setElem(i,j,produto);
            }
        }          
    }
    public TADMatriz multi(TADMatriz m){
        if(this.quantColunas() != m.quantLinhas()){
            return null;
        }
        
        TADMatriz mAux = new TADMatriz(this.quantLinhas(), m.quantColunas());
        for(int i=1; i<=this.quantLinhas();i++){
            for(int j=1;j<=m.quantColunas();j++){
                for(int k=1;k<=this.quantColunas();k++){
                    mAux.setElem(i,j,mAux.getElem(i, j)+(this.getElem(i, k)*m.getElem(k, j)));
                }
            }
        }    
        return mAux;
    }
    
    public TADMatriz transposta(){
        TADMatriz mAux = new TADMatriz(this.colunas,this.linhas);
        
        for (int i=0; i<this.quantLinhas(); i++) {
            for (int j=0; j<this.quantColunas(); j++) {
                mAux.setElem(j,i,this.getElem(i,j));
            }
        }
        return mAux;    
    }
      
}
