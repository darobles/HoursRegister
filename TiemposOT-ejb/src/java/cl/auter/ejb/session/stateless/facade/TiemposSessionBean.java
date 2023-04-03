/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.RegistroDAO;
import cl.auter.ejb.dao.UsuarioDAO;
import cl.auter.ejb.dao.VistaHorasUsadasDao;
import cl.auter.ejb.entity.bmp.RegistroTiemposEntity;
import cl.auter.ejb.entity.bmp.UsuarioEntity;
import cl.auter.ejb.entity.bmp.VistaHorasUsadasEntity;
import cl.auter.patron.to.RegistroTiempoTO;
import cl.auter.patron.to.UsuarioTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class TiemposSessionBean implements TiemposSessionBeanLocal {

    @PersistenceContext(unitName = "TiemposOT")
    private EntityManager entityManager;
    
    @Override
    public List<UsuarioTO> findAllUsers() {
        UsuarioDAO dao = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return dao.findAllUsers();
    }
    
    @Override
    public void insertReg(RegistroTiempoTO registro){
        RegistroDAO dao = new RegistroDAO(RegistroTiemposEntity.class,entityManager);
        dao.guardar(registro);
    }
    
    @Override
    public double getSpendTime(int id_usuario, Date fecha){
        VistaHorasUsadasDao dao = new VistaHorasUsadasDao(VistaHorasUsadasEntity.class,entityManager);
        return dao.buscarHorasUsuario(id_usuario,fecha);
    }
    
        
    @Override
    public double getSpendByRange(int id_usuario, Date fechaDesde, Date fechaHasta){
        VistaHorasUsadasDao dao = new VistaHorasUsadasDao(VistaHorasUsadasEntity.class,entityManager);
        return dao.buscarEntreFechas(id_usuario,fechaDesde,fechaHasta);
    }
    
    @Override
    public List<RegistroTiempoTO> getUserHistoric(int id_usuario){
        RegistroDAO dao = new RegistroDAO(RegistroTiemposEntity.class,entityManager);
        return dao.buscarPorUsuario(id_usuario);
    }
    
    @Override
    public void deleteHistoric(RegistroTiempoTO regSel){
        RegistroDAO dao = new RegistroDAO(RegistroTiemposEntity.class,entityManager);
        dao.eliminar(regSel);
    }
}
