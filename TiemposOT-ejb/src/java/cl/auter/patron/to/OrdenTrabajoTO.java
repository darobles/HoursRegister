/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

/**
 *
 * @author drobles
 */
public class OrdenTrabajoTO {
    private int otIdinterno;
    private String otMateria;
    private String clienteRut;
    private String clienteNombre;
    private String contratocodigo;
    private int otNumero;
    private int otPresupuesto;
    private String otComentarios;
    private String otEjecutor;
    private String departamento;
    private String admContratoOt;
    private String compromiso;
    private String plazo;
    private int estado_ot;

    public int getOtIdinterno() {
        return otIdinterno;
    }

    public void setOtIdinterno(int otIdinterno) {
        this.otIdinterno = otIdinterno;
    }

    public String getOtMateria() {
        return otMateria;
    }

    public void setOtMateria(String otMateria) {
        this.otMateria = otMateria;
    }

    public String getClienteRut() {
        return clienteRut;
    }

    public void setClienteRut(String clienteRut) {
        this.clienteRut = clienteRut;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getContratocodigo() {
        return contratocodigo;
    }

    public void setContratocodigo(String contratocodigo) {
        this.contratocodigo = contratocodigo;
    }

    public int getOtNumero() {
        return otNumero;
    }

    public void setOtNumero(int otNumero) {
        this.otNumero = otNumero;
    }

    public int getOtPresupuesto() {
        return otPresupuesto;
    }

    public void setOtPresupuesto(int otPresupuesto) {
        this.otPresupuesto = otPresupuesto;
    }

    public String getOtComentarios() {
        return otComentarios;
    }

    public void setOtComentarios(String otComentarios) {
        this.otComentarios = otComentarios;
    }

    public String getOtEjecutor() {
        return otEjecutor;
    }

    public void setOtEjecutor(String otEjecutor) {
        this.otEjecutor = otEjecutor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getAdmContratoOt() {
        return admContratoOt;
    }

    public void setAdmContratoOt(String admContratoOt) {
        this.admContratoOt = admContratoOt;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public int getEstado_ot() {
        return estado_ot;
    }

    public void setEstado_ot(int estado_ot) {
        this.estado_ot = estado_ot;
    }
    
    @Override
    public String toString() {
        return "OrdenTrabajoTO{" + "otIdinterno=" + otIdinterno + ", otMateria=" + otMateria + ", clienteRut=" + clienteRut + ", clienteNombre=" + clienteNombre + ", contratocodigo=" + contratocodigo + ", otNumero=" + otNumero + ", otPresupuesto=" + otPresupuesto + ", otComentarios=" + otComentarios + ", otEjecutor=" + otEjecutor + ", departamento=" + departamento + ", admContratoOt=" + admContratoOt + ", compromiso=" + compromiso + ", plazo=" + plazo + '}';
    }
    
}
