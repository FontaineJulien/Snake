package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import dao.ArticleDAO;
import dao.DAOFactory;

/*
 * TODO : Formater la date d'affichage des articles
 * TODO : Gerer l'administration des articles
 * TODO : Gérer le blocage des pages admin par un non-admin (avec des filtres eventuellement)
 * TODO : Ecrire des TU
 * 
 * Servlet gérant la récupération des articles et l'affichage de la page d'acceuil
 */

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONF_DAO_FACTORY = "daofactory";
	
	private static final String ATT_LIST_ARTICLES = "listArticles";
	
	private static final String PAGE_INDEX = "/WEB-INF/index.jsp";
	
	public ArticleDAO dao_article;
	
	public void init() {
		dao_article = ((DAOFactory)this.getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Vector<Article> listArticles = dao_article.find();
		request.setAttribute(ATT_LIST_ARTICLES, listArticles);
		this.getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
	}

}
