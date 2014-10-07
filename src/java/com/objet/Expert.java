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
public class Expert extends User {
    private ArrayList<Oeuvre> OeuvreExpertisee=new ArrayList<>();
    public Expert(){
        
    }

    public ArrayList<Oeuvre> getOeuvreExpertisee() {
        return OeuvreExpertisee;
    }
    
    public void addOeuvreExpertisee(Oeuvre o){
        this.OeuvreExpertisee.add(o);
    }

    public void setOeuvreExpertisee(ArrayList<Oeuvre> OeuvreExpertisee) {
        this.OeuvreExpertisee = OeuvreExpertisee;
    }
    public Expert(String log,String pass,String name,String first,ArrayList<Oeuvre> list){
        super(log,pass,name,first);
        OeuvreExpertisee=list;
    }
    
}
