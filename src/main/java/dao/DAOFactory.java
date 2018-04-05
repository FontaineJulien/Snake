package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import dao.ArticleDAO;
import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.UserDAO;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD    = "password";
    
    private static final int MIN_CONNECTION = 5;
    private static final int MAX_CONNECTION = 10;
    private static final int PARTITION_COUNT = 2;

    private BoneCP connectionPool = null;

    DAOFactory( BoneCP connectionPool ) {
        this.connectionPool = connectionPool;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        BoneCP connectionPool = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_USERNAME );
            motDePasse = properties.getProperty( PROPERTY_PASSWORD );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }
        
        try {
	        BoneCPConfig config = new BoneCPConfig();
	        config.setJdbcUrl(url);
	        config.setUsername(nomUtilisateur);
	        config.setPassword(motDePasse);
	        
	        config.setMinConnectionsPerPartition(MIN_CONNECTION);
	        config.setMaxConnectionsPerPartition(MAX_CONNECTION);
	        config.setPartitionCount(PARTITION_COUNT);
	        
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
		}
        

        DAOFactory instance = new DAOFactory(connectionPool);
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données package */ 
    Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO
     */
    public UserDAO getUserDAO() {
        return new UserDAO( this );
    }
    
    public ArticleDAO getArticleDAO() {
    	return new ArticleDAO( this );
    }
}

