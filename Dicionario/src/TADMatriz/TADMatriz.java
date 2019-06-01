/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADMatriz;

import dicionario.TADDicChain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Salzman
 */
public class TADMatriz {
    int linhas;
    int colunas;
    TADDicChain dicDados;
    
    public TADMatriz(int linhas, int colunas){
        this.linhas=linhas;
        this.colunas=colunas;
        dicDados = new TADDicChain(null);
    }
    
    public int quantLinhas(){
        return linhas;
    }    
    
    public int quantColunas(){
        return colunas;
    }
    
    public void imprimeMatriz() {
        for(int i=1; i<=this.linhas; i++){
            for(int j=1; j<=this.colunas; j++){
                if(this.getElem(i, j)==null) continue;
                System.out.print(this.getElem(i, j)+" | ");
            }
            System.out.println("");
        }
    }
    
    public boolean isValidoP(int i, int j){
        if((i>=0) && (i<this.quantLinhas()) && (j>=0) && (j<this.quantColunas())) return true;
        return false;
    }
    
    public LinkedList diagonalPrincipal(){
        LinkedList d = null;
        if (quantLinhas() == quantColunas()){
             d = new LinkedList();
            for (int i=0; i<quantLinhas(); i++){
                for(int j=0; j<quantColunas(); j++){
                    if (i==j) d.add(this.getElem(i,j));
                }
            }   
        }
        else
            System.out.println("Incapaz de definir diagonal principal");
        return d;
    }
    
    public boolean isQuadrada(int linhas, int colunas){
        if(linhas == colunas) return true;
        return false;
    }
    
    public LinkedList diagonalSecundaria(){
        LinkedList d = null;
        
        if(isQuadrada(linhas,colunas)){
            d = new LinkedList();
            int n = this.quantColunas()-1;
            
            for(int i=0; i<this.quantLinhas(); i++){
                for(int j=this.quantColunas()-1; j>=0; j--){
                    if(i+j ==n) d.add(this.getElem(i, j));
                }
            }
            
        }
        else{
            System.out.println("Incapaz de definir diagonal secundária");
        }
        
        return d;
    }
    
    public Float getElem(int i, int j){
        Float dado = null;
        
        if(isValidoP(i,j)){
            String key = i+"|"+j;
            dado = (Float)this.dicDados.findElement(key);
            
            if(dado==null)dado=0F;
        }
        
        return dado;
    }  ///retorna null se as posições passadas extrapolarem as dimensões da matriz,
       ///ou se não encontrar nenhum dado na posição ij
    
    public Float setElem(int i, int j, Float valor){
        if(isValidoP(i, j)){
            String key = i+"|"+j;
            
            if(valor!=0F)
                dicDados.insertItem(key, valor);
            else
                dicDados.insertItem(key, null);
        }
        return valor;     
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
        LinkedList<String> posicoes = dicDados.keys();
        for (String chave : posicoes) {
            
            int i = Integer.parseInt(chave.substring(0, 1));
            int j = Integer.parseInt(chave.substring(2, 3));
            Float valor = getElem(i,j);
            this.setElem(i,j, valor*k);
        }          
    }
    public TADMatriz multi(TADMatriz m){
        if(!isValidoP(m.quantLinhas(),m.quantColunas())){
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
    
    public static TADMatriz carrega(String nome_arq) throws FileNotFoundException{
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
        TADMatriz m = new TADMatriz(l, c);
        int posList = 0;
        for(int i=0; i<l; i++){
            for(int j=0; j<c; j++){
                Float num = Float.parseFloat(lista.get(posList));
                m.setElem(i, j, num);
                posList++;
            }
        }
        return m;   
    }
    
    public String salva(String nome_arq) throws IOException{
        /*
        salva a matriz corrente (this) em um arquivo texto de nome nome_arq 
        cada linha do arquivo deve ser uma linha da matriz
        
        */
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(nome_arq, true));
        String line;
        for(int i=1;i<=this.quantLinhas();i++){
            line = "";
            for(int j=1;j<=this.quantColunas();j++){
                if(this.getElem(i, j)==null) continue;
                line += this.getElem(i, j)+" ";
            }
            line += "\n";
            bufferWriter.write(line);
        }
        bufferWriter.close();
        return nome_arq;
    }
}
