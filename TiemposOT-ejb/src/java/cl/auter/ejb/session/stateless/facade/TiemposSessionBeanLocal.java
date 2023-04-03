/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.patron.to.RegistroTiempoTO;
import cl.auter.patron.to.UsuarioTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author drobles
 */
@Local
public interface TiemposSessionBeanLocal {

    public List<UsuarioTO> findAllUsers();
    
    public void insertReg(RegistroTiempoTO registro);
    
    public double getSpendTime(int id_usuario, Date fecha);
    
    public double getSpendByRange(int id_usuario, Date fechaDesde, Date fechaHasta);
    
    public List<RegistroTiempoTO> getUserHistoric(int id_usuario);

    public void deleteHistoric(RegistroTiempoTO regSel);
}
