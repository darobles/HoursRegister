/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backings;

import cl.auter.ejb.session.stateless.facade.TiemposSessionBeanLocal;
import cl.auter.patron.to.RegistroTiempoTO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author drobles
 */
@ManagedBean
@SessionScoped
public class RegistroBacking extends BaseBacking implements Serializable {

    @EJB
    TiemposSessionBeanLocal ejbTiempos;
    Date fechaHoy = new Date();
    RegistroTiempoTO registro = new RegistroTiempoTO();
    String descOT = "";
    String rendMsj = "false";
    double totalDia = 0;
    double totalSemana = 0;
    double totalMes = 0;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    
    @PostConstruct
    public void init() {
        try {           
            Date todayWithZeroTime = formatter.parse(formatter.format(fechaHoy));
            fechaHoy = todayWithZeroTime;
            registro.setTiempo(usurioAutenticado().getHorasMax());
            registro.setFecha(fechaHoy);
            actualizarEstadisticas();
            
        } catch (ParseException ex) {
            Logger.getLogger(RegistroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarEstadisticas() {
        try {
            Calendar currMonth = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 1);
            Calendar monday = Calendar.getInstance();
            monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            totalDia = ejbTiempos.getSpendTime(usurioAutenticado().getId(), fechaHoy);
            totalSemana = ejbTiempos.getSpendByRange(usurioAutenticado().getId(), formatter.parse(formatter.format(monday.getTime())),fechaHoy);
            totalMes = ejbTiempos.getSpendByRange(usurioAutenticado().getId(), currMonth.getTime(), fechaHoy);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ingresarOT() {
        registro.setIdUsuario(usurioAutenticado().getId());
        registro.setFechaIngreso(new Date());
        ejbTiempos.insertReg(registro);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Tiempo asignado a la OT: " + registro.getNroOt() + " ingresado correctamente."));
        //registro = new RegistroTiempoTO();
        //registro.setFecha(fechaHoy);
        registro.setNroOt(0);
        registro.setTiempo(usurioAutenticado().getHorasMax());
        descOT = "";
        actualizarEstadisticas();
        //return "";
       // return "/registro.jsf?faces-redirect=true";
    }

    public void startDateFilter() {
    }

    public RegistroTiempoTO getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroTiempoTO registro) {
        this.registro = registro;
    }


    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getDescOT() {
        return descOT;
    }

    public void setDescOT(String descOT) {
        this.descOT = descOT;
    }

    public String getRendMsj() {
        return rendMsj;
    }

    public void setRendMsj(String rendMsj) {
        this.rendMsj = rendMsj;
    }

    public double getTotalDia() {
        return totalDia;
    }

    public void setTotalDia(double totalDia) {
        this.totalDia = totalDia;
    }

    public double getTotalSemana() {
        return totalSemana;
    }

    public void setTotalSemana(double totalSemana) {
        this.totalSemana = totalSemana;
    }

    public double getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(double totalMes) {
        this.totalMes = totalMes;
    }

}
