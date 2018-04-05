package servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import metiers.LoginForm;

/*
 * Servlet gérant la connexion d'un utilisateur
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userdao;
	
	private static final String ATT_USER = "user";
	private static final String ATT_ERREURS = "erreurs";
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String PAGE_LOGIN = "/WEB-INF/login.jsp";
	
	private static final String URL_INDEX = "index";
	
	public void init( ) {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}
	
	/*
	 * Affichage du formulaire de connexion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher( PAGE_LOGIN ).forward( request, response );
	}

	/*
	 * Gestion des informations saisies : si valide affichage page d'acceuil en mode connecté sinon affichage pas login
	 * avec les messages d'erreurs 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginForm lf = new LoginForm();
		User user = lf.login(request, userdao);
		Map<String, String> erreurs = lf.getErreurs();
		
		user.getUsername();
		
		if(erreurs.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute(ATT_USER, user);
			response.sendRedirect(URL_INDEX);
		} else {
			request.setAttribute(ATT_USER, user);
			request.setAttribute(ATT_ERREURS, erreurs);
			this.getServletContext().getRequestDispatcher( PAGE_LOGIN ).forward( request, response );
		}
	}

}
