package servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import beans.User;
import dao.ArticleDAO;
import dao.DAOFactory;

/*
 * Servlet gérant l'affichage du formulaire de création d'un article
 */
@WebServlet("/formcreatearticle")
public class FormCreateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_FORM_CREATE_ARTICLE = "/WEB-INF/form_create_article.jsp";
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String ATT_LIST_ARTICLES = "listArticles";
	
	private static final String ATT_SESSION_USER = "user";
	
	public ArticleDAO dao_article;
	
	public void init() {
		dao_article = ((DAOFactory)this.getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = ((User)request.getSession().getAttribute(ATT_SESSION_USER)).getId();
		Vector<Article> listArticles = dao_article.find(id);
		request.setAttribute(ATT_LIST_ARTICLES, listArticles);
		this.getServletContext().getRequestDispatcher(PAGE_FORM_CREATE_ARTICLE).forward(request, response);
	}

}
