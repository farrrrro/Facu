package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import model.Image;

@Entity
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
    @ManyToOne
	private User user;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@NotNull
    @Size(min=2,max=255)
	private String content;
	
    @ManyToOne
    	private Image image;
	
    @Size(min=2,max=255)
	private int likes;
	
    
    @Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", user=" + user + ", image=" + image + ", dateTime=" + dateTime
				+ "]";
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
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
