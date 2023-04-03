/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.RegistroTiemposEntity;
import cl.auter.patron.to.RegistroTiempoTO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class RegistroDAO extends BaseDAO<RegistroTiemposEntity> {

    public RegistroDAO(Class<RegistroTiemposEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void guardar(RegistroTiempoTO registroTO) {
        RegistroTiemposEntity registroEntity = traspasar(registroTO);
        persist(registroEntity);
    }
    
    public void eliminar(RegistroTiempoTO registroTO) {
        RegistroTiemposEntity estadoEntity = traspasar(registroTO);
        estadoEntity.setId(registroTO.getId());
        remove(estadoEntity);

    }

    public List<RegistroTiempoTO> buscarPorUsuario(int id) {        
        List<RegistroTiempoTO> listaOT = new ArrayList();
        Query query = getEm().createNamedQuery("RegistroTiemposEntity.findByIdUsuario");
        query.setParameter("idUsuario", id);       
        List<RegistroTiemposEntity> listaOTEntity = query.getResultList();        
        for(RegistroTiemposEntity usrEnt: listaOTEntity)
        {
            listaOT.add(traspasar(usrEnt));
        }
        return listaOT;
    }

    private RegistroTiemposEntity traspasar(RegistroTiempoTO registroTO) {
        RegistroTiemposEntity registroEntity = new RegistroTiemposEntity();
       // registroEntity.setId(registroTO.getId());
        registroEntity.setIdUsuario(registroTO.getIdUsuario());
        registroEntity.setNroOt(registroTO.getNroOt());
        registroEntity.setFecha(registroTO.getFecha());
        registroEntity.setTiempo(new BigDecimal(registroTO.getTiempo()));
        registroEntity.setFechaIngreso(registroTO.getFechaIngreso());
        return registroEntity;
    }

    private RegistroTiempoTO traspasar(RegistroTiemposEntity registroEntity) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        RegistroTiempoTO registroTO = new RegistroTiempoTO();
        registroTO.setId(registroEntity.getId());
        registroTO.setIdUsuario(registroEntity.getIdUsuario());
        registroTO.setNroOt(registroEntity.getNroOt());
        registroTO.setFecha(registroEntity.getFecha());
        registroTO.setTiempo(registroEntity.getTiempo().doubleValue());
        registroTO.setFechaIngreso(registroEntity.getFechaIngreso());
        registroTO.setFechaIngreso_str(sdf.format(registroTO.getFechaIngreso()));
        registroTO.setFecha_str(sdf.format(registroTO.getFecha()));
        return registroTO;
    }
}
