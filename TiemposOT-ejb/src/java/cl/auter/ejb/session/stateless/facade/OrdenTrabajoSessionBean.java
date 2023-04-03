/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.OrdenTrabajoDAO;
import cl.auter.ejb.dao.RegistroDAO;
import cl.auter.ejb.entity.bmp.OrdenTrabajoEntity;
import cl.auter.ejb.entity.bmp.RegistroTiemposEntity;
import cl.auter.patron.to.OrdenTrabajoTO;
import cl.auter.patron.to.RegistroTiempoTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class OrdenTrabajoSessionBean implements OrdenTrabajoSessionBeanLocal {

    @PersistenceContext(unitName = "OrdenTrabajo")
    private EntityManager entityManager;
    
    @Override
    public OrdenTrabajoTO findOTByNro(int nro){
        OrdenTrabajoDAO dao = new OrdenTrabajoDAO(OrdenTrabajoEntity.class,entityManager);
        return dao.buscarUsuarioPorId(nro);
    }
    

}
