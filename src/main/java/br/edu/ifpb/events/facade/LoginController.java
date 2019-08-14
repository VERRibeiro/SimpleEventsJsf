package br.edu.ifpb.events.facade;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifpb.events.dao.Transactional;
import br.edu.ifpb.events.dao.UsuarioDAO;
import br.edu.ifpb.events.entity.Usuario;
import br.edu.ifpb.events.util.PasswordUtil;

public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario isValido(String usuario, String senha) {

		Usuario user = usuarioDAO.findByLogin(usuario);
		if (user == null || !senha.equals(user.getSenha())) {
			user = null;
		}

		return user;
	}
	
	@Transactional
	public Usuario cadastrar(Usuario usuario) {
		return usuarioDAO.insert(usuario);
	}
	
	@Transactional
	public Usuario atualizar(Usuario usuario) {
		return usuarioDAO.update(usuario);
	}
	
	public Usuario getById(int id) {
		return usuarioDAO.find(id);
	}
}
