package config;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.DAOConfigurationException;
import dao.DAOFactory;

/*
 * Instanciation de la DAO factory au lancement de l'application.
 * Elle est placée en attribut dans le ServletContext pour être accessible
 * depuis les Servlets
 */
public class InitialisationDAOFactory implements ServletContextListener {
	
	private DAOFactory daofactory;
	
	private static final String ATT_DAO_FACTORY = "daofactory";

	public void contextDestroyed(ServletContextEvent arg0) {

		
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		
		try {
			this.daofactory = DAOFactory.getInstance();
		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}
		
		servletContext.setAttribute(ATT_DAO_FACTORY, daofactory);
		
	}


}
