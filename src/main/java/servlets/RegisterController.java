package servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import metiers.RegisterForm;

/*
 * Servlet gérant l'inscription d'un utilisateur
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userdao;
	
	private static final String ATT_USER = "user";
	private static final String ATT_ERREURS = "erreurs";
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String PAGE_REGISTER = "/WEB-INF/register.jsp";
	
	private static final String URL_INDEX = "index";
	
	public void init() {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}
	
	/*
	 * Affichage du formulaire d'inscription
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( PAGE_REGISTER ).forward( request, response );
	}

	/*
	 * Gestion des informations saisies : si valide affichage page d'acceuil, sinon affichage formulaire d'inscription avec
	 * les messages d'erreurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegisterForm rf = new RegisterForm();
		User u = rf.verification(request,userdao);
		Map<String, String> erreurs = rf.getErreurs();
		
		request.setAttribute(ATT_USER, u);
		
		if(erreurs.isEmpty()) {
			
			response.sendRedirect(URL_INDEX);
			
		} else {
			
			request.setAttribute(ATT_ERREURS, erreurs);
			this.getServletContext().getRequestDispatcher( PAGE_REGISTER ).forward( request, response );
			
		}
		
	}

}

