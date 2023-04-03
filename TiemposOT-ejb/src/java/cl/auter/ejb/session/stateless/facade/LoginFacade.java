/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.UsuarioDAO;
import cl.auter.ejb.entity.bmp.UsuarioEntity;
import cl.auter.patron.to.UsuarioTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class LoginFacade implements LoginFacadeLocal {
    @PersistenceContext(unitName = "TiemposOT")
    private EntityManager entityManager;


    @Override
    public UsuarioTO buscarUsuarioPorId(String identificador) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.buscarUsuarioPorId(identificador);
    }


    @Override
    public List<UsuarioTO> buscarUsuarioTodos(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.findAllUsers();
    }
    
    @Override
    public void editarUsuario(UsuarioTO usuarioTO){
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.editar(usuarioTO);
    }
    
    @Override
    public void guardarUsuario(UsuarioTO usuarioTO){
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.guardar(usuarioTO);
    }
    
    @Override
    public void eliminarUsuario(UsuarioTO usuarioSeleccionado){
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.eliminar(usuarioSeleccionado);    
    }
    
    @Override
    public boolean existeUsuario(String idUsr){
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        if(usuarioDAO.buscarUsuarioPorId(idUsr).getId() == 0)
            return false;
        else return true;
    }
}
