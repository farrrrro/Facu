package view;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import view.FollowerMb;
import view.AuthMb;
import controller.ImageController;
import controller.PostController;
import controller.LikeController;
import model.Image;
import model.Post;
import model.User;

@Named
@MultipartConfig(location="/tmp",
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,
maxRequestSize=1024*1024*5*5)
public class PostMb {
	
	@Inject
	private PostController postCtrl;
	
	@Inject
	private AuthMb authMb;

	@Inject 
	private ImageController imgCtrl;
	
	@Inject
	private FollowerMb follower;

	@Inject
	private LikeController likeCtrl;
	
	private Part file;
	

	private User currentUser;
	
	@NotNull
    @Size(min=2,max=255)
	private String content;
	
	public void generate(){
		try {
			Image img = null;
			if (file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")) {
				img = imgCtrl.upload(file);
			}
			postCtrl.generate(authMb.getCurrentUser(), content, img);
			content = null;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se a podido postear el contenido",
					null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public List<Post> obtain(){
		try{
			return postCtrl.obtain(currentUser);
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		
	}
	
	public List<Post> obtainAll(){
		try{
			return postCtrl.obtainAll();
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		
	}
	
	public void like(Post p) {
		likeCtrl.like(authMb.getCurrentUser(), p);

	}
	
	public String amount(Post p) {
		return likeCtrl.amount(p) + "";
	}
	
	public List<Post> followPostList() {
		try {
			return postCtrl.obtain(follower.getUser());
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

	public PostController getPostCntr() {
		return postCtrl;
	}

	public void setPostCntr(PostController postCntr) {
		this.postCtrl = postCntr;
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
