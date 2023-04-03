/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.validators;

import cl.auter.backings.BaseBacking;
import cl.auter.backings.RegistroBacking;
import cl.auter.ejb.session.stateless.facade.TiemposSessionBeanLocal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@FacesValidator("horasOTValidator")
public class HorasOTValidator extends BaseBacking implements Validator {
     
    private TiemposSessionBeanLocal getEjb() {
        try {
            return (TiemposSessionBeanLocal) new InitialContext().lookup("java:global/TiemposOT/TiemposOT-ejb/TiemposSessionBean!cl.auter.ejb.session.stateless.facade.TiemposSessionBeanLocal");
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedAndConvertedValue) throws ValidatorException {
        if (submittedAndConvertedValue == null ) {
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar un valor valido."));
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        double horasUtilizadas = 0;
        Date param = (Date) component.getAttributes().get("param"); 
        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(param));
            horasUtilizadas = getEjb().getSpendTime(usurioAutenticado().getId(), todayWithZeroTime);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        double horas = (double) submittedAndConvertedValue;
        //  sessionBean = new OrdenTrabajoSessionBean();
       // OrdenTrabajoTO orden = getEjb().findOTByNro(orden_trabajo);
        if (horas <= 0 ) {
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "","La cantidad de horas no puede ser menor o igual a 0"));
        }
        if (horas > usurioAutenticado().getHorasMax() ) {
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "","La cantidad de horas no puede ser mayor a " + usurioAutenticado().getHorasMax()));
        }
        if((horasUtilizadas + horas) > usurioAutenticado().getHorasMax())
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Se han utilizado " + horasUtilizadas + " de un total de " + usurioAutenticado().getHorasMax() + " horas disponibles. (Max permitido: " + (usurioAutenticado().getHorasMax() - horasUtilizadas) + ")" ));
        }
        
    }
    
}
