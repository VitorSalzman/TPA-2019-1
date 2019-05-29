/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADMatriz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    
    public static TADMatriz carrega(String nome_arq) throws FileNotFoundException, IOException{
        BufferedReader buffRead = new BufferedReader(new FileReader(nome_arq));
        String linha = "";
        int i=0,j=0;
        
        LinkedList<String> result = new LinkedList<String>();
                
        while (true) {
            if (linha != null) {
                if(!linha.trim().isEmpty()){
                    result.add(linha);
                    i++;
                }
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        String[] line = result.get(1).split("\\s+");
        
        for (String string : line) {
            if(!string.isEmpty()){
                j++;
//                System.out.println("line");
            }
        }
        
//        System.out.printf("quantidade de linhas: "+i+"\n");
//        System.out.printf("quantidade de colunas: "+j+"\n");
        
        TADMatriz matriz = new TADMatriz(i,j);
        int countI=1,countJ=1;
        for (String lineResult : result) {
            line = lineResult.split("\\s+");
            for (String cell : line) 
                if(!cell.isEmpty()){
                    matriz.setElem(countI, countJ, Float.parseFloat(cell));
                    countJ++;
                }
            countJ=1;
            countI++;
        }
        
        return matriz;
        
    }
    
    public String salva(String nome_arq) throws IOException{
        /*
        salva a matriz corrente (this) em um arquivo texto de nome nome_arq cada linha do arquivo deve ser uma linha da matriz
        */
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(nome_arq, true));
        String line;
        for(int i=1;i<=this.quantLinhas();i++){
            line = "";
            for(int j=1;j<=this.quantColunas();j++){
                line += this.getElem(i, j)+" ";
            }
            line += "\n";
            bufferWriter.write(line);
        }
        bufferWriter.close();
        return nome_arq;
    }
      
}
