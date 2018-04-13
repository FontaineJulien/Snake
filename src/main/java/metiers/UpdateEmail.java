package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.UserDAO;

public class UpdateEmail {

	// Permet de stocker les messages en cas d'informations invalides
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	private static final String ATT_USER = "user";
	
	// Expressions régulières
	private static final String REGEXP_EMAIL = "^[a-zA-Z]([a-zA-Z0-9_]|\\.)*@[a-z]*\\.[a-z]{2,3}$";
	
	private static final String PARAM_EMAIL = "email";
	
	public UpdateEmail() {
		
	}
	
	public User verify(HttpServletRequest request, UserDAO userdao) {
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute(ATT_USER);
		
		String email = getValueParameter(request,PARAM_EMAIL);
		
		try {
			verificationEmail(email);
		} catch(Exception e) {
			setErreur(PARAM_EMAIL,e.getMessage());
		}
		
		u.setEmail(email);
		
		if(erreurs.isEmpty()) {
			userdao.update(u.getId(), u.getUsername(), u.getEmail(), u.getIsAdmin());
		}
		
		return u;
	}
	
	private void verificationEmail(String email) throws Exception {
		if(!email.matches(REGEXP_EMAIL))
			throw new Exception("Invalid email");
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
