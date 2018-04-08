package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;

/*
 * Servlet gérant la récupération d'un utilisateur afin d'afficher le formulaire
 * permettant de modifier ses informations
 */
@WebServlet("/formupdateuser")
public class FormUpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String PAGE_FORM_UPDATE_USER = "/WEB-INF/form_update_user.jsp";
	
	private static final String PARAM_ID_PLAYER = "idPlayer";
	
	private static final String ATT_USER = "user";
	
	private UserDAO userdao;
	
	public void init() {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Short id_player = Short.parseShort(request.getParameter(PARAM_ID_PLAYER));
		
		User u = userdao.find(id_player);
		
		request.setAttribute(ATT_USER, u);
		
		this.getServletContext().getRequestDispatcher(PAGE_FORM_UPDATE_USER).forward(request, response);
	}

}
