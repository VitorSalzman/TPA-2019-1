/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import TADMatriz.TADMatriz;

/**
 *
 * @author vitorsalzman
 */
public class Apmattpa {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        String nome_arq = "bdaritmat.csv";
        String caminho_arq = "C:\\Users\\Salzman\\Documents\\GitHub\\TPA-2019-1\\Dicionario\\src\\aplicacao\\bdmatrizes\\";
        String arquivoMatriz;
        
        BufferedReader bufferReader = new BufferedReader(new FileReader(caminho_arq+nome_arq));
        
        String linha = bufferReader.readLine();
        arquivoMatriz = linha+".txt";
        TADMatriz mat = TADMatriz.carrega(caminho_arq + arquivoMatriz);
        TADMatriz mAux;
        System.out.println("Arquivo a carregar: " +caminho_arq+arquivoMatriz);
        mat.imprimeMatriz();
        
        linha = bufferReader.readLine();
        
        while(linha != null){
            String linha2[] = linha.split(",");
            if(linha2[0]=="+"){
                arquivoMatriz = linha2[1] + ".txt";
                mAux = TADMatriz.carrega(caminho_arq+arquivoMatriz);
                mat = mat.soma(mAux);
                System.out.println(" ___________Matriz após a soma____________");
                mat.imprimeMatriz();
                break;
            }
            else if(linha2[0]=="-"){
                arquivoMatriz = linha2[1] + ".txt";
                mAux = TADMatriz.carrega(caminho_arq+arquivoMatriz);
                mAux.vezesK(-1f);
                mat = mat.soma(mAux);
                System.out.println(" ___________Matriz após subtração____________");
                mat.imprimeMatriz();
                break;
            }
            else if(linha2[0]=="*"){
                if(linha2[1].matches("[0-9]+")){ //expressão regular
                    Float mult = Float.parseFloat(linha2[1]);
                    mat.vezesK(mult);
                    System.out.println(" ____________Matriz após a multiplicação por K___________");
                    mat.imprimeMatriz();
                }
            
                else{
                    arquivoMatriz = linha2[1]+".txt";
                    mAux = TADMatriz.carrega(caminho_arq + arquivoMatriz);
                    mat = mat.multi(mAux);
                    System.out.println(" ______________Matriz após a multiplicação_____________");

                    }
            }
            else if(linha2[0]=="t"){
                mat = mat.transposta();
                System.out.println(" _____________Matriz transposta_____________");
                mat.imprimeMatriz();
            }
            
            linha = bufferReader.readLine();
        }
        
        System.out.println(" ___________A última matriz__________");
        mat.imprimeMatriz();
        mat.salva(caminho_arq+"resposta.txt");
        
      
        
        
    }
    
}
