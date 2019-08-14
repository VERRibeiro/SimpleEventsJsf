package br.edu.ifpb.events.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.events.dao.Transactional;
import br.edu.ifpb.events.dao.VagaEventoDAO;
import br.edu.ifpb.events.entity.VagaEvento;

public class VagaEventoController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VagaEventoDAO vagaDAO;
	
	@Transactional
	public VagaEvento cadastrar(VagaEvento vaga) {
		return vagaDAO.insert(vaga);
	}
	
	public List<VagaEvento> listar() {
		return vagaDAO.findAll();
	}
	
	public List<VagaEvento> findByEventoId(int id){
		List<VagaEvento> lista = new ArrayList<VagaEvento>();
		for (VagaEvento vagaEvento : vagaDAO.findAll()) {
			if(vagaEvento.getEvento().getId() == id)
				lista.add(vagaEvento);
		}
		return lista;		
	}
	
	public VagaEvento findByEventoVagaId(int evento, int vaga){
		for (VagaEvento vagaEvento : vagaDAO.findAll()) {
			if(vagaEvento.getEvento().getId() == evento && vagaEvento.getVaga().getId() == vaga)
				return vagaEvento;
		}
		return null;		
	}

	@Transactional
	public VagaEvento atualizar(VagaEvento ve) {
		return vagaDAO.update(ve);
	}
}
