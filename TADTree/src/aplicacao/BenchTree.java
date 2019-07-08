/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.io.IOException;
import tadtree.ProcessaArvore;
import tadtree.TreeTPAV1;

/**
 *
 * @author Salzman
 */
public class BenchTree {
    
    public static void main(String[] args) throws IOException {
        
        
        String file_path = "C:\\Users\\Salzman\\Documents\\GitHub\\TPA-2019-1\\TADTree\\src\\tadtree\\fileTree\\";
        String arq_read = "aluno_jgo_da_velha.txt";
        String arq_read_prof = "PROFESSOR.txt"; //simulando arquivo de professor
        TreeTPAV1<String> t = new TreeTPAV1<String>(); 

        TreeTPAV1<String> tp = new TreeTPAV1<String>();
        
        ProcessaArvore pa = new ProcessaArvore(t);
        ProcessaArvore PROFESSOR = new ProcessaArvore(tp);
        tp = PROFESSOR.carrega_tree(file_path+arq_read_prof);
        
        t = pa.povoaVelha();
        
        
        pa.salva_tree("teste.txt");
        
        

       
     
    }   
}
