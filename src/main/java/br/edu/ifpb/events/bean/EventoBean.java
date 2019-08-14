package br.edu.ifpb.events.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.events.dao.EventoDAO;
import br.edu.ifpb.events.entity.CandidatoVaga;
import br.edu.ifpb.events.entity.Evento;
import br.edu.ifpb.events.entity.Usuario;
import br.edu.ifpb.events.entity.Vaga;
import br.edu.ifpb.events.entity.VagaEvento;
import br.edu.ifpb.events.facade.CandidatoVagaController;
import br.edu.ifpb.events.facade.EventoController;
import br.edu.ifpb.events.facade.LoginController;
import br.edu.ifpb.events.facade.VagaController;
import br.edu.ifpb.events.facade.VagaEventoController;
import br.edu.ifpb.events.util.Status;

@Named(value = "eventoBean")
@ViewScoped
public class EventoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Evento evento;

	@Inject
	private EventoController eventoController;

	@Inject
	private LoginBean loginBean;
	
	@Inject
	private VagaController vagaController;

	@Inject
	private VagaEventoController vagaEventoController;
	
	@Inject
	private CandidatoVagaController candidatoVagaController;
	
	private List<Evento> eventos;
	private List<Vaga> vagas;
	private List<Usuario> candidatos;
	private List<CandidatoVaga> candidatosVagas;

	public String cadastrarEvento() {
		Evento e = new Evento();
		e.setTitulo(evento.getTitulo());
		e.setLocal(evento.getLocal());
		e.setDataHora(evento.getDataHora());
		e.setAdmin(loginBean.getUsuarioLogado());
		eventoController.cadastrar(e);
		for (Vaga vaga : vagas) {
			Vaga vag = vagaController.findById(vaga.getId());
			VagaEvento ve = new VagaEvento();
			ve.setEvento(e);
			ve.setVaga(vag);
			ve.setQtdeEvento(vaga.getQtdeVagas());
			vagaEventoController.cadastrar(ve);
		}

		return "evento/listar?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		eventos = eventoController.listar();	
		candidatos = new ArrayList<Usuario>();
		candidatosVagas = new ArrayList<CandidatoVaga>();
		Evento e = (Evento) this.getFlash("evento");		
		List<Vaga> ve = (List<Vaga>) this.getFlash("vagas");
		if (ve != null) {
			this.vagas = ve;
		} else {
			vagas = vagaController.listar();
		}
		
		if (e != null) {
			this.evento = e;		
			candidatosVagas = candidatoVagaController.getByEventoId(e.getId());
			for (CandidatoVaga cv : candidatoVagaController.getByEventoId(e.getId())) {
				if(cv.getUsuario() != null) {
					cv.getUsuario().setCargo(cv.getVaga().getVaga().getNome());
					candidatos.add(cv.getUsuario());					
				}
			}
		} else {
			this.evento = new Evento();
		}
	}

	public String editar(Evento e) {		
		int i = 0;
		for (VagaEvento ve : vagaEventoController.findByEventoId(e.getId())) {
			this.vagas.get(i++).setQtdeVagas(ve.getQtdeEvento());
		}		

		this.setFlash("vagas", this.vagas);
		this.setFlash("evento", e);
		return "/evento/cadastrar?faces-redirect=true";
	}
	
	public String detalhes(Evento e) {		
		int i = 0;
		for (VagaEvento ve : vagaEventoController.findByEventoId(e.getId())) {
			this.vagas.get(i++).setQtdeVagas(ve.getQtdeEvento());
		}		

		this.setFlash("vagas", this.vagas);
		this.setFlash("evento", e);
		return "/evento/detalhesEvento?faces-redirect=true";
	}
	
	public String candidatar(Evento e) {
		int i = 0;
		for (VagaEvento ve : vagaEventoController.findByEventoId(e.getId())) {
			this.vagas.get(i++).setQtdeVagas(ve.getQtdeEvento());
		}		

		this.setFlash("vagas", this.vagas);
		this.setFlash("evento", e);
		return "/evento/candidatar?faces-redirect=true";
	}
	
	public boolean usuarioCadastrado(Evento e) {
			
		for (CandidatoVaga cv : candidatosVagas) {
			if(cv.getUsuario().getEmail() == loginBean.getUsuarioLogado().getEmail() && e.getId() == cv.getVaga().getEvento().getId())
				return true;
		}
		return false;
	}
	public String finalizar(Evento e) {
		Evento eve = eventoController.getById(e.getId());
		eve.setFinalizado(true);
		eventoController.atualizar(eve);
		return "/evento/listar?faces-redirect=true";
	}
	
	public void candidatarUsuario(Vaga vaga, Evento e) {
		VagaEvento ve  = vagaEventoController.findByEventoVagaId(e.getId(),vaga.getId());
		CandidatoVaga cv = new CandidatoVaga();
		cv.setUsuario(loginBean.getUsuarioLogado());
		cv.setVaga(ve);
		cv.setStatus(Status.NEUTRO);
		ve.setQtdeEvento(ve.getQtdeEvento() - 1);
		for (Vaga v : vagas) {
			if(v.getId() == vaga.getId()) {
				v.setQtdeVagas(ve.getQtdeEvento());
			}
		}		
		candidatoVagaController.cadastrar(cv);
		vagaEventoController.atualizar(ve);
	}
	public Evento getEvento() {
		return evento;
	}
	
	public void aprovar(CandidatoVaga u) {
		u.setStatus(Status.APROVADO);
		candidatoVagaController.atualizar(u);
	}
	
	public void reprovar(CandidatoVaga u) {
		u.setStatus(Status.REPROVADO);
		candidatoVagaController.atualizar(u);
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public List<Usuario> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Usuario> candidatos) {
		this.candidatos = candidatos;
	}

	public List<CandidatoVaga> getCandidatosVagas() {
		return candidatosVagas;
	}

	public void setCandidatosVagas(List<CandidatoVaga> candidatosVagas) {
		this.candidatosVagas = candidatosVagas;
	}
	

}