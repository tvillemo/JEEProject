package com.objet;
 
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class Chart implements Serializable {
 
    private CartesianChartModel lineModel1;
    private LineChartModel lineModel2;
     
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public CartesianChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2=initCategoryModel();
        lineModel2.setTitle("CA par années");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Année"));
        lineModel2.getAxis(AxisType.Y).setLabel("M$");
        lineModel2.getAxis(AxisType.Y).setMin(0);
        lineModel2.getAxis(AxisType.Y).setMax(50);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
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