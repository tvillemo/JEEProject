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
public class Acheteur extends User {
    private String rmq;
    private ArrayList<Oeuvre> OeuvreAchetee = new ArrayList<>();
    
    public Acheteur(){
        
    }
    public Acheteur(String log,String pass,String name,String first,String rm){
        super(log,pass,name,first);
        rmq=rm;
    }

    public void addOeuvreAchetee(Oeuvre o){
        this.OeuvreAchetee.add(o);
    }
    
    public String getRmq() {
        return rmq;
    }

    public void setRmq(String rmq) {
        this.rmq = rmq;
    }
    
    
}
