package br.edu.ifpb.events.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifpb.events.util.Status;

@Entity
@Table(name = "tb_candidato_vaga")
public class CandidatoVaga implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private VagaEvento vaga;
	
	@ManyToOne
	private Usuario usuario;
	
	private Status status;
	
	public CandidatoVaga() {};
	
	
	public CandidatoVaga(VagaEvento vaga, Usuario usuario) {
		super();
		this.vaga = vaga;
		this.usuario = usuario;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VagaEvento getVaga() {
		return vaga;
	}

	public void setVaga(VagaEvento vaga) {
		this.vaga = vaga;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
