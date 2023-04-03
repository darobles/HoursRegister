/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.patron.to.UsuarioTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author drobles
 */
@Local
public interface LoginFacadeLocal {
        
    public UsuarioTO buscarUsuarioPorId(String identificador);
    
    public List<UsuarioTO> buscarUsuarioTodos();
    
    public void editarUsuario(UsuarioTO usuarioTO);
    
    public void guardarUsuario(UsuarioTO usuarioTO);

    public void eliminarUsuario(UsuarioTO usuarioSeleccionado);

    public boolean existeUsuario(String idUsr);
}
