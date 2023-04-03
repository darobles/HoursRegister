/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backings;

import cl.auter.ejb.session.stateless.facade.LoginFacadeLocal;
import cl.auter.patron.to.UsuarioTO;
import cl.auter.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author drobles
 */
@ManagedBean
@ViewScoped
public class UsuarioBacking extends BaseBacking implements Serializable {

    @EJB
    LoginFacadeLocal ejbLogin;
    List<UsuarioTO> listaUsuarios = new ArrayList();
    UsuarioTO usuarioNuevo = new UsuarioTO();
    UsuarioTO usuarioSeleccionado = new UsuarioTO();
    String claveAnterior = "";
    boolean verPopup = false;
    boolean verPopupIngresar = false;
    boolean usuarioValido = false;

    @PostConstruct
    public void init() {
        listaUsuarios = ejbLogin.buscarUsuarioTodos();
    }

    public void eliminarUsuario() {
        ejbLogin.eliminarUsuario(usuarioSeleccionado);
        listaUsuarios = ejbLogin.buscarUsuarioTodos();
    }

    public void abrirEditarUsuario() {
        if(usuarioSeleccionado != null && usuarioSeleccionado.getPasUsr() != null)
        {
            claveAnterior = usuarioSeleccionado.getPasUsr();
            verPopup = true;
        }
        else{
            verPopup = false;
        }
    }
    public void cerrarDlgEdit(){
        claveAnterior = "";
        usuarioSeleccionado = null;
    }
    
    
    public void editarUsuario() {
        if (validarUsuario(usuarioSeleccionado, "form3:")) {
            if(!claveAnterior.equals(usuarioSeleccionado.getPasUsr()))
            {
                usuarioSeleccionado.setPasUsr(Util.getMD5(usuarioSeleccionado.getPasUsr()));
            }                
            ejbLogin.editarUsuario(usuarioSeleccionado);
            verPopup = false;
            claveAnterior = "";
            usuarioSeleccionado = null;
        }
        
    }

    public boolean validarUsuario(UsuarioTO usuarioTO, String form) {
          FacesContext context = FacesContext.getCurrentInstance();
        if (usuarioTO.getIdUsr().equals("") && !form.equals("form3:")) {
            context.addMessage(form + "idUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Usuario.", "Debe ingresar Usuario."));
            return false;
        }
        if (ejbLogin.existeUsuario(usuarioTO.getIdUsr()) && !form.equals("form3:")) {
            context.addMessage(form + "idUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre de usuario ya existe.", "El nombre de usuario ya existe."));
            return false;
        } 
        if (usuarioTO.getNomUsr().equals("")) {
            context.addMessage(form + "idNombre", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Nombre.", "Debe ingresar Nombre."));
            return false;
        }
        if (usuarioTO.getApePat().equals("")) {
            context.addMessage(form + "idApaterno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Apellido Paterno.", "Debe ingresar Apellido Paterno."));
            return false;
        }
        if (usuarioTO.getApeMat().equals("")) {
            context.addMessage(form + "idAmaterno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Apellido Materno.", "Debe ingresar Apellido Materno."));
            return false;
        }
        
        if (usuarioTO.getEmaUsr().equals("")) {
            context.addMessage(form + "idEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Email.", "Debe ingresar Email."));
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(usuarioTO.getEmaUsr());
        if (!matcher.matches()) {
            context.addMessage(form + "idEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Formato Email.", "Error de Formato Email."));
            return false;
        }
        
        if (usuarioTO.getHorasMax() <= 0) {
            context.addMessage(form + "idMaxHrs", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las horas de trabajo deben ser mayor a 0.", "Las horas de trabajo deben ser mayor a 0."));
            return false;
        }
         if (usuarioTO.getHorasMax() >= 24) {
            context.addMessage(form + "idMaxHrs", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las horas de trabajo no pueden exceder a 24 horas.", "Las horas de trabajo no pueden exceder a 24 horas."));
            return false;
        }
        
        if (usuarioTO.getPasUsr().equals("")) {
            context.addMessage(form + "idClave", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar Clave.", "Debe ingresar Clave."));
            return false;
        }
        if (usuarioTO.getPasUsr().length() < 2) {
            context.addMessage(form + "idClave", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clave debe tener una longitud minima de tres caracteres.", "La clave debe tener una longitud minima de tres caracteres."));
            return false;
        }
        if (usuarioTO.getPasUsr2().equals("")) {
            context.addMessage(form + "idClave2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar reingresar clave.", "Debe ingresar reingresar clave."));
            return false;
        }
        if (!usuarioTO.getPasUsr().equals(usuarioTO.getPasUsr2())) {
            context.addMessage(form + "idClave2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clave no coincide.", "La clave no coincide."));
            return false;
        }
        
        return true;
    }

    public void crearUsuario() {
        if (validarUsuario(usuarioNuevo, "form2:")) {
            usuarioNuevo.setPasUsr(Util.getMD5(usuarioNuevo.getPasUsr()));
            ejbLogin.guardarUsuario(usuarioNuevo);
            usuarioNuevo = new UsuarioTO();
            listaUsuarios = ejbLogin.buscarUsuarioTodos();
            verPopupIngresar = false;
        }
        
    }
    
    public void hacerUsuario() {
        usuarioNuevo.toString();
    }

    public void verCrearUsuario() {
        verPopupIngresar = true;
    }

    public List<UsuarioTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public UsuarioTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public boolean isVerPopupIngresar() {
        return verPopupIngresar;
    }

    public void setVerPopupIngresar(boolean verPopupIngresar) {
        this.verPopupIngresar = verPopupIngresar;
    }

    public UsuarioTO getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(UsuarioTO usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

}
