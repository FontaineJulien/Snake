package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebServlet("/profil")
public class ProfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_WRONG_USER = "wrong_user";
	private static final String ATT_USER = "user";
	private static final String ATT_ERREURS = "erreurs";
	private static final String PAGE_PROFIL = "/WEB-INF/profil.jsp";
	
	private static final String ATT_WRONG_EMAIL_USER = "wrong_email_user";
	private static final String ATT_WRONG_EMAIL_ERREURS = "wrong_email_erreurs";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User wu = null;
		User u = null;
		
		wu = (User)session.getAttribute(ATT_WRONG_EMAIL_USER);
		if(wu != null) {
			System.out.println("WRONG");
			@SuppressWarnings("unchecked")
			Map<String, String> erreurs = (HashMap<String, String>)session.getAttribute(ATT_WRONG_EMAIL_ERREURS);
			
			session.setAttribute(ATT_WRONG_EMAIL_ERREURS, null);
			session.setAttribute(ATT_WRONG_EMAIL_USER, null);
			
			request.setAttribute(ATT_WRONG_USER, wu);
			request.setAttribute(ATT_ERREURS, erreurs);
			
			u = (User)session.getAttribute(ATT_USER);
			System.out.println(u.getEmail());
			
			this.getServletContext().getRequestDispatcher( PAGE_PROFIL ).forward( request, response );
		} else {
		
			u = (User)session.getAttribute(ATT_USER);
			
			if(u == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				
				this.getServletContext().getRequestDispatcher( PAGE_PROFIL ).forward( request, response );
			}
		}
	}


}
