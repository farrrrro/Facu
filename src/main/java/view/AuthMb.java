package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import controller.UserController;
import model.User;

@Named
@SessionScoped
public class AuthMb implements Serializable {
	
	private static final long serialVersionUID = 791515424619865689L;

	@Inject
	private UserController userCntr;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	private User currentUser;
	
	public boolean isLogged(){
		return currentUser != null;
	}
	
	public String loggin(){
		currentUser = userCntr.getAuthUser(username, password);
		username = null;
		password = null;
		if(isLogged())
			return "home";
		else 
			return "login";
	}
	
	public String logout(){
		return "login?faces-redirect=true";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
