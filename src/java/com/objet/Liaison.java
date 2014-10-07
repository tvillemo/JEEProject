/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.util.ArrayList;

/**
 *
 * @author tanguy
 */
public class Liaison {
    private ArrayList<Caracteristique> carac=new ArrayList<>();
    private Objet obj=new Objet();
    private ArrayList<String> value=new ArrayList<>();

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }
    
    public Liaison(){
        
    }
    public Liaison (Objet object,ArrayList<Caracteristique> caracList){
        this.carac=caracList;
        this.obj=object;
    }
    
    public Object getObject(){
        return this.obj;
    }
    public ArrayList<Caracteristique> getListCarac(){
        return this.carac;
    }
    
    public void addValue(String val){
        this.value.add(val);
    }
}
