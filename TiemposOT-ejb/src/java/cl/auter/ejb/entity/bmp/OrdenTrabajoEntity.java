/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "ORDEN_TRABAJO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenTrabajoEntity.findAll", query = "SELECT o FROM OrdenTrabajoEntity o")
    , @NamedQuery(name = "OrdenTrabajoEntity.findByOtIdinterno", query = "SELECT o FROM OrdenTrabajoEntity o WHERE o.otIdinterno = :otIdinterno")
    , @NamedQuery(name = "OrdenTrabajoEntity.findByOtNumero", query = "SELECT o FROM OrdenTrabajoEntity o WHERE o.otNumero = :otNumero")
    , @NamedQuery(name = "OrdenTrabajoEntity.findByOtPresupuesto", query = "SELECT o FROM OrdenTrabajoEntity o WHERE o.otPresupuesto = :otPresupuesto")})
public class OrdenTrabajoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ot_idinterno")
    private Integer otIdinterno;
    @Lob
    @Size(max = 65535)
    @Column(name = "ot_materia")
    private String otMateria;
    @Lob
    @Size(max = 65535)
    @Column(name = "cliente_rut")
    private String clienteRut;
    @Lob
    @Size(max = 65535)
    @Column(name = "cliente_nombre")
    private String clienteNombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "contratocodigo")
    private String contratocodigo;
    @Column(name = "ot_numero")
    private Integer otNumero;
    @Column(name = "ot_presupuesto")
    private Integer otPresupuesto;
    @Lob
    @Size(max = 65535)
    @Column(name = "ot_comentarios")
    private String otComentarios;
    @Lob
    @Size(max = 65535)
    @Column(name = "ot_ejecutor")
    private String otEjecutor;
    @Lob
    @Size(max = 65535)
    @Column(name = "departamento")
    private String departamento;
    @Lob
    @Size(max = 65535)
    @Column(name = "adm_contrato_ot")
    private String admContratoOt;
    @Lob
    @Size(max = 65535)
    @Column(name = "compromiso")
    private String compromiso;
    @Lob
    @Size(max = 65535)
    @Column(name = "plazo")
    private String plazo;
    @Column(name = "estado_ot")
    private Integer estadoOt;

    public OrdenTrabajoEntity() {
    }

    public OrdenTrabajoEntity(Integer otIdinterno) {
        this.otIdinterno = otIdinterno;
    }

    public Integer getOtIdinterno() {
        return otIdinterno;
    }

    public void setOtIdinterno(Integer otIdinterno) {
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

    public Integer getOtNumero() {
        return otNumero;
    }

    public void setOtNumero(Integer otNumero) {
        this.otNumero = otNumero;
    }

    public Integer getOtPresupuesto() {
        return otPresupuesto;
    }

    public void setOtPresupuesto(Integer otPresupuesto) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otIdinterno != null ? otIdinterno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenTrabajoEntity)) {
            return false;
        }
        OrdenTrabajoEntity other = (OrdenTrabajoEntity) object;
        if ((this.otIdinterno == null && other.otIdinterno != null) || (this.otIdinterno != null && !this.otIdinterno.equals(other.otIdinterno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.OrdenTrabajoEntity[ otIdinterno=" + otIdinterno + " ]";
    }

    public Integer getEstadoOt() {
        return estadoOt;
    }

    public void setEstadoOt(Integer estadoOt) {
        this.estadoOt = estadoOt;
    }
    
}
