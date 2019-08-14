package br.edu.ifpb.events.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.events.dao.Transactional;
import br.edu.ifpb.events.dao.VagaDAO;
import br.edu.ifpb.events.entity.Vaga;

public class VagaController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VagaDAO vagaDAO;
	
	@Transactional
	public Vaga cadastrar(Vaga vaga) {
		return vagaDAO.insert(vaga);
	}
	
	@Transactional
	public Vaga atualizar(Vaga vaga) {
		return vagaDAO.update(vaga);
	}
	public List<Vaga> listar() {
		return vagaDAO.findAll();
	}
	
	public Vaga findById(int id) {
		return vagaDAO.find(id);
	}
}
