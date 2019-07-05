/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadtree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Salzman
 */
public class TADTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        TreeTPAV1<String> t = new TreeTPAV1<String>();
        Node<String> no_raiz = new Node<String>("A");
        
        Node<String> nB, nC, nW, nZ;
        
        t.setRootElement(no_raiz);
        
        LinkedList<Node<String>> filhos_da_raiz = new LinkedList<Node<String>>();
        
        nB = new Node<String>("B");
        filhos_da_raiz.add(nB);
        
        nC = new Node<String>("C");
        nC.addChild(new Node<String>("D"));
        nC.addChild(new Node<String>("E"));
        
        filhos_da_raiz.add(nC);
        
        nW = new Node<String>("W");
        nZ = new Node<String>("Z");
        nW.addChild(nZ);
        filhos_da_raiz.add(nW);
        
        no_raiz.setChildren((List<Node<String>>)filhos_da_raiz);
        
        System.out.println(t.toString());
        
        ProcessaArvore p = new ProcessaArvore(t);
        p.write();
        
    }
    
}
