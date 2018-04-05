package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.ArticleDAO;

@WebServlet("/removearticle")
public class RemoveArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String URL_ARTICLE_MANAGEMENT = "formcreatearticle";
	
	private ArticleDAO articledao;
	
	public void init() {
		this.articledao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id_article = Long.parseLong(request.getParameter("idArticle"));
		
		articledao.delete(id_article);
		
		response.sendRedirect(URL_ARTICLE_MANAGEMENT);

	}

}
