/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tanguy
 */

package com.objet;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;


@ManagedBean(name = "BDDContact")
@SessionScoped
public class BDDContact implements Serializable {
    private String login;
    private String pwd;
    
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getPwd() {
        return pwd;
    }
 
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
public List<Objet> getIDList()
{
    List<Objet> list = new ArrayList<Objet>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
        String sql = "select * from objet";
        ps= con.prepareStatement(sql); 
        rs= ps.executeQuery(); 
        while (rs.next())
        {
            Objet it = new Objet();
            it.setIdObjet(rs.getInt("id_objet"));
            list.add(it);
        } 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            con.close();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    return list;
}

public List<Caracteristique> getCaracteristiqueList()
{
    List<Caracteristique> list = new ArrayList<Caracteristique>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
        String sql = "select * from caracteristique";
        ps= con.prepareStatement(sql); 
        rs= ps.executeQuery(); 
        while (rs.next())
        {
            Caracteristique it = new Caracteristique();
            it.setIdCar(rs.getInt("id_car"));
            it.setCaracDesc(rs.getString("carac_desc"));
            list.add(it);

        } 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            con.close();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    return list;
}

public Liaison getObjLiaison(int idObject)
{
    Liaison li = new Liaison();
    PreparedStatement ps = null;
    PreparedStatement SD = null;
    Connection con = null;
    ResultSet rs = null;
    ResultSet rsDesc = null;
    Liaison it=new Liaison();
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
        String sql = "select * from objet join liaison on objet.id_objet=liaison.id_objet join caracteristique on caracteristique.id_car=liaison.id_car where objet.id_objet="+idObject;
        ps= con.prepareStatement(sql); 
        rs= ps.executeQuery();
        Objet o = new Objet(idObject);
        ArrayList<Caracteristique> caracList=new ArrayList<>();
        while (rs.next())
        {
            Caracteristique car=new Caracteristique(rs.getInt("id_car"),rs.getString("value"));
            caracList.add(car);
        }
        it=new Liaison(o,caracList);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            con.close();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    return it;
}

public static void insertInBDD(String query){
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
        ps= con.prepareStatement(query); 
        rs= ps.executeQuery(); 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            con.close();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
    
    public static void updateBDD(String query){
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
        ps= con.prepareStatement(query); 
        rs= ps.executeQuery(); 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            con.close();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } 
    }
    
     //TODO
    public static Vendeur getVendeurFromBDDWithID(int id){
        Vendeur v = new Vendeur();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psOeuvre = null;
        ResultSet rsOeuvre = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
            rs= ps.executeQuery();
            while(rs.next()){
                v.setRole("Vendeur");
                v.setId(rs.getInt("id_objet"));
                String carac=rs.getString("carac_desc");
                if (carac.equals("log")){
                    v.setLogin(rs.getString("value"));
                }   
                else if(carac.equals("password")){
                    v.setPwd(rs.getString("value"));
                }
                else if(carac.equals("nom")){
                    v.setNom(rs.getString("value"));
                }
                else if(carac.equals("prenom")){
                    v.setPrenom(rs.getString("value"));
                }
            }
            psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"type\" AND ( value = \"Peinture\" OR value = \"Sculpture\" ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'vendeur' AND value ='"+v.getNom()+"') group by id_objet");
            rsOeuvre= psOeuvre.executeQuery();
            while(rsOeuvre.next()){
                v.addOeuvreVendu(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } 
        return v;
    }
    
    public static ArrayList<Artiste> getArtisteFromBDD(){
        ArrayList<Artiste> a = new ArrayList<Artiste>();
        
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psOeuvre = null;
        ResultSet rsOeuvre = null;
        PreparedStatement psArtiste = null;
        ResultSet rsArtiste= null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (select id_objet from liaison where id_car=1 and value=\"Artiste\")  group by id_objet");  //change
            rs= ps.executeQuery();
            while(rs.next()){
                Artiste v = new Artiste();
                psArtiste=con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+rs.getInt("id_objet")+" order by id_objet");
                rsArtiste=psArtiste.executeQuery();
                while(rsArtiste.next()){
                v.setRole("Vendeur");
                v.setId(rsArtiste.getInt("id_objet"));
                String carac=rsArtiste.getString("carac_desc");
                if (carac.equals("log")){
                    v.setLogin(rsArtiste.getString("value"));
                }   
                else if(carac.equals("password")){
                    v.setPwd(rsArtiste.getString("value"));
                }
                else if(carac.equals("nom")){
                    v.setNom(rsArtiste.getString("value"));
                }
                else if(carac.equals("prenom")){
                    v.setPrenom(rsArtiste.getString("value"));
                }
                
                }
                a.add(v);
            }
            for (Artiste artisteActuel : a){
                psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"type\" AND ( value = \"Peinture\" OR value = \"Sculpture\" ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'Artiste' AND value ='"+artisteActuel.getNom()+"') group by id_objet");
                rsOeuvre= psOeuvre.executeQuery();
                while(rsOeuvre.next()){
                    artisteActuel.addOeuvre(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } 
        return a;
    }
    
    //todo
    public static Acheteur getAcheteurFromBDDWithID(int id){
       Acheteur a = new Acheteur();
       PreparedStatement ps = null;
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement psOeuvre = null;
       ResultSet rsOeuvre = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
           ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
           rs= ps.executeQuery();
           while(rs.next()){
               a.setRole("Acheteur");
               a.setId(rs.getInt("id_objet"));
               String carac=rs.getString("carac_desc");
               if (carac.equals("log")){
                   a.setLogin(rs.getString("value"));
               }   
               else if(carac.equals("password")){
                   a.setPwd(rs.getString("value"));
               }
               else if(carac.equals("nom")){
                   a.setNom(rs.getString("value"));
               }
               else if(carac.equals("prenom")){
                   a.setPrenom(rs.getString("value"));
               }
           }
           psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"type\" AND ( value = \"Peinture\" OR value = \"Sculpture\" ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'acheteur' AND value ='"+a.getNom()+"') group by id_objet");
           rsOeuvre= psOeuvre.executeQuery();
           while(rsOeuvre.next()){
               a.addOeuvreAchetee(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               con.close();
               ps.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return a;
    }
    
    public static List<Acheteur> getAcheteurFromBDD(){
       List<Acheteur> a = new ArrayList<Acheteur>();
       PreparedStatement ps = null;
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement psOeuvre = null;
       ResultSet rsOeuvre = null;
       PreparedStatement psAcheteur = null;
       ResultSet rsAcheteur = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
           psAcheteur= con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car where (liaison.id_car=1 and value='Acheteur') group by id_objet");  //change
           rsAcheteur= psAcheteur.executeQuery();
           while(rsAcheteur.next()){
               Acheteur v = new Acheteur();
               ps=con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car where id_objet="+rsAcheteur.getInt("id_objet")+" order by id_objet");
               rs=ps.executeQuery();
               while(rs.next()){
               v.setRole("Acheteur");
               v.setId(rs.getInt("id_objet"));
               String carac=rs.getString("carac_desc");
               if (carac.equals("log")){
                   v.setLogin(rs.getString("value"));
               }   
               else if(carac.equals("password")){
                   v.setPwd(rs.getString("value"));
               }
               else if(carac.equals("nom")){
                   v.setNom(rs.getString("value"));
               }
               else if(carac.equals("prenom")){
                   v.setPrenom(rs.getString("value"));
               }
               
               
           }
               a.add(v);
           }
           for(Acheteur currentAcheteur : a){
           psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"type\" AND ( value = \"Peinture\" OR value = \"Sculpture\" ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'acheteur' AND value ='"+currentAcheteur.getNom()+"') group by id_objet");
           rsOeuvre= psOeuvre.executeQuery();
           while(rsOeuvre.next()){
               currentAcheteur.addOeuvreAchetee(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
           }
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               con.close();
               ps.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return a;
    }
    
    public static Expert getExpertFromBDDWithID(int id){
        Expert exp = new Expert();
        PreparedStatement ps = null;
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement psOeuvre = null;
       ResultSet rsOeuvre = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
           ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
           rs= ps.executeQuery();
           while(rs.next()){
               exp.setRole("Expert");
               exp.setId(rs.getInt("id_objet"));
               String carac=rs.getString("carac_desc");
               if (carac.equals("log")){
                   exp.setLogin(rs.getString("value"));
               }   
               else if(carac.equals("password")){
                   exp.setPwd(rs.getString("value"));
               }
               else if(carac.equals("nom")){
                   exp.setNom(rs.getString("value"));
               }
               else if(carac.equals("prenom")){
                   exp.setPrenom(rs.getString("value"));
               }
           }
           psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'type' AND ( value = 'Peinture' OR value = 'Sculpture' ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'acheteur' AND value ='"+exp.getNom()+"') group by id_objet");
           rsOeuvre= psOeuvre.executeQuery();
           while(rsOeuvre.next()){
               exp.addOeuvreExpertisee(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               con.close();
               ps.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return exp;
    }
    
    public Directeur getDirecteurFromBDDWithID(int id){
        Directeur dir = new Directeur();
        PreparedStatement ps = null;
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement psOeuvre = null;
       ResultSet rsOeuvre = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
           ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
           rs= ps.executeQuery();
           while(rs.next()){
               dir.setRole("Directeur");
               dir.setId(rs.getInt("id_objet"));
               String carac=rs.getString("carac_desc");
               if (carac.equals("log")){
                   dir.setLogin(rs.getString("value"));
               }   
               else if(carac.equals("password")){
                   dir.setPwd(rs.getString("value"));
               }
               else if(carac.equals("nom")){
                   dir.setNom(rs.getString("value"));
               }
               else if(carac.equals("prenom")){
                   dir.setPrenom(rs.getString("value"));
               }
           }
           psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'type' AND value = 'vendeur' order by id_objet) group by id_objet");
           rsOeuvre= psOeuvre.executeQuery();
           while(rsOeuvre.next()){
               dir.addSeller(getVendeurFromBDDWithID(rsOeuvre.getInt("id_objet")));
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               con.close();
               ps.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return dir;
    }
    
    public static Artiste getArtisteFromBDDWithID(int id){
       Artiste art = new Artiste();
       PreparedStatement ps = null;
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement psOeuvre = null;
       ResultSet rsOeuvre = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
           ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
           rs= ps.executeQuery();
           while(rs.next()){
               art.setId(rs.getInt("id_objet"));
               art.setRole("Artiste");
               String carac=rs.getString("carac_desc");
               if (carac.equals("log")){
                   art.setLogin(rs.getString("value"));
               }   
               else if(carac.equals("password")){
                   art.setPwd(rs.getString("value"));
               }
               else if(carac.equals("nom")){
                   art.setNom(rs.getString("value"));
               }
               else if(carac.equals("prenom")){
                   art.setPrenom(rs.getString("value"));
               }
           }
           psOeuvre=con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'type' AND ( value = 'Peinture' OR value = 'Sculpture' ) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = \"prix_vente\" AND value IS NOT NULL )) AND id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'acheteur' AND value ='"+art.getNom()+"') group by id_objet");
           rsOeuvre= psOeuvre.executeQuery();
           while(rsOeuvre.next()){
               art.addOeuvre(getOeuvreFromBDDWithID(rsOeuvre.getInt("id_objet")));
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               con.close();
               ps.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       return art;
    }
    
    //TODO
    public static Oeuvre getOeuvreFromBDDWithID(int id){
        Oeuvre u=new Oeuvre();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psUser = null;
        ResultSet rsUser = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet="+id+" order by id_objet");  //change
            rs= ps.executeQuery(); 
            while(rs.next()){
                String carac=rs.getString("carac_desc");
                u.setId(rs.getInt("id_objet"));
                if (carac.equals("vendeur")){
                    psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='vendeur' group by id_objet)");
                    rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("acheteur")){
                   psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='acheteur' group by id_objet)");
                   rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("expert")){
                    psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='expert' group by id_objet)");
                    rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("type")){
                    u.setType(rs.getString("value"));
                }
                else if(carac.equals("remarque")){
                    u.setRmq(rs.getString("value"));
                }
                else if(carac.equals("prix_estime")){
                    u.setPrixEstime(rs.getFloat("value"));
                }
                else if(carac.equals("prix_vente")){
                    u.setPrixVendu(rs.getFloat("value"));
                }
                else if(carac.equals("commission")){
                    u.setCommission(rs.getFloat("value"));
                }
                else if(carac.equals("date")){
                    u.setDate(rs.getDate("value"));
                }
                 else if(carac.equals("nom")){
                    u.setNom(rs.getString("value"));
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } 
        
        return u;
    }
    
    public static List<Oeuvre> getOeuvreFromBDD(){
        List<Oeuvre> v=new ArrayList<Oeuvre>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psUser = null;
        ResultSet rsUser = null;
        PreparedStatement psOeuvre = null;
        ResultSet rsOeuvre = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car where liaison.id_car=1 and (value='Sculpture' or value='Peinture') group by id_objet");  //change
            rs= ps.executeQuery(); 
            while(rs.next()){
                Oeuvre u=new Oeuvre();
                psOeuvre=con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car where id_objet="+rs.getInt("id_objet") +" order by id_objet");
                rsOeuvre=psOeuvre.executeQuery();
                while(rsOeuvre.next()){
                    int id=rsOeuvre.getInt("id_objet");
                String carac=rsOeuvre.getString("carac_desc");
                u.setId(rsOeuvre.getInt("id_objet"));
                if (carac.equals("vendeur")){
                    psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='vendeur' group by id_objet)");
                    rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("acheteur")){
                   psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='acheteur' group by id_objet)");
                   rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("expert")){
                    psUser=con.prepareStatement("select id_objet from liaison where id_car=10 and value=(select value from liaison join caracteristique on liaison.id_car=caracteristique.id_car where id_objet="+id+" and carac_desc='expert' group by id_objet)");
                    rsUser=psUser.executeQuery();
                    if (rsUser.next()){
                        u.setSeller(getVendeurFromBDDWithID(rsUser.getInt("id_objet")));
                    }
                }
                else if(carac.equals("type")){
                    u.setType(rsOeuvre.getString("value"));
                }
                else if(carac.equals("remarque")){
                    u.setRmq(rsOeuvre.getString("value"));
                }
                else if(carac.equals("prix_estime")){
                    u.setPrixEstime(rsOeuvre.getFloat("value"));
                }
                else if(carac.equals("prix_vente")){
                    u.setPrixVendu(rsOeuvre.getFloat("value"));
                }
                else if(carac.equals("commission")){
                    u.setCommission(rsOeuvre.getFloat("value"));
                }
                else if(carac.equals("date")){
                    u.setDate(rsOeuvre.getDate("value"));
                }
                 else if(carac.equals("nom")){
                    u.setNom(rsOeuvre.getString("value"));
                }
            }
                v.add(u);
                System.out.print(v.size());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } 
        
        return v;
    }
    
    public User connectUserFromBDDWithLoginAndPwd(){
        User u=null;
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'log' AND value = '"+this.login+"') and id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'password' AND value = '"+this.pwd+"' order by id_objet) and carac_desc='type'");  //change
            rs= ps.executeQuery();
            while (rs.next()){
                String carac=rs.getString("value");
                if (carac.equals("Vendeur")){
                    u=getVendeurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("Directeur")){
                    u=getDirecteurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("Acheteur")){
                    u=getAcheteurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("Expert")){
                    u=getExpertFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("Artiste")){
                    u=getArtisteFromBDDWithID(rs.getInt("id_objet"));
                }
            } 
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return u;
    }
    
    public String allerALaPage() throws IOException{ 
     try {   
        User u = connectUserFromBDDWithLoginAndPwd();
        String role = null;
        
        if (u instanceof Directeur) {
            role = ((Directeur)u).getRole();
        }
        else if(u instanceof Vendeur){
            role = ((Vendeur)u).getRole();
        }
        else if(u instanceof Acheteur){
            role = ((Acheteur)u).getRole();
        }
        else if(u instanceof Artiste){
            role = ((Artiste)u).getRole();
        }
        else if(u instanceof Expert){
            role = ((Expert)u).getRole();
        }
        
        if (role.equals ("Directeur")) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession(false).invalidate(); 
            return "/Directeur";
        }
        else if (role.equals ("Vendeur")) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession(false).invalidate(); 
            return "/Vendeur";
        }
        else if (role.equals ("Expert")) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession(false).invalidate(); 
            return "/Expert";
        }
        else if (role.equals ("Artiste")) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession(false).invalidate(); 
            return "/artistes"; 
        }
     }
     catch(NullPointerException e) {
         System.err.println("Erreur connection");   
     }
       return "/index";
    }

}
