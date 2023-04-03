/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class RegistroTiempoTO implements Serializable{
    private int id;
    private int idUsuario;
    private int nroOt;
    private Date fecha;
    private double tiempo;
    private Date fechaIngreso;
    private String fecha_str;
    private String fechaIngreso_str;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNroOt() {
        return nroOt;
    }

    public void setNroOt(int nroOt) {
        this.nroOt = nroOt;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFecha_str() {
        return fecha_str;
    }

    public void setFecha_str(String fecha_str) {
        this.fecha_str = fecha_str;
    }

    public String getFechaIngreso_str() {
        return fechaIngreso_str;
    }

    public void setFechaIngreso_str(String fechaIngreso_str) {
        this.fechaIngreso_str = fechaIngreso_str;
    }

    @Override
    public String toString() {
        return "RegistroTiempoTO{" + "id=" + id + ", idUsuario=" + idUsuario + ", nroOt=" + nroOt + ", fecha=" + fecha + ", tiempo=" + tiempo + '}';
    }
    
}
