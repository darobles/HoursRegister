/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.UsuarioEntity;
import cl.auter.patron.to.UsuarioTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class UsuarioDAO extends BaseDAO<UsuarioEntity> {

    public UsuarioDAO(Class<UsuarioEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void editar(UsuarioTO usuarioTO) {
        UsuarioEntity registroEntity = traspasar(usuarioTO);
        registroEntity.setId(usuarioTO.getId());
        edit(registroEntity);
    }

    public void guardar(UsuarioTO usuarioTO) {
        UsuarioEntity registroEntity = traspasar(usuarioTO);
        persist(registroEntity);
    }

    public void eliminar(UsuarioTO usuarioTO) {
        UsuarioEntity registroEntity = traspasar(usuarioTO);
        registroEntity.setId(usuarioTO.getId());
        remove(registroEntity);

    }

    public List<UsuarioTO> findAllUsers() {
        List<UsuarioTO> listaUsuarioTO = new ArrayList();
        Query query = getEm().createNamedQuery("UsuarioEntity.findAll");
        List<UsuarioEntity> listaUsuarioEntity = query.getResultList();
        for (UsuarioEntity usrEnt : listaUsuarioEntity) {
            listaUsuarioTO.add(traspasar(usrEnt));
        }
        return listaUsuarioTO;

    }

    public UsuarioTO buscarUsuarioPorId(String identificador) {
        Query query = getEm().createNamedQuery("UsuarioEntity.findByIdUsr");
        query.setParameter("idUsr", identificador);
        List<UsuarioEntity> listaUsuarioEntity = query.getResultList();
        if (!listaUsuarioEntity.isEmpty()) {
            return traspasar(listaUsuarioEntity.get(0));
        }
        return new UsuarioTO();
    }

    public UsuarioEntity traspasar(UsuarioTO usuarioTO) {
        UsuarioEntity userEntity = new UsuarioEntity();
        //userEntity.setId(usuarioTO.getId());
        userEntity.setIdUsr(usuarioTO.getIdUsr());
        userEntity.setNomUsr(usuarioTO.getNomUsr());
        userEntity.setApePat(usuarioTO.getApePat());
        userEntity.setApeMat(usuarioTO.getApeMat());
        if (usuarioTO.getIndAct()) {
            userEntity.setIndAct("SI");
        } else {
            userEntity.setIndAct("NO");
        }
        userEntity.setFecIng(new Date());
        userEntity.setEmaUsr(usuarioTO.getEmaUsr());
        userEntity.setPasUsr(usuarioTO.getPasUsr());
        userEntity.setTipo(usuarioTO.getTipo());
        userEntity.setHorasMax(usuarioTO.getHorasMax());
        return userEntity;
    }

    public UsuarioTO traspasar(UsuarioEntity usuarioEntity) {
        UsuarioTO userTO = new UsuarioTO();
        userTO.setId(usuarioEntity.getId());
        userTO.setIdUsr(usuarioEntity.getIdUsr());
        userTO.setNomUsr(usuarioEntity.getNomUsr());
        userTO.setApePat(usuarioEntity.getApePat());
        userTO.setApeMat(usuarioEntity.getApeMat());
        if (usuarioEntity.getIndAct().equals("SI")) {
            userTO.setIndAct(true);
        } else {
            userTO.setIndAct(false);
        }
        userTO.setFecIng(usuarioEntity.getFecIng());
        userTO.setEmaUsr(usuarioEntity.getEmaUsr());
        userTO.setPasUsr(usuarioEntity.getPasUsr());
        userTO.setTipo(usuarioEntity.getTipo());
        userTO.setPasUsr2(userTO.getPasUsr());
        userTO.setHorasMax(Optional.ofNullable(usuarioEntity.getHorasMax()).orElse(0));
        return userTO;
    }
}
