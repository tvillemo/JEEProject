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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tanguy
 */
@Entity
@Table(name = "objet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objet.findAll", query = "SELECT o FROM Objet o"),
    @NamedQuery(name = "Objet.findByIdObjet", query = "SELECT o FROM Objet o WHERE o.idObjet = :idObjet")})
public class Objet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_objet")
    private Integer idObjet;

    public Objet() {
    }

    public Objet(Integer idObjet) {
        this.idObjet = idObjet;
    }

    public Integer getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(Integer idObjet) {
        this.idObjet = idObjet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjet != null ? idObjet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objet)) {
            return false;
        }
        Objet other = (Objet) object;
        if ((this.idObjet == null && other.idObjet != null) || (this.idObjet != null && !this.idObjet.equals(other.idObjet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.objet.Objet[ idObjet=" + idObjet + " ]";
    }
    
}
