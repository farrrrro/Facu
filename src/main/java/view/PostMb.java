package view;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import view.AuthMb;
import controller.ImageController;
import model.Image;
import controller.PostController;
import model.Post;
import model.User;

@Named
public class PostMb {
	
	@Inject
	private PostController postCntr;
	
	@Inject
	private AuthMb authMb;

	@Inject 
	private ImageController imgCntr;
	
	private Part file;
	

	private User currentUser;
	
	@NotNull
    @Size(min=2,max=255)
	private String content;
	
	public void generate(){
		try{
			Image img = null;
			if(file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")){
				img = imgCntr.upload(file);
			}
			postCntr.generate(authMb.getCurrentUser(), content,img);
			content = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Post> obtain(){
		return postCntr.obtain(currentUser);
	}
	
	public List<Post> obtainAll(){
		return postCntr.obtainAll();
	}

	public PostController getPostCntr() {
		return postCntr;
	}

	public void setPostCntr(PostController postCntr) {
		this.postCntr = postCntr;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
}
