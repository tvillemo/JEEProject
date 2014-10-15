/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashMap;
import java.util.Map;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author tanguy
 */
public class MyPieChart extends PieChartModel implements MouseListener {

    int selectedID;
    
    public void setSelectedID(int id){
        selectedID=id;
    }
    
    public int getChartSelectedID() {
        System.err.println("get chart " + selectedID );
        return selectedID ;
    }
    public void mouseClick(Object me) {
        System.out.println(me);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(me.getClass());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("Yahahaha");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println("Yahahaha");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("Yahahaha");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("Yahahaha");
    }
    
}
