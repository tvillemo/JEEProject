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
public class Artiste extends User{
    private ArrayList<Oeuvre> OeuvreList=new ArrayList<>();

    public ArrayList<Oeuvre> getOeuvreList() {
        return OeuvreList;
    }

    public void addOeuvre(Oeuvre o){
        this.OeuvreList.add(o);
    }
    
    public void setOeuvreList(ArrayList<Oeuvre> OeuvreList) {
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
