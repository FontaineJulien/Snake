package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

@WebServlet("/profil")
public class ProfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_USER = "user";
	private static final String PAGE_PROFIL = "/WEB-INF/profil.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User u = (User)request.getSession().getAttribute(ATT_USER);
		
		if(u == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			this.getServletContext().getRequestDispatcher( PAGE_PROFIL ).forward( request, response );
		}
	}


}
