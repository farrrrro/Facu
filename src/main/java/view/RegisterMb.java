package view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.servlet.http.Part;

import controller.UserController;
import controller.ImageController;
import model.User;
import model.Image;

@Named
public class RegisterMb {

	@Inject
	private UserController userCntr;
	
	private User user = new User();
	
	@NotNull
	private String confirmPass;
	
	@Inject
	ImageController imageController;

	
	private Part file;

	public String register(){
		try {
			if(!confirmPass.equals(user.getPassword())){
				//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseñas no coniciden", null);
				//FacesContext.getCurrentInstance().addMessage(null, msg);
				return null;
			}
			/*if(file != null && file.getSize() > 0){
				try{
					Image img = null;
					if(file.getContentType().startsWith("image/")){
						img = imageController.upload(file);
						user.setImage(img);
					}
				} catch (Exception e){
					e.printStackTrace();
					//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo cargar la foto, reintente.", null);
					//FacesContext.getCurrentInstance().addMessage(null, msg);
				}	
			}*/
			userCntr.addUser(user);
			user = null;
			//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró el usuario", null);
			//FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error interno", null);
			//FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
