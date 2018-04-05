package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Article;
import beans.User;
import dao.ArticleDAO;

public class CreateArticleForm {
	
	// Permet de stocker les messages en cas d'informations invalides
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_TITLE = "title";
	private static final String PARAM_CONTENT = "content";
	private static final String ATT_SESSION_USER = "user";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_TITLE = "title";
	private static final String ATT_CONTENT = "content";
	
	public CreateArticleForm() {
		
	}
	
	/*
	 * Vérification des informations saisies par l'utilisateur
	 */
	public Article verification(HttpServletRequest request, ArticleDAO articledao) {
		
		Article a = new Article();
		
		User u = (User)request.getSession().getAttribute(ATT_SESSION_USER);
		
		String title = getValueParameter(request,PARAM_TITLE);
		String content = getValueParameter(request,PARAM_CONTENT);
		
		a.setAuthor(u);
		
		try {
			verificationTitle(title);
		} catch(Exception e) {
			setErreur(ATT_TITLE,e.getMessage());
		}
		a.setTitle(title);
		
		try {
			verificationContent(content);
		} catch(Exception e) {
			setErreur(ATT_CONTENT,e.getMessage());
		}
		a.setContent(content);
		
		if(erreurs.isEmpty()) {
			articledao.create(a);
		}
		
		return a;
	}
	
	private void verificationTitle(String title) throws Exception {
		if(title == null) {
			throw new Exception("Empty title");
		}
	}
	
	private void verificationContent(String content) throws Exception {
		if(content == null) {
			throw new Exception("Empty content");
		}
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	
	private void setErreur(String key, String message) {
		erreurs.put(key,message);
	}
	
	private String getValueParameter(HttpServletRequest request,String param) {
		String value = (String)request.getParameter(param);
		
		if ( value == null || value.trim().length() == 0 ) {
            return null;
        }
		
		return value;
	}

}
