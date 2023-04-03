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
public class UsuarioTO implements Serializable{
    private int id;
    private String idUsr;
    private String nomUsr;
    private String apePat;
    private String apeMat;
    private boolean indAct;
    private Date fecIng;
    private String emaUsr;
    private String pasUsr;
    private String pasUsr2;
    private String tipo;
    private int horasMax;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(String idUsr) {
        this.idUsr = idUsr;
    }

    public String getNomUsr() {
        return nomUsr;
    }

    public void setNomUsr(String nomUsr) {
        this.nomUsr = nomUsr;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public boolean getIndAct() {
        return indAct;
    }

    public void setIndAct(boolean indAct) {
        this.indAct = indAct;
    }

    public Date getFecIng() {
        return fecIng;
    }

    public void setFecIng(Date fecIng) {
        this.fecIng = fecIng;
    }

    public String getEmaUsr() {
        return emaUsr;
    }

    public void setEmaUsr(String emaUsr) {
        this.emaUsr = emaUsr;
    }

    public String getPasUsr() {
        return pasUsr;
    }

    public void setPasUsr(String pasUsr) {
        this.pasUsr = pasUsr;
    }

    public String getPasUsr2() {
        return pasUsr2;
    }

    public void setPasUsr2(String pasUsr2) {
        this.pasUsr2 = pasUsr2;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHorasMax() {
        return horasMax;
    }

    public void setHorasMax(int horasMax) {
        this.horasMax = horasMax;
    }

    @Override
    public String toString() {
        return "UsuarioTO{" + "id=" + id + ", idUsr=" + idUsr + ", nomUsr=" + nomUsr + ", apePat=" + apePat + ", apeMat=" + apeMat + ", indAct=" + indAct + ", fecIng=" + fecIng + ", emaUsr=" + emaUsr + ", pasUsr=" + pasUsr + ", tipo=" + tipo + ", horasMax=" + horasMax + '}';
    }
    
}
