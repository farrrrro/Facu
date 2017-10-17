package db;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import model.Post;
import model.User;

@Singleton
public class DataBase {

	public List<User> users = new ArrayList<>();
	public List<Post> posts = new ArrayList<>();
	
	public DataBase() {
		User user = new User("admin","admin","admin@admin");
		user.setId(nextUserId());
		users.add(user);
	}
	
	public int nextUserId(){
		return users.size();
	}
	
	public int nextPostId(){
		return posts.size();
	}
}
