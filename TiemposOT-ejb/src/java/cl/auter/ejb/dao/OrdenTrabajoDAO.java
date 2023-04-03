/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.OrdenTrabajoEntity;
import cl.auter.patron.to.OrdenTrabajoTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class OrdenTrabajoDAO extends BaseDAO<OrdenTrabajoEntity>{

    public OrdenTrabajoDAO(Class<OrdenTrabajoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
        
    public List<OrdenTrabajoTO> findAllOT() {
        List<OrdenTrabajoTO> listaOT = new ArrayList();
        Query query = getEm().createNamedQuery("OrdenTrabajoEntity.findAll");       
        List<OrdenTrabajoEntity> listaOTEntity = query.getResultList();        
        for(OrdenTrabajoEntity usrEnt: listaOTEntity)
        {
            listaOT.add(traspasar(usrEnt));
        }
        return listaOT;
        
    }
    
    public OrdenTrabajoTO buscarUsuarioPorId(int nro) {
        Query query = getEm().createNamedQuery("OrdenTrabajoEntity.findByOtNumero");
        query.setParameter("otNumero", nro);
        List<OrdenTrabajoEntity> listaOtEntity = query.getResultList();  
        if(!listaOtEntity.isEmpty()){
            return traspasar(listaOtEntity.get(0));
        }
        return new OrdenTrabajoTO();
    }
     
    public OrdenTrabajoTO traspasar(OrdenTrabajoEntity otEntity){
        OrdenTrabajoTO ot = new OrdenTrabajoTO();

        ot.setOtIdinterno(otEntity.getOtIdinterno());
        ot.setOtMateria(otEntity.getOtMateria());
        ot.setClienteRut(otEntity.getClienteRut());
        ot.setClienteNombre(otEntity.getClienteNombre());
        ot.setContratocodigo(otEntity.getContratocodigo());
        ot.setOtNumero(otEntity.getOtNumero());
        ot.setOtPresupuesto(otEntity.getOtPresupuesto());
        ot.setOtComentarios(otEntity.getOtComentarios());
        ot.setOtEjecutor(otEntity.getOtEjecutor());
        ot.setDepartamento(otEntity.getDepartamento());      
        ot.setAdmContratoOt(otEntity.getAdmContratoOt());
        ot.setCompromiso(otEntity.getCompromiso());
        ot.setPlazo(otEntity.getPlazo());    
        ot.setEstado_ot(otEntity.getEstadoOt());
        return ot;
    }    
}
