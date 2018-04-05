package metiers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.isA;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import beans.User;
import dao.UserDAO;
import security.HashPassword;

public class RegisterFormTest {
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_CONFIRM_PASSWORD = "confirm_password";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_PASSWORD = "password";
	private static final String ATT_USERNAME = "username";
	private static final String ATT_EMAIL = "email";

    @Mock
    HttpServletRequest request;

    @Mock
    UserDAO userdao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doNothing().when(userdao).create(isA(User.class));
    }

    @Test
    public void testRegisterValidParameters() {
    	
    	String username = "Test";
    	String email = "test@test.tst";
    	String password = "password";
    	String confirm_password = "password";
    	
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_EMAIL)).thenReturn(email);
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn(password);
    	when(request.getParameter(PARAM_CONFIRM_PASSWORD)).thenReturn(confirm_password);
    	
    	RegisterForm rf = new RegisterForm();
    	User u = rf.verification(request, userdao);
    	
    	
    	assertTrue(rf.getErreurs().isEmpty());
    	assertEquals(u.getUsername(),username);
    	assertEquals(u.getEmail(),email);
    	assertEquals(u.getPassword(),HashPassword.get_SHA_512_SecurePassword(password));
    	assertEquals(u.getCredit(),0);
    	assertEquals(u.getIsAdmin(),0);
   	
    }
    
    @Test
    public void testRegisterEmptyParameters() {
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsKey(ATT_USERNAME));
    	assertTrue(rf.getErreurs().containsKey(ATT_PASSWORD));
    	assertTrue(rf.getErreurs().containsKey(ATT_EMAIL));

    }
    
    @Test
    public void testRegisterInvalidUsername() {
    	   
    	String username = "-username";
    	
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsKey(ATT_USERNAME));

    }
    
    @Test
    public void testRegisterInvalidEmail() {
    	   
    	String email = "test@test";
    	
    	when(request.getParameter(PARAM_EMAIL)).thenReturn(email);
    	
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsKey(ATT_EMAIL));

    }
    
    @Test
    public void testRegisterNullPassword() {
    	
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn(null);
    	
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsValue("Password is empty"));

    }
    
    @Test
    public void testRegisterEmptyPassword() {
    	
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn("");
    	
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsValue("Password is empty"));

    }
    
    @Test
    public void testRegisterUnmatchingPassword() {
    	
    	when(request.getParameter(PARAM_PASSWORD)).thenReturn("password");
    	when(request.getParameter(PARAM_CONFIRM_PASSWORD)).thenReturn("confirm_password");
    	
    	
    	RegisterForm rf = new RegisterForm();
    	rf.verification(request, userdao);
    	
    	assertTrue(rf.getErreurs().containsValue("Password does not match the confirmation password"));

    }

}