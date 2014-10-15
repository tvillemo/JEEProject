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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;


@ManagedBean(name = "BDDContact")
@SessionScoped
public class BDDContact implements Serializable {
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
        Artiste v = new Artiste();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psOeuvre = null;
        ResultSet rsOeuvre = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (select id_objet from liaison where id_car=1 and value=\"Artiste\"  order by id_objet");  //change
            rs= ps.executeQuery();
            int i=0;
            while(rs.next()){
                i++;
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
                    u.setSeller(getVendeurFromBDDWithID(rs.getInt("value")));
                }
                else if(carac.equals("acheteur")){
                   u.setBuyer(getAcheteurFromBDDWithID(rs.getInt("value")));
                }
                else if(carac.equals("expert")){
                    u.setExp(getExpertFromBDDWithID(rs.getInt("value")));
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
                else if(carac.equals("prix_vendu")){
                    u.setPrixVendu(rs.getFloat("value"));
                }
                else if(carac.equals("commission")){
                    u.setCommission(rs.getFloat("value"));
                }
                else if(carac.equals("date")){
                    u.setDate(rs.getDate("value"));
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
    
    public User connectUserFromBDDWithLoginAndPwd(String login,String pwd){
        User u=null;
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/galerieart", "root", "");
            ps= con.prepareStatement("SELECT id_objet, carac_desc, value FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'log' AND value = '"+login+"') and id_objet in (SELECT id_objet FROM liaison JOIN caracteristique ON liaison.id_car = caracteristique.id_car WHERE carac_desc = 'password' AND value = '"+pwd+"' order by id_objet) and carac_desc='type'");  //change
            rs= ps.executeQuery(); 
            while (rs.next()){
                String carac=rs.getString("value");
                if (carac.equals("vendeur")){
                    u=getVendeurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("directeur")){
                    u=getDirecteurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("acheteur")){
                    u=getAcheteurFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("expert")){
                    u=getExpertFromBDDWithID(rs.getInt("id_objet"));
                }
                else if (carac.equals("artiste")){
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
    
    
    
}
