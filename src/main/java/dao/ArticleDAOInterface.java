package dao;

import java.util.Vector;
import beans.Article;

public interface ArticleDAOInterface {
	Vector<Article> find()throws DAOException;
	Vector<Article> find(long id)throws DAOException;
	void create(Article a) throws DAOException;
	void delete(long id) throws DAOException;
}
