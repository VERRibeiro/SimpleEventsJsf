package br.edu.ifpb.events.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.events.dao.EventoDAO;
import br.edu.ifpb.events.dao.Transactional;
import br.edu.ifpb.events.entity.Evento;

public class EventoController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoDAO eventoDAO;
	
	@Transactional
	public Evento cadastrar(Evento evento) {
		return eventoDAO.insert(evento);
	}
	
	@Transactional
	public Evento atualizar(Evento evento) {
		return eventoDAO.update(evento);
	}
	
	public List<Evento> listar() {
		return eventoDAO.findAll();
	}
	
	public Evento getById(int id) {
		return eventoDAO.find(id);
	}
}
