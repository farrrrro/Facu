package controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import db.DataBase;
import model.Post;
import model.User;

@Stateless
public class PostController {

	@Inject
	private DataBase db;

	public void generate(Post post) {
		Date date = new Date();
		post.setDateTime(date);
		post.setId(db.nextPostId());
		db.posts.add(post);
	}

	public List<Post> obtain(User user){
		List<Post> list = new ArrayList<>();
		for(Post post : db.posts){
			if (post.getUser().getUsername().equals(user.getUsername())) 
			{
				list.add(post);
			}
		}
		return list;
	}
	
	public List<Post> obtainAll(){
		List<Post> list = new ArrayList<>();
		for(Post post : db.posts){
			list.add(post);
		}
		return list;
	}

}
