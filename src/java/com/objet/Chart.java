package com.objet;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean
public class Chart implements Serializable {
 
    private MyPieChart pieModel1;
    private LineChartModel lineModel2;
    
    @PostConstruct
    public void init() {
        createLineModels();
        createPieModel1();
    }
 
    public MyPieChart getPieModel1() {
        return pieModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    private void createLineModels() {
        
         
        lineModel2=initCategoryModel();
        lineModel2.setTitle("CA par années");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Année"));
        lineModel2.getAxis(AxisType.Y).setLabel("M$");
        lineModel2.getAxis(AxisType.Y).setMin(0);
        lineModel2.getAxis(AxisType.Y).setMax(50);
    }
     
    private void createPieModel1() {
        pieModel1 = new MyPieChart();
         
        pieModel1.set("Vendeur 1", 540);
        pieModel1.set("Vendeur 2", 325);
        pieModel1.set("Vendeur 3", 702);
        pieModel1.set("Vendeur 4", 421);
        pieModel1.setShowDataLabels(true);
         
        pieModel1.setTitle("Partie de CA par vendeur");
        pieModel1.setLegendPosition("w");
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartModel mod=new LineChartModel();
        ChartSeries chiffres_ventes = new ChartSeries();
        ChartSeries chiffres_expertises = new ChartSeries();
        
        chiffres_expertises.setLabel("Expertise");
        chiffres_ventes.setLabel("Ventes");
        
        chiffres_expertises.set("2014",10);
        chiffres_expertises.set("2013",20);
        chiffres_expertises.set("2012",15);
        chiffres_expertises.set("2011",5);
        
        chiffres_ventes.set("2011",10);
        chiffres_ventes.set("2012",10);
        chiffres_ventes.set("2013",25);
        chiffres_ventes.set("2014",5);
        
        model.addSeries(chiffres_ventes);
        model.addSeries(chiffres_expertises);
         
        return model;
    }
 
}