package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.User;

@Named
@RequestScoped
public class FollowerMb implements Serializable {

	private static final long serialVersionUID = -4803205115493366016L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}