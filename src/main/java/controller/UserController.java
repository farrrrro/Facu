package controller;

import javax.ejb.Stateless;
import javax.inject.Inject;

import db.DataBase;
import model.User;

@Stateless
public class UserController {

	@Inject
	private DataBase db;
	
	public User getAuthUser(String email,String password){
		for(User user : db.users){
			if (user.getEmail().equals(email) 
			 && user.getPassword().equals(password)){
					return user;
			}
		}
		return null;
	}
	
	public boolean userNameExist(String username){
		for(User user : db.users){
			if (user.getUsername().equals(username)){
					return true;
			}
		}
		return false;
	}
	
	public void register(User user){
		if(userNameExist(user.getUsername())){
			throw new RuntimeException("Usuario ya existe");
		}
		user.setId(db.nextUserId());
		db.users.add(user);
	}
}
