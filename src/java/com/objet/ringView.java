/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
 
@ManagedBean
public class ringView implements Serializable {
     
    private List<Oeuvre> listOeuvre;
    private List<Artiste> artiste;
    private Artiste selectedArtiste;

     
    @PostConstruct
    public void init() {
        artiste = BDDContact.getArtisteFromBDD();
    }
    
    public void setOeuvre(List<Oeuvre> oeuvre){
        listOeuvre=selectedArtiste.getOeuvreList();
    }
    
    public List<Oeuvre> getOeuvre(){
        return listOeuvre;
    }
 
    public List<Artiste> getCars() {
        return artiste;
    }
 
    public void setCars(List<Artiste> cars) {
        this.artiste = cars;
    }
 
    public Artiste getSelectedCar() {
        return selectedArtiste;
    }
 
    public void setSelectedCar(Artiste selectedCar) {
        this.selectedArtiste = selectedCar;
    }
}
