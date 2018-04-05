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
import metiers.UpdateUserForm;

@WebServlet("/updateuser")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String URL_USER_MANAGEMENT = "usermanagement";
	
	private static final String ATT_USER = "user";
	private static final String ATT_ERREURS = "erreurs";
	
	private static final String PAGE_UPDATE_USER = "/WEB-INF/form_update_user.jsp";
	
	
	private UserDAO userdao;
	
	public void init() {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UpdateUserForm uf = new UpdateUserForm();
		User user = uf.verifyUpdate(request);
		
		Map<String, String> erreurs = uf.getErreurs();
		
		if(erreurs.isEmpty()) {
			userdao.update(user.getId(), user.getUsername(), user.getEmail(), user.getIsAdmin());
			response.sendRedirect(URL_USER_MANAGEMENT);
		} else {
			request.setAttribute(ATT_USER, user);
			request.setAttribute(ATT_ERREURS, erreurs);
			this.getServletContext().getRequestDispatcher( PAGE_UPDATE_USER ).forward( request, response );
		}
		
		
	}

}
