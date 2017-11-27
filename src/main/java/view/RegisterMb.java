package view;


import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import controller.UserController;
import model.User;

@Named
public class RegisterMb {

	@Inject
	private UserController userCntr;
	
	private User user = new User();
	
	@NotNull
	private String confirmPass;
	
	

	public String register(){
		try {
			if(!confirmPass.equals(user.getPassword())){
				return "Contraseñas no coinciden";
			}
			userCntr.addUser(user);
			user = null;
			return "login?faces-redirect=true";
		} catch (Exception e) {
			return "Ha ocurrido un error";
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
	
}
