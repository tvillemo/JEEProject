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
public class Directeur extends User {
    private ArrayList<Vendeur> sellerList=new ArrayList<>();
    
    Directeur(){};
    
    Directeur (String log,String pass,String name,String first,ArrayList<Vendeur> list){
        super(log,pass,name,first);
        sellerList=list;
    }
    
    public void addSeller(Vendeur v){
        this.sellerList.add(v);
    }
    
    public ArrayList<Vendeur> getSellerList() {
        return sellerList;
    }

    public void setSellerList(ArrayList<Vendeur> sellerList) {
        this.sellerList = sellerList;
    }
    
        @Override
    public String getRole(){
        return "Directeur";
    }
    
}
