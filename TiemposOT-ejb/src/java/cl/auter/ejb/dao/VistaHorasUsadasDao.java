/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaHorasUsadasEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaHorasUsadasDao extends BaseDAO<VistaHorasUsadasEntity> {

    public VistaHorasUsadasDao(Class<VistaHorasUsadasEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public double buscarHorasUsuario(int id_usuario, Date fecha) {
        Query query = getEm().createNamedQuery("VistaHorasUsadasEntity.findByIdUsuarioFecha");
        query.setParameter("idUsuario", id_usuario);
        query.setParameter("fecha", fecha);
        List<VistaHorasUsadasEntity> listaUsuarioEntity = query.getResultList();
        if (!listaUsuarioEntity.isEmpty()) {
            return listaUsuarioEntity.get(0).getTiempoTotal().doubleValue();
        }
        return 0;
    }
    
    public double buscarEntreFechas(int id_usuario, Date fechaDesde, Date fechaHasta) {
        Query query = getEm().createNamedQuery("VistaHorasUsadasEntity.findByEntreFechas");
        query.setParameter("idUsuario", id_usuario);
        query.setParameter("fechaDesde", fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        List<BigDecimal> tiempos = query.getResultList();
        if (!tiempos.isEmpty() && tiempos.get(0) != null) {
            return tiempos.get(0).doubleValue();
        }
        return 0;
    }
}
