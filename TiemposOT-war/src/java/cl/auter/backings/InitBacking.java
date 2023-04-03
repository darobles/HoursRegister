/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backings;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author drobles
 */
@ManagedBean
public class InitBacking extends BaseBacking implements Serializable {

    private PieChartModel pieModel1;

    @PostConstruct
    public void init() {
        createPie();
    }

    public void createPie() {

        pieModel1 = new PieChartModel();
        pieModel1.set("108580", 540);
        pieModel1.set("107580 2", 325);
        pieModel1.set("102380 3", 702);
        pieModel1.set("105110 4", 421);
        pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("Tiempo por O.T");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
        

    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
    

}
