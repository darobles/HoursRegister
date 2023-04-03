/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backings;

import cl.auter.patron.to.UsuarioTO;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;

public class BaseBacking {

    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private DefaultStreamedContent download;

    /**
     * Busca un componente por el ID
     *
     * @param id
     * @return
     */
    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }

    /**
     * Busca un Componente por ID dentro de Otro Compoenente
     *
     * @param base
     * @param id
     * @return
     */
    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent kid = null;
        UIComponent result = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Metodo que permite recuperar un Bean que este dentro un ambito
     *
     * @param <T>
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T buscarBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
    
    public UsuarioTO usurioAutenticado() {
        LoginBacking loginBacking = buscarBean("loginBacking");
        return loginBacking.getUsuarioAutenticado();
    }

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }
    
    public void redirectIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Enlace/paginas/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BaseBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
