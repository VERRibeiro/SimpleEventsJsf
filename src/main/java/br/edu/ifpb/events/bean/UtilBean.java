//package br.edu.ifpb.events.bean;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import br.edu.ifpb.memoriam.entity.Operadora;
//import br.edu.ifpb.memoriam.facade.OperadoraController;
//
//@Named
//@ApplicationScoped
//public class UtilBean {
//	
//	private List<Operadora> operadoras;
//	
//	
//	@Inject
//	private OperadoraController operController;
//
//	@PostConstruct
//	private void init() {
//		operadoras = operController.consultar();
//	}
//
//	public List<Operadora> getOperadoras() {
//		return operadoras;
//	}
//
//	public void setOperadoras(List<Operadora> operadoras) {
//		this.operadoras = operadoras;
//	}
//
//}
