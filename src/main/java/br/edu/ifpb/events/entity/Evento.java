package br.edu.ifpb.events.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_evento")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	private Date dataHora;
	private double avaliacao = 0;
	private String local;
	private boolean finalizado;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.MERGE, orphanRemoval = true)	
	private List<VagaEvento> vagas;
	
	@ManyToOne
	private Usuario admin;
	
	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public void addVaga(VagaEvento vaga) {		
		vagas.add(vaga);
	}
	
	public List<VagaEvento> getVagas() {
		return vagas;
	}

	public void setVagas(List<VagaEvento> vagas) {
		this.vagas = vagas;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}
	
	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}


	@Override
	public String toString() {
		return "Evento [id=" + id + ", titulo=" + titulo + ", dataHora=" + dataHora + ", local=" + local
				+ ", finalizado=" + finalizado + "]";
	}

}
