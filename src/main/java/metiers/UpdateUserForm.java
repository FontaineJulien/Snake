package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.User;

/*
 * Objet métier gérant la vérification les informations de 
 * l'utilisateur saisies suite à une modification de compte.
 */
public class UpdateUserForm {
	
	// Permet de stocker les messages en cas d'informations invalides
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	// Expressions régulières
	private static final String REGEXP_EMAIL = "^[a-zA-Z]([a-zA-Z0-9_]|\\.)*@[a-z]*\\.[a-z]{2,3}$";
	private static final String REGEXP_NAME = "^[A-Z][a-z]{2,30}$";
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_ID_PLAYER = "idPlayer";
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_IS_ADMIN = "isAdmin";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_USERNAME = "username";
	private static final String ATT_EMAIL = "email";
	
	public UpdateUserForm() {
		
	}
	
	public User verifyUpdate(HttpServletRequest request) {
		
		User u = new User();
		
		String email = getValueParameter(request,PARAM_EMAIL);
		String username = getValueParameter(request,PARAM_USERNAME);
		short isAdmin = getValueParameter(request,PARAM_IS_ADMIN) == null ? (short)0 : (short)1;
		long idPlayer = Long.parseLong(getValueParameter(request,PARAM_ID_PLAYER));
		
		u.setId(idPlayer);
		
		try {
			verificationUsername(username);
		} catch(Exception e) {
			setErreur(ATT_USERNAME, e.getMessage());
		}
		u.setUsername(username);
		
		try {
			verificationEmail(email);
		} catch(Exception e) {
			setErreur(ATT_EMAIL, e.getMessage());
		}
		u.setEmail(email);
		
		u.setIsAdmin(isAdmin);
		
		return u;
		
	}
	
	private void verificationUsername(String username) throws Exception {
		if(username == null) {
			throw new Exception("Empty username");
		} else {
		if(!username.matches(REGEXP_NAME))
			throw new Exception("Invalid username");
		}
	}

	private void verificationEmail(String email) throws Exception {
		if(email == null) {
			throw new Exception("Empty email");
		} else {
		if(!email.matches(REGEXP_EMAIL))
			throw new Exception("Invalid email");
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
