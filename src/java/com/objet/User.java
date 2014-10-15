/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

/**
 *
 * @author tanguy
 */
public abstract class User {
    protected String login;
    protected String pwd;
    protected String nom;
    protected String prenom;
    int id=-1;
    
    public User(){
    }
    
    public User(String log,String pass,String name,String first){
        login=log;
        pwd=pass;
        nom=name;
        prenom=first;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public void setLogin(String log){
        this.login=log;
    }
    public String getPwd(){
        return this.pwd;
    }
    
    public void setPwd(String pass){
        this.pwd=pass;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public void setNom(String name){
        this.nom=name;
    }
    
    public String getPrenom(){
        return this.prenom;
    }
    
    public void setPrenom(String first){
        this.prenom=first;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public abstract  String getRole();
}
