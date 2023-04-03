/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u")
    , @NamedQuery(name = "UsuarioEntity.findById", query = "SELECT u FROM UsuarioEntity u WHERE u.id = :id")
    , @NamedQuery(name = "UsuarioEntity.findByIdUsr", query = "SELECT u FROM UsuarioEntity u WHERE u.idUsr = :idUsr")
    , @NamedQuery(name = "UsuarioEntity.findByNomUsr", query = "SELECT u FROM UsuarioEntity u WHERE u.nomUsr = :nomUsr")
    , @NamedQuery(name = "UsuarioEntity.findByApePat", query = "SELECT u FROM UsuarioEntity u WHERE u.apePat = :apePat")
    , @NamedQuery(name = "UsuarioEntity.findByApeMat", query = "SELECT u FROM UsuarioEntity u WHERE u.apeMat = :apeMat")
    , @NamedQuery(name = "UsuarioEntity.findByIndAct", query = "SELECT u FROM UsuarioEntity u WHERE u.indAct = :indAct")
    , @NamedQuery(name = "UsuarioEntity.findByFecIng", query = "SELECT u FROM UsuarioEntity u WHERE u.fecIng = :fecIng")
    , @NamedQuery(name = "UsuarioEntity.findByEmaUsr", query = "SELECT u FROM UsuarioEntity u WHERE u.emaUsr = :emaUsr")
    , @NamedQuery(name = "UsuarioEntity.findByPasUsr", query = "SELECT u FROM UsuarioEntity u WHERE u.pasUsr = :pasUsr")
    , @NamedQuery(name = "UsuarioEntity.findByTipo", query = "SELECT u FROM UsuarioEntity u WHERE u.tipo = :tipo")})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID_USR")
    private String idUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOM_USR")
    private String nomUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APE_PAT")
    private String apePat;
    @Size(max = 100)
    @Column(name = "APE_MAT")
    private String apeMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "IND_ACT")
    private String indAct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEC_ING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecIng;
    @Size(max = 50)
    @Column(name = "EMA_USR")
    private String emaUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PAS_USR")
    private String pasUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "HORAS_MAX")
    private Integer horasMax;


    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id) {
        this.id = id;
    }

    public UsuarioEntity(Integer id, String idUsr, String nomUsr, String apePat, String indAct, Date fecIng, String pasUsr, String tipo) {
        this.id = id;
        this.idUsr = idUsr;
        this.nomUsr = nomUsr;
        this.apePat = apePat;
        this.indAct = indAct;
        this.fecIng = fecIng;
        this.pasUsr = pasUsr;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getIndAct() {
        return indAct;
    }

    public void setIndAct(String indAct) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.UsuarioEntity[ id=" + id + " ]";
    }

    public Integer getHorasMax() {
        return horasMax;
    }

    public void setHorasMax(Integer horasMax) {
        this.horasMax = horasMax;
    }
    
}
