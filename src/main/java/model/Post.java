package model;

import java.util.Date;

public class Post {
	private int id;
	private User user;
	private Date dateTime;
	private String content;
	public Post(User user, String content) {
		super();
		this.user = user;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateime) {
		this.dateTime = dateime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
