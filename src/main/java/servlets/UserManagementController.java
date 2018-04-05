package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;

@WebServlet("/usermanagement")
public class UserManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String PAGE_MANAGE_USERS = "/WEB-INF/admin_manage_users.jsp";
	
	private UserDAO userdao;
	
	public void init() {
		this.userdao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Vector<User> userList = userdao.find(); // Recuperation de la liste des utilisateurs
		
		request.setAttribute("userList", userList);
		this.getServletContext().getRequestDispatcher( PAGE_MANAGE_USERS ).forward( request, response );
	}

}
