package br.edu.ifpb.events.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.events.entity.Vaga;
import br.edu.ifpb.events.facade.VagaController;

@Named(value = "vagaBean")
@ViewScoped
public class VagaBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Vaga> vagas;
	
	private Vaga vaga;
	
	@Inject
	private VagaController vagaController;
	
	public String cadastrarVaga() {
		Vaga v = new Vaga();
		v.setNome(vaga.getNome());
		v.setDescricao(vaga.getDescricao());
		v.setQtdeVagas(0);
		vagaController.cadastrar(v);
		return "/vaga/listar?faces-redirect=true";
	}
	
	@PostConstruct
	public void init() {
		vagas = vagaController.listar();
		Vaga v = (Vaga) this.getFlash("vaga");
		if (v != null) {
			this.vaga = v;
		} else {
			this.vaga  = new Vaga();
		}
	}
	
	public String editar(Vaga vaga) {
		this.setFlash("vaga", vaga);
		return "/vaga/cadastro?faces-redirect=true";
	}
	
	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}