package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import beans.Article;
import beans.User;

public class ArticleDAO implements ArticleDAOInterface {
	
	DAOFactory factory_dao;
	
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM Article a JOIN Player p ON p.idPlayer = a.fk_idPlayer ORDER BY PostDate DESC;";
	private static final String SELECT_ARTICLES_BY_ID = "SELECT * FROM Article a JOIN Player p ON p.idPlayer = a.fk_idPlayer WHERE a.fk_idPlayer=? ORDER BY PostDate DESC;";
	private static final String INSERT_ARTICLE = "INSERT INTO Article(fk_idPlayer,PostDate,Title,Content) VALUES (?,NOW(),?,?);";
	private static final String DELETE_BY_ID = "DELETE FROM Article WHERE idArticle=?;";
	
	public ArticleDAO(DAOFactory factory) {
		this.factory_dao = factory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.julfon.dao.ArticleDAOInterface#find()
	 * 
	 * Récupère la liste de tous les articles pour qu'ils soient
	 * affichés dans la page d'acceuil
	 * 
	 * A voir si on veut limiter le nombre que l'on récupère afin
	 * de ne pas surcharger la page d'acceuil
	 */
	
	public Vector<Article> find() throws DAOException {
		Vector<Article> articleList = new Vector<Article>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_ALL_ARTICLES, false);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				Article a = map(result);
				articleList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return articleList;
	}
	
	public Vector<Article> find(long id) throws DAOException {
		Vector<Article> articleList = new Vector<Article>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, SELECT_ARTICLES_BY_ID, false, id);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				Article a = map(result);
				articleList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement,result);
		}
		
		return articleList;
	}
	
	public void create(Article a) throws DAOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = factory_dao.getConnection();
			
			statement = DAOMisceleanous.initPreparedStatement(connection, INSERT_ARTICLE, false, a.getAuthor().getId(),a.getTitle(),a.getContent());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOMisceleanous.close(connection,statement);
		}
		
	}
	
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
	
	private Article map(ResultSet result) throws SQLException {
		Article a = new Article();
		User u = new User();
		
		u.setUsername(result.getString("username"));
		u.setEmail(result.getString("mailaddress"));
		u.setId(result.getLong("idplayer"));
		u.setPassword(result.getString("password"));
		u.setCredit(result.getShort("credit"));
		u.setIsAdmin(result.getShort("isadmin"));
		
		a.setAuthor(u);
		a.setId(result.getLong("idarticle"));
		a.setDate(result.getDate("postdate"));
		a.setTitle(result.getString("title"));
		a.setContent(result.getString("content"));
		
		return a;
	}

}
