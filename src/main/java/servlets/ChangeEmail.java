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
import metiers.UpdateEmail;

@WebServlet("/changeemail")
public class ChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userdao;
	
	private static final String URL_PAGE_PROFIL = "profil";
	
	private static final String ATT_USER = "user";
	
	private static final String ATT_WRONG_EMAIL_USER = "wrong_email_user";
	private static final String ATT_WRONG_EMAIL_ERREURS = "wrong_email_erreurs";
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	public void init( ) {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UpdateEmail ue = new UpdateEmail();
		User u = ue.verify(request, userdao);
		
		Map<String, String> erreurs = ue.getErreurs();
		HttpSession session = request.getSession();
		
		if(!erreurs.isEmpty()) {
			
			session.setAttribute(ATT_WRONG_EMAIL_USER, u);
			session.setAttribute(ATT_WRONG_EMAIL_ERREURS, erreurs);
		} else {
			session.setAttribute(ATT_USER, u);
		}
		
		response.sendRedirect(URL_PAGE_PROFIL);
	}

}