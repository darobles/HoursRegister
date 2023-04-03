/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.patron.to.OrdenTrabajoTO;
import javax.ejb.Local;

/**
 *
 * @author drobles
 */
@Local
public interface OrdenTrabajoSessionBeanLocal {
    
    public OrdenTrabajoTO findOTByNro(int nro);
    
}
