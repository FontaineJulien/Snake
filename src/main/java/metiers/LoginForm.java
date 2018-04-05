package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UserDAO;
import security.HashPassword;

/*
 * Objet métier gérant la vérification du pseudo et du mot de passe de 
 * l'utilisateur suite à une tentative de connexion.
 */
public class LoginForm {
	
	// Permet de stocker les messages en cas d'informations invalides
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_PASSWORD = "password";
	private static final String ATT_USERNAME = "username";
	
	public LoginForm() {
		
	}
	
	/*
	 * Vérification des informations saisies par l'utilisateur
	 */
	public User login(HttpServletRequest request, UserDAO userdao) {
		
		String username = getValueParameter(request, PARAM_USERNAME);
		String password = getValueParameter(request, PARAM_PASSWORD);
		
		User u = userdao.find(username);
		
		if(u != null) {
			if(password != null) {
				String passwordHash = HashPassword.get_SHA_512_SecurePassword(password);
				if(!u.getPassword().equals(passwordHash)) {
					setErreur(ATT_PASSWORD, "Invalid password");
				}
			} else {
				setErreur(ATT_PASSWORD, "Invalid password");
			}
			
		} else {
			u = new User();
			u.setUsername(username);
			setErreur(ATT_USERNAME, "Unknown username");
		}
		
		return u;
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
