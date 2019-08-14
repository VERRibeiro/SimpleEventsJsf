package br.edu.ifpb.events.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import br.edu.ifpb.events.dao.EntityManagerProducer;

@WebListener
public class StartupListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(StartupListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		EntityManagerFactory emf = EntityManagerProducer.getEntityManagerFactory();
		if (emf != null) {
			emf.close();
			logger.info("Fábrica de EntityManagers fechada.");
		}else {
			System.out.println("CRIOU");
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		// Inicia a criação da fábrica de EntityManagers da JPA
		EntityManagerProducer.getEntityManagerFactory();
		logger.info("Fábrica de EntityManagers instanciada.");
	}

}
