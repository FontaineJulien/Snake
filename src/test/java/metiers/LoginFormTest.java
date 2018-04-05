package metiers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import beans.User;
import dao.UserDAO;
import security.HashPassword;

public class LoginFormTest {
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_PASSWORD = "password";
	private static final String ATT_USERNAME = "username";

    @Mock
    HttpServletRequest request;

    @Mock
    UserDAO userdao;
    
    @Mock
	User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginUnknownUsername() {
    	
    	String username = "UnknownUser";

    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(userdao.find(username)).thenReturn(null);
    	
        LoginForm lf = new LoginForm();
        User u = lf.login(request, userdao);
        
        assertEquals(u.getUsername(),username);
        assertTrue(lf.getErreurs().containsKey(ATT_USERNAME));    

    }
    
    @Test
    public void testLoginInvalidPassword() {
    	
    	String username = "ValidUsername";
    	String password = "UnmatchingPassword";
    	String user_password = "Password";
    	
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn(password);
    	
    	when(userdao.find(username)).thenReturn(user);
    	
    	when(user.getPassword()).thenReturn(HashPassword.get_SHA_512_SecurePassword(user_password));
    	when(user.getUsername()).thenReturn(username);
    	
    	LoginForm lf = new LoginForm();
    	User u = lf.login(request, userdao);
    	
    	assertEquals(u.getUsername(),username);
    	assertTrue(lf.getErreurs().containsKey(ATT_PASSWORD));
    	
    }
    
    @Test
    public void testLoginValidCredentials() {
    	
    	String username = "ValidUsername";
    	String password = "ValidPassword";
    	String user_password = "ValidPassword";
    	
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn(password);
    	
    	when(userdao.find(username)).thenReturn(user);
    	
    	when(user.getPassword()).thenReturn(HashPassword.get_SHA_512_SecurePassword(user_password));
    	when(user.getUsername()).thenReturn(username);
    	
    	LoginForm lf = new LoginForm();
    	User u = lf.login(request, userdao);
    	
    	assertEquals(u.getUsername(),username);
    	assertTrue(lf.getErreurs().isEmpty());
    	
    }

}
