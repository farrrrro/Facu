package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.UserController;
import model.User;

@Named
@SessionScoped
public class AuthMb implements Serializable {
	private static final long serialVersionUID = 791515424619865689L;

	@Inject
	private UserController userCntr;

	private String email;
	private String password;
	private String username;
	
	private User currentUser;
	
	public boolean isLogged(){
		return currentUser != null;
	}
	
	public String loggin(){
		currentUser = userCntr.getAuthUser(email, password);
		email = null;
		password = null;
		if(isLogged())
			return "home?faces-redirect=true";
		else 
			return null;
	}
	
	public String logout(){
		currentUser = null;
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
}
