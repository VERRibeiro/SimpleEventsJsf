package br.edu.ifpb.events.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vaga")
public class Vaga implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int qtdeVagas;
	
	
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<VagaEvento> vagaEvento;
	
	
	private String nome;
	
	private String descricao;
	
	public Vaga() {};
	public Vaga(int id, String nome, String descricao, int qtde) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdeVagas = qtde;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtdeVagas() {
		return qtdeVagas;
	}

	public void setQtdeVagas(int qtdeVagas) {
		this.qtdeVagas = qtdeVagas;
	}
	
	public void addVagaEvento(VagaEvento ve) {
		this.vagaEvento.add(ve);
	}
	
	public List<VagaEvento> getVagaEvento() {
		return vagaEvento;
	}
	public void setVagaEvento(List<VagaEvento> vagaEvento) {
		this.vagaEvento = vagaEvento;
	}
	@Override
	public String toString() {
		return "Vaga [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
