package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Classe utilitaire de méthodes pour les différentes DAO
 */
public final class DAOMisceleanous {
	
	private DAOMisceleanous() {
		
	}
	
	/*
	 * Méthode chargée de créer un PreparedStatement (requête SQL préparée) d'une DAO.
	 * Cette methode attend pour arguments : 
	 *  - la connexion à la BDD
	 *  - la requête SQL
	 *  - un booléen à "true" si on veut récupérer la clef générée, false sinon
	 *  - la liste des paramètres de la requête SQL DANS L'ORDRE
	 */
	public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		
		for(int i = 0; i < objects.length; i++) {
			statement.setObject(i+1, objects[i]);
		}
		
		return statement;
	}
	
	/*
	 * Méthodes chargées de fermer les différentes connexions ouvertes lors d'un appel à la BDD
	 */
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet result) {
		if(result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection connection, PreparedStatement statement) {
		close(connection);
		close(statement);
	}
	
	public static void close(Connection connection, PreparedStatement statement, ResultSet result) {
		close(connection);
		close(statement);
		close(result);
	}

}
