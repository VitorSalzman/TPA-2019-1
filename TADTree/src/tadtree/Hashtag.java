/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadtree;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Salzman
 */
public class Hashtag {
    
    private LinkedList<Character> listChar; 
    
    public Hashtag(String str){
        toListChar(str);
        this.listChar=toListChar(str);
        
    }
    
    private LinkedList<Character> toListChar(String str){
        List<Character> list = new LinkedList<Character>();
        for(char c : "abc".toCharArray()){
            list.add(c);
        }
        return (LinkedList<Character>) list;
    }
    
    public LinkedList<Character> getList(){
        return this.listChar;
    }
    
}

