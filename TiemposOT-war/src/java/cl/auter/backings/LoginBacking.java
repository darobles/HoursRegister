package cl.auter.backings;

import cl.auter.ejb.session.stateless.facade.LoginFacadeLocal;
import cl.auter.patron.to.UsuarioTO;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBacking extends BaseBacking implements Serializable {
    @EJB
    LoginFacadeLocal loginFacadeLocal;
    
    private static final long serialVersionUID = 1L;
    private String errorUsuario = "";
    private String errorClave = "";
    private String nombreUsuario = "";

    private UsuarioTO usuarioAutenticado;

    private String telefono;
    private String email;

    /**
     * Metodo una creado para realizar la carga inicial en backing
     */
    @PostConstruct
    public void carga() {
        this.errorUsuario = "";
        this.errorClave = "";
    }

    public UsuarioTO getUsuario() {
        if(usuarioAutenticado == null)
        {
            getUsuarioAutenticado();
        }
        return usuarioAutenticado;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "registro";
    }

    public UsuarioTO getUsuarioAutenticado() {
        if (usuarioAutenticado == null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String username = request.getRemoteUser();
            if (username != null) {
                try {
                    if (usuarioAutenticado == null) {
                        usuarioAutenticado = new UsuarioTO();
                        usuarioAutenticado = loginFacadeLocal.buscarUsuarioPorId(username.toUpperCase());
                    
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(UsuarioTO usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getErrorUsuario() {
        return errorUsuario;
    }

    public void setErrorUsuario(String errorUsuario) {
        this.errorUsuario = errorUsuario;
    }

    public String getErrorClave() {
        return errorClave;
    }

    public void setErrorClave(String errorClave) {
        this.errorClave = errorClave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
