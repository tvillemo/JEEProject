/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tanguy
 */
public class Artiste extends User{
    private List<Oeuvre> OeuvreList=new ArrayList<>();

    public List<Oeuvre> getOeuvreList() {
        return OeuvreList;
    }

    public void addOeuvre(Oeuvre o){
        this.OeuvreList.add(o);
    }
    
    public void setOeuvreList(List<Oeuvre> OeuvreList) {
        this.OeuvreList = OeuvreList;
    }
   
    public Artiste(){
    }
    public Artiste(String log,String pass,String name,String first,ArrayList<Oeuvre> list){
        super(log,pass,name,first);
        OeuvreList=list;
    }
    
        @Override
    public String getRole(){
        return "Artiste";
    }
    
}
