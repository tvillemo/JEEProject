/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mathilde
 */
public class OeuvreService {
    
    private List<Oeuvre> oeuvreList=new ArrayList<>();
    
    public void setList(){
        oeuvreList=BDDContact.getOeuvreFromBDD();
    }
    public void addList(Oeuvre oe) {
        oeuvreList.add(oe);
    }

    public List<Oeuvre> getList() {
        return oeuvreList;
    }
    
    /*public List<String> getNom() {
       return Arrays.asList(Oeuvre.nom); 
    }
    */
}
