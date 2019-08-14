package br.edu.ifpb.events.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.events.dao.CandidatoVagaDAO;
import br.edu.ifpb.events.dao.Transactional;
import br.edu.ifpb.events.entity.CandidatoVaga;
import br.edu.ifpb.events.entity.VagaEvento;

public class CandidatoVagaController  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CandidatoVagaDAO candidatoVagaDAO;
	
	@Transactional
	public CandidatoVaga cadastrar(CandidatoVaga candidatoVaga) {
		return candidatoVagaDAO.insert(candidatoVaga);
	}
	
	@Transactional
	public CandidatoVaga atualizar(CandidatoVaga candidatoVaga) {
		return candidatoVagaDAO.update(candidatoVaga);
	}
	
	public List<CandidatoVaga> listar() {
		return candidatoVagaDAO.findAll();
	}
	
	public List<CandidatoVaga> getByEventoId(int id){
		List<CandidatoVaga> lista = new ArrayList<CandidatoVaga>();
		for (CandidatoVaga cv : candidatoVagaDAO.findAll()) {
			if(cv.getVaga().getEvento().getId() == id)
				lista.add(cv);
		}
		return lista;	
	}
	
	public List<CandidatoVaga> getByUserId(int id){
		List<CandidatoVaga> lista = new ArrayList<CandidatoVaga>();
		System.out.println("AQUI");
		for (CandidatoVaga cv : candidatoVagaDAO.findAll()) {
			if(cv.getUsuario().getId() == id)
				lista.add(cv);
			System.out.println("AQUI" + cv.getUsuario());
		}
		return lista;	
	}
	
}
