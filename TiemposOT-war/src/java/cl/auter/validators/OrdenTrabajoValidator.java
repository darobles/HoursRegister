/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.validators;

import cl.auter.backings.RegistroBacking;
import cl.auter.ejb.session.stateless.facade.OrdenTrabajoSessionBeanLocal;
import cl.auter.patron.to.OrdenTrabajoTO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;

/**
 *
 * @author drobles
 */
@ManagedBean
@RequestScoped
@FacesValidator("ordenTrabajoValidator")
public class OrdenTrabajoValidator extends RegistroBacking implements Validator {

    private OrdenTrabajoSessionBeanLocal getEjb() {
        try {
            //Servidor AUTER
            return (OrdenTrabajoSessionBeanLocal) new InitialContext().lookup("java:global/TiemposOT/TiemposOT-ejb/OrdenTrabajoSessionBean!cl.auter.ejb.session.stateless.facade.OrdenTrabajoSessionBeanLocal");
            //Servidor LOCAL
            //return (LocalizacionFacadeLocal) new InitialContext().lookup("java:global/Georeferencia-war/LocalizacionFacade!cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal");            
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedAndConvertedValue) throws ValidatorException {
        RegistroBacking reg = buscarBean("registroBacking");
        if (submittedAndConvertedValue == null) {
            reg.setDescOT("");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar un valor valido."));
        }
        int orden_trabajo = (Integer) submittedAndConvertedValue;

        if (orden_trabajo == 0) {
            reg.setDescOT("");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La OT ingresada no es valida."));
        }
        OrdenTrabajoTO orden = getEjb().findOTByNro(orden_trabajo);
        if (orden.getOtNumero() <= 0) {
            reg.setDescOT("");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La OT ingresada no existe."));
        }
        if (orden.getEstado_ot() != -3) {
            reg.setDescOT(orden.getOtMateria());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La OT se encuentra cerrada."));
        }

       /* if (orden.getContratocodigo() != null && orden.getContratocodigo().equals("CON-2979")) {
            reg.setDescOT("");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La OT ingresada se encuentra cerrada."));
        }*/
        reg.setDescOT(orden.getOtMateria());
    }

}
