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
 
@ManagedBean
public class ringView implements Serializable {
     
    private List<Artiste> artiste;
    private Artiste selectedArtiste;
     
    @PostConstruct
    public void init() {
        artiste = new ArrayList<Artiste>();
        
        
        artiste.add(BDDContact.getArtisteFromBDDWithID(3));
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
