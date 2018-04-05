package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.UserDAO;

/*
 * Servlet gérant la suppression d'un utilisateur
 */
@WebServlet("/removeuser")
public class RemoveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String URL_USER_MANAGEMENT = "usermanagement";
	
	private UserDAO userdao;
	
	public void init() {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id_player = Long.parseLong(request.getParameter("idPlayer"));
		
		userdao.delete(id_player);
		
		response.sendRedirect(URL_USER_MANAGEMENT);

	}

}
