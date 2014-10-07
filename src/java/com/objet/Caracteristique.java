/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tanguy
 */
@Entity
@Table(name = "caracteristique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracteristique.findAll", query = "SELECT c FROM Caracteristique c"),
    @NamedQuery(name = "Caracteristique.findByIdCar", query = "SELECT c FROM Caracteristique c WHERE c.idCar = :idCar"),
    @NamedQuery(name = "Caracteristique.findByCaracDesc", query = "SELECT c FROM Caracteristique c WHERE c.caracDesc = :caracDesc")})
public class Caracteristique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_car")
    private Integer idCar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "carac_desc")
    private String caracDesc;

    public Caracteristique() {
    }

    public Caracteristique(Integer idCar) {
        this.idCar = idCar;
    }

    public Caracteristique(Integer idCar, String caracDesc) {
        this.idCar = idCar;
        this.caracDesc = caracDesc;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getCaracDesc() {
        return caracDesc;
    }

    public void setCaracDesc(String caracDesc) {
        this.caracDesc = caracDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCar != null ? idCar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristique)) {
            return false;
        }
        Caracteristique other = (Caracteristique) object;
        if ((this.idCar == null && other.idCar != null) || (this.idCar != null && !this.idCar.equals(other.idCar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return caracDesc;
    }
    
}
