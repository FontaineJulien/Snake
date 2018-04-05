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

public class UpdateUserFormTest {
	
	// Nom des paramètres reçus dans la requête
	private static final String PARAM_ID_PLAYER = "idPlayer";
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_IS_ADMIN = "isAdmin";
	
	// Nom des attributs (clefs) placés dans la map des messages d'erreur
	private static final String ATT_USERNAME = "username";
	private static final String ATT_EMAIL = "email";

    @Mock
    HttpServletRequest request;
    
    @Mock
	User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateUserEmptyParameters() {
    	
    	String id_player = "69";
    	
    	when(request.getParameter(PARAM_ID_PLAYER)).thenReturn(id_player);
    	
    	UpdateUserForm uf = new UpdateUserForm();
    	uf.verifyUpdate(request);
    	
    	assertTrue(uf.getErreurs().containsKey(ATT_EMAIL));
    	assertTrue(uf.getErreurs().containsKey(ATT_USERNAME));

    }
    
    @Test
    public void testUpdateUserValidParametersAdmin() {
    	
    	String id_player = "69";
    	String username = "Test";
    	String email = "test@test.tst";
    	String isAdmin = "1";
    	
    	when(request.getParameter(PARAM_EMAIL)).thenReturn(email);
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_IS_ADMIN)).thenReturn(isAdmin);
    	when(request.getParameter(PARAM_ID_PLAYER)).thenReturn(id_player);
    	
    	UpdateUserForm uf = new UpdateUserForm();
    	User u = uf.verifyUpdate(request);
    	
    	assertEquals(u.getUsername(),username);
    	assertEquals(u.getEmail(),email);
    	assertEquals(u.getIsAdmin(),1);
    	assertEquals(u.getId(),Long.parseLong(id_player));
    	assertTrue(uf.getErreurs().isEmpty()); 	

    }
    
    @Test
    public void testUpdateUserValidParametersNoAdmin() {
    	
    	String id_player = "69";
    	String username = "Test";
    	String email = "test@test.tst";
    	String isAdmin = null;
    	
    	when(request.getParameter(PARAM_EMAIL)).thenReturn(email);
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_IS_ADMIN)).thenReturn(isAdmin);
    	when(request.getParameter(PARAM_ID_PLAYER)).thenReturn(id_player);
    	
    	UpdateUserForm uf = new UpdateUserForm();
    	User u = uf.verifyUpdate(request);
    	
    	assertEquals(u.getUsername(),username);
    	assertEquals(u.getEmail(),email);
    	assertEquals(u.getIsAdmin(),0);
    	assertEquals(u.getId(),Long.parseLong(id_player));
    	assertTrue(uf.getErreurs().isEmpty()); 	

    }
    
    @Test
    public void testRegisterInvalidUsername() {
    	   
    	String username = "-username";
    	String id_player = "69";
    	
    	when(request.getParameter(PARAM_USERNAME)).thenReturn(username);
    	when(request.getParameter(PARAM_ID_PLAYER)).thenReturn(id_player);
    	
    	
    	UpdateUserForm uf = new UpdateUserForm();
    	uf.verifyUpdate(request);
    	
    	assertTrue(uf.getErreurs().containsKey(ATT_USERNAME));

    }
    
    @Test
    public void testRegisterInvalidEmail() {
    	   
    	String email = "test@test";
    	String id_player = "69";
    	
    	when(request.getParameter(PARAM_EMAIL)).thenReturn(email);
    	when(request.getParameter(PARAM_ID_PLAYER)).thenReturn(id_player);
    	
    	UpdateUserForm uf = new UpdateUserForm();
    	uf.verifyUpdate(request);
    	
    	assertTrue(uf.getErreurs().containsKey(ATT_EMAIL));

    }
    
}
