/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
 
@ManagedBean
public class mindmapView implements Serializable {
     
    private MindmapNode root;
     
    private MindmapNode selectedNode;

    private List<Acheteur> clientList=new ArrayList<Acheteur>();
    public mindmapView() {
        clientList=BDDContact.getAcheteurFromBDD();
        root = new DefaultMindmapNode("Clients", "Liste Client", "FFCC00", false);
         
        for (Acheteur a : clientList){
            root.addNode(new DefaultMindmapNode(a.nom+" "+a.prenom,""+a.id,"6e9ebf",true));
        }
    }
 
    public MindmapNode getRoot() {
        return root;
    }
 
    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            
            for (Acheteur a : clientList){
                if((a.nom+" "+a.prenom).equals(node.getLabel())){
                    for (Oeuvre o : a.getOeuvreAchetee()){
                        node.addNode(new DefaultMindmapNode(o.getNom(), ""+o.getId(), "82c542", true));
                    }
                }
            }
            
        }
    }
     
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();       
    }
}