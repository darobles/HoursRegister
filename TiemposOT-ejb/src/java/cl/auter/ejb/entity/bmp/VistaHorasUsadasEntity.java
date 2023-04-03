/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "VISTA_HORAS_USADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaHorasUsadasEntity.findAll", query = "SELECT v FROM VistaHorasUsadasEntity v")
    , @NamedQuery(name = "VistaHorasUsadasEntity.findById", query = "SELECT v FROM VistaHorasUsadasEntity v WHERE v.id = :id")
    , @NamedQuery(name = "VistaHorasUsadasEntity.findByIdUsuarioFecha", query = "SELECT v FROM VistaHorasUsadasEntity v WHERE v.idUsuario = :idUsuario and v.fecha = :fecha")
    , @NamedQuery(name = "VistaHorasUsadasEntity.findByEntreFechas", query = "SELECT SUM(tiempoTotal) FROM VistaHorasUsadasEntity v WHERE v.idUsuario = :idUsuario and v.fecha >= :fechaDesde and v.fecha <= :fechaHasta")
        , @NamedQuery(name = "VistaHorasUsadasEntity.findByIdUsuario", query = "SELECT v FROM VistaHorasUsadasEntity v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "VistaHorasUsadasEntity.findByFecha", query = "SELECT v FROM VistaHorasUsadasEntity v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "VistaHorasUsadasEntity.findByTiempoTotal", query = "SELECT v FROM VistaHorasUsadasEntity v WHERE v.tiempoTotal = :tiempoTotal")})
public class VistaHorasUsadasEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tiempo_total")
    private BigDecimal tiempoTotal;

    public VistaHorasUsadasEntity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(BigDecimal tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }
    
}
