/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tanguy
 */
public class Vendeur extends User implements Serializable{
    private ArrayList<Oeuvre> OeuvreVendu=new ArrayList<>();

    public ArrayList<Oeuvre> getOeuvreVendu() {
        return OeuvreVendu;
    }
    
    public void addOeuvreVendu(Oeuvre o){
        this.OeuvreVendu.add(o);
    }
    
    public void setOeuvreVendu(ArrayList<Oeuvre> OeuvreVendu) {
        this.OeuvreVendu = OeuvreVendu;
    }
    
    public Vendeur(){
        
    }
    public Vendeur(String log,String pass,String name,String first,ArrayList<Oeuvre> list){
        super(log,pass,name,first);
        OeuvreVendu=list;
    }
}
