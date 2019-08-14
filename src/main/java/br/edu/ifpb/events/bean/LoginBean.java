package br.edu.ifpb.events.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.events.entity.CandidatoVaga;
import br.edu.ifpb.events.entity.Evento;
import br.edu.ifpb.events.entity.Usuario;
import br.edu.ifpb.events.entity.Vaga;
import br.edu.ifpb.events.facade.CandidatoVagaController;
import br.edu.ifpb.events.facade.LoginController;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String nome;
	private String senha;
	private String telefone;
	private boolean admin;
	private boolean candidato;

	private Usuario usuarioLogado;

	@Inject
	private CandidatoVagaController candidatoVagaController;

	@Inject
	private LoginController loginController;
	private List<CandidatoVaga> candidatos;
	private List<Evento> eventosAdministrados;

	public String autenticar() {
		String proxView = null;
		if ((usuarioLogado = loginController.isValido(usuario, senha)) != null) {
			this.setValueOf("#{sessionScope.loginUser}", String.class, usuarioLogado.getEmail());
			nome = loginController.getById(usuarioLogado.getId()).getNome();
			telefone = loginController.getById(usuarioLogado.getId()).getTelefone();			
			proxView = "/evento/listar?faces-redirect=true";
		} else {
			this.addMessage(FacesMessage.SEVERITY_ERROR, "Login inv�lido.");
		}
		return proxView;
	}

	public String cadastrar() {
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setEmail(usuario);
		user.setSenha(senha);
		user.setTelefone(telefone);
		user.setAdmin(admin);
		user.setCandidato(candidato);
		loginController.cadastrar(user);
		return "/login/login";
	}

	public void atualizarUsuario(Usuario user) {
		user.setNome(nome);
		user.setEmail(usuario);
		user.setSenha(senha);
		user.setTelefone(telefone);
		loginController.atualizar(user);
	}

	public String logout() {
		this.invalidateSession();
		return "/login/login?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		candidatos = new ArrayList<CandidatoVaga>();
		System.out.println("ENTROU1");
		if (usuarioLogado != null) {
			System.out.println("ENTROU");
			candidatos = candidatoVagaController.getByUserId(usuarioLogado.getId());
		}
	}

	public String perfil() {
		candidatos = new ArrayList<CandidatoVaga>();
		eventosAdministrados = new ArrayList<Evento>();
		if (usuarioLogado != null) {
			candidatos = candidatoVagaController.getByUserId(usuarioLogado.getId());

			eventosAdministrados = loginController.getById(usuarioLogado.getId()).getEventosAdministrados();
			System.out.println("TENSO");
			for (Evento e : eventosAdministrados) {
				System.out.println("ALOU");
			}
		}
		return "/login/perfil?faces-redirect=true";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isCandidato() {
		return candidato;
	}

	public void setCandidato(boolean candidato) {
		this.candidato = candidato;
	}

	public List<CandidatoVaga> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoVaga> candidatos) {
		this.candidatos = candidatos;
	}

	public List<Evento> getEventosAdministrados() {
		return eventosAdministrados;
	}

	public void setEventosAdministrados(List<Evento> eventosAdministrados) {
		this.eventosAdministrados = eventosAdministrados;
	}

}
