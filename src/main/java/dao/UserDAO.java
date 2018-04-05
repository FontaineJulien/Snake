package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import beans.User;

import dao.DAOMisceleanous;

public class UserDAO implements UserDAOInterface {
	
	protected DAOFactory factory_dao;
	
	private static final String SELECT_BY_USERNAME = "SELECT * FROM Player WHERE UserName=?;";
	private static final String SELECT_BY_ID = "SELECT * FROM Player WHERE idPlayer=?;";
	private static final String INSERT_USER = "INSERT INTO Player (UserName,PassWord,MailAddress,Credit,isAdmin) VALUES (?,?,?,?,?);";
	private static final String SELECT_ALL_USERS = "SELECT * FROM Player;";
	private static final String UPDATE_USER = "UPDATE Player SET UserName=?, MailAddress=?, isAdmin=? WHERE idPlayer=?";
	private static final String DELETE_BY_ID = "DELETE FROM Player WHERE idPlayer=?;";
	
	
	public UserDAO(DAOFactory factory_dao) {
		this.factory_dao = factory_dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#create(com.julfon.beans.User)
	 * 
	 * Création d'un joueur en BDD.
	 */
	
	public void create(User user) throws DAOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, INSERT_USER, false, user.getUsername(), user.getPassword(), user.getEmail(),user.getCredit(),user.getIsAdmin());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#find(java.lang.String)
	 * 
	 * Recherche d'un joueur en BDD par son pseudo.
	 * Rappel : Le pseudo d'un joueur est unique.
	 */
	
	public User find(String username) throws DAOException {
		
		User u = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_BY_USERNAME, false, username);
			
			result = statement.executeQuery();
			
			if(result.next()) {
				u = map(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return u;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#find(short)
	 * 
	 * Recherche d'un joueur en BDD par son ID de joueur
	 */
	
	public User find(short idPlayer) throws DAOException {
			
			User u = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				connection = factory_dao.getConnection();
				
				statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_BY_ID, false, idPlayer);
				
				result = statement.executeQuery();
				
				if(result.next()) {
					u = map(result);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAOMisceleanous.close(connection,statement,result);
			}
			
			return u;
		}
	
	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#find()
	 * 
	 * Récupération de liste complète des joueurs
	 * 
	 * A voir si on veut limiter le nombre que l'on récupère
	 */
	
	public Vector<User> find() throws DAOException {
		Vector<User> userList = new Vector<User>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_ALL_USERS, false);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				User u = map(result);
				userList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return userList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#update(long, java.lang.String, java.lang.String, short)
	 * 
	 * Modification des informations d'un joueur par un admin
	 */
	
	public void update(long idPlayer, String username, String email, short isAdmin) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, UPDATE_USER, false, username, email, isAdmin, idPlayer);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.UserDAOInterface#delete(long)
	 * 
	 * Suppression d'un joueur
	 */
	
	public void delete(long id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, DELETE_BY_ID, false, id);
			
			statement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}
	}
	
	private User map(ResultSet result) throws SQLException {
		User u = new User();
		
		u.setUsername(result.getString("username"));
		u.setEmail(result.getString("mailaddress"));
		u.setId(result.getLong("idplayer"));
		u.setPassword(result.getString("password"));
		u.setCredit(result.getShort("credit"));
		u.setIsAdmin(result.getShort("isadmin"));
		
		return u;
	
	}

}
