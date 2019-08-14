package br.edu.ifpb.events.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.events.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	public Usuario findByLogin(String login) {
		Query q = entityManager.createQuery("select u from Usuario u where u.email = :login");
		q.setParameter("login", login);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
		}catch (NoResultException e) {		
		}
		return u; 
	}
	
}
