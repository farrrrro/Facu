package view;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.User;
import model.Post;

@Named
@SessionScoped
public class PostMb {
	@Inject
	private PostController postCntr;

	private User currentUser;
	private String content;
	
	public void generate(){
		Post post = new Post(currentUser, content);
		postCntr.generate(post);
	}
	
	public List<Post> obtain(){
		return postCntr.obtain(currentUser);
	}
	
	public List<Post> obtainAll(){
		return postCntr.obtainAll();
	}
}
