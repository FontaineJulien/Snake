package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UserDAO;
import security.HashPassword;

/*
 * Objet métier gérant la vérification les informations de 
 * l'utilisateur suite à une création de compte.
 */
public class RegisterForm {
	
	// Permet de stocker les messages en cas d'informations invalides
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	// Expressions régulières
	private static final String REGEXP_EMAIL = "^[a-zA-Z]([a-zA-Z0-9_]|\\.)*@[a-z]*\\.[a-z]{2,3}$";
	private static final String REGEXP_NAME = "^[a-zA-Z][a-z]{2,30}$";
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_CONFIRM_PASSWORD = "confirm_password";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_PASSWORD = "password";
	private static final String ATT_USERNAME = "username";
	private static final String ATT_EMAIL = "email";
	
	public RegisterForm() {
		
	}
	
	public User verification(HttpServletRequest request, UserDAO userdao) {
		
		User user = new User();
		
		String username = getValueParameter(request, PARAM_USERNAME);
		String email = getValueParameter(request, PARAM_EMAIL);
		String password = getValueParameter(request, PARAM_PASSWORD);
		String confirm_password = getValueParameter(request, PARAM_CONFIRM_PASSWORD);
		
		
		try {
			verificationUsername(username);
		} catch(Exception e) {
			setErreur(ATT_USERNAME,e.getMessage());
		}
		user.setUsername(username);
		
		try {
			verificationEmail(email);
		} catch(Exception e) {
			setErreur(ATT_EMAIL,e.getMessage());
		}
		user.setEmail(email);
		
		try {
			verificationPassword(password, confirm_password);
		} catch(Exception e) {
			setErreur(ATT_PASSWORD,e.getMessage());
		}
		
		if(password != null && !password.isEmpty()) {
			String securePassword = HashPassword.get_SHA_512_SecurePassword(password);
			user.setPassword(securePassword);
		}
		
		user.setCredit(0);
		

		if(erreurs.isEmpty()) {
			userdao.create(user);
		}
		
		
		return user;
	}
	
	private void verificationUsername(String username) throws Exception {
		if(!username.matches(REGEXP_NAME))
			throw new Exception("Invalid username");
	}

	private void verificationEmail(String email) throws Exception {
		if(!email.matches(REGEXP_EMAIL))
			throw new Exception("Invalid email");
	}
	
	private void verificationPassword(String password, String confirm_password) throws Exception {
		if(password == null) {
			throw new Exception("Password is empty");
		} else {
			if(password.isEmpty()) {
				throw new Exception("Password is empty");
			} else {
				if(!password.equals(confirm_password))
					throw new Exception("Password does not match the confirmation password");
			}
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
