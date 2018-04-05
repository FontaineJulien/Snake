package beans;

import java.util.Date;

public class Article {
	
	private long id;
	private User author;
	private Date date;
	private String title;
	private String content;
	
	public Article() {
		
	}
	
	public Article(User author, Date date, String title, String content) {
		this.setAuthor(author);
		this.setDate(date);
		this.setTitle(title);
		this.setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
