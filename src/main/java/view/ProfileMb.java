package view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import model.Image;
import model.User;
import controller.ImageController;
import controller.UserController;

@Named
public class ProfileMb {
	
	@Inject
	UserController userController;
	
	@Inject
	ImageController imgController;
	
	@Inject
	AuthMb authMb;
	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private Part file;

	private User user = new User();
	
	public String saveChanges(){
		
		boolean errorCarga = false;
						
		user.setEmail(authMb.getCurrentUser().getEmail());
		user.setUsername(authMb.getCurrentUser().getUsername());
		user.setId(authMb.getCurrentUser().getId());
		if(oldPassword.length() > 0 && newPassword.length() > 0){
			if(userController.getAuthUser(authMb.getCurrentUser().getUsername(), oldPassword) != null){
				if(newPassword.equals(confirmPassword)){
					user.setPassword(newPassword);
				}else{					
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La nueva clave y su confirmacion no coinciden.", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					errorCarga = true;
				}
			}else{		
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La clave anterior es incorrecta.", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				errorCarga = true;
			}
		}
		
		if(file != null && file.getSize() > 0){
			try{
				Image img = null;
				if(file.getContentType().startsWith("image/")){
					img = imgController.upload(file);
					user.setImage(img);
				}
			} catch (Exception e){
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo cargar la foto, reintente.", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				errorCarga = true;
			}	
		}else{
			user.setImage(authMb.getCurrentUser().getImage());
		}
								
		if(!errorCarga){
			userController.update(user);
			authMb.setCurrentUser(user);
			return "index";
		}else{
			return null;
		}
		
	}
	
	
	public String cancelChanges(){
		return "index";
	}	

	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Part getFile(){
		return file;
	}
	
	public void setFile(Part file){
		this.file = file;
	}
	

}