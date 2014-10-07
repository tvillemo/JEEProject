/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.util.Date;

/**
 *
 * @author tanguy
 */
public class Oeuvre {
    private Artiste artiste;
    private String rmq;
    private String type;
    private float prixEstime;
    private float prixVendu;
    private Vendeur seller;
    private Expert exp;
    private Acheteur buyer;
    private float commission;
    private int id;
    private Date date; // a compléter
 
    public Oeuvre(){};
    
    public Oeuvre(Artiste art, String obs,float estim,float prix,Vendeur vend,Expert expert,Acheteur ach,float comm,Date d){
        artiste=art;
        rmq=obs;
        prixEstime=estim;
        prixVendu=prix;
        seller=vend;
        exp=expert;
        buyer=ach;
        commission=comm;
        date=d;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public String getRmq() {
        return rmq;
    }

    public void setRmq(String rmq) {
        this.rmq = rmq;
    }

    public float getPrixEstime() {
        return prixEstime;
    }

    public void setPrixEstime(float prixEstime) {
        this.prixEstime = prixEstime;
    }

    public float getPrixVendu() {
        return prixVendu;
    }

    public void setPrixVendu(float prixVendu) {
        this.prixVendu = prixVendu;
    }

    public Vendeur getSeller() {
        return seller;
    }

    public void setSeller(Vendeur seller) {
        this.seller = seller;
    }

    public Expert getExp() {
        return exp;
    }

    public void setExp(Expert exp) {
        this.exp = exp;
    }

    public Acheteur getBuyer() {
        return buyer;
    }

    public void setBuyer(Acheteur buyer) {
        this.buyer = buyer;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setDate(Date d){
        this.date=d;
    }
    
    public Date getDate(){
        return this.date;
    }
}

