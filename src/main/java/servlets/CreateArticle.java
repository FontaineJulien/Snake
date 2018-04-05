package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleDAO;
import dao.DAOFactory;
import metiers.CreateArticleForm;

/**
 * Servlet implementation class CreateArticle
 */
@WebServlet("/createarticle")
public class CreateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleDAO articledao;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String URL_INDEX = "index";
	
	public void init( ) {
		this.articledao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CreateArticleForm af = new CreateArticleForm();
		af.verification(request, articledao);
		
		response.sendRedirect(URL_INDEX);	
	}

}
