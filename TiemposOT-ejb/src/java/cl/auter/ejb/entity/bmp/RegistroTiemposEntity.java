/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "registro_tiempos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroTiemposEntity.findAll", query = "SELECT r FROM RegistroTiemposEntity r")
    , @NamedQuery(name = "RegistroTiemposEntity.findById", query = "SELECT r FROM RegistroTiemposEntity r WHERE r.id = :id")
    , @NamedQuery(name = "RegistroTiemposEntity.findByIdUsuario", query = "SELECT r FROM RegistroTiemposEntity r WHERE r.idUsuario = :idUsuario order by r.id desc")
    , @NamedQuery(name = "RegistroTiemposEntity.findByNroOt", query = "SELECT r FROM RegistroTiemposEntity r WHERE r.nroOt = :nroOt")
    , @NamedQuery(name = "RegistroTiemposEntity.findByFecha", query = "SELECT r FROM RegistroTiemposEntity r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroTiemposEntity.findByTiempo", query = "SELECT r FROM RegistroTiemposEntity r WHERE r.tiempo = :tiempo")})
public class RegistroTiemposEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_ot")
    private int nroOt;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tiempo")
    private BigDecimal tiempo;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    public RegistroTiemposEntity() {
    }

    public RegistroTiemposEntity(Integer id) {
        this.id = id;
    }

    public RegistroTiemposEntity(Integer id, int idUsuario, int nroOt) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nroOt = nroOt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getTiempo() {
        return tiempo;
    }

    public void setTiempo(BigDecimal tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroTiemposEntity)) {
            return false;
        }
        RegistroTiemposEntity other = (RegistroTiemposEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.RegistroTiemposEntity[ id=" + id + " ]";
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}
