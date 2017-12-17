package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.User;

public class FollowController {

	public void addFollow(User followed, User user){
		Set<User> following = getFollowing(user);
		if(following==null)
			following = new HashSet<User>();
		following.add(followed);
		user.setFollowing(following);
		entityManager.merge(user);				
	}
	
	public Set<User> getFollowing(User u){
		User userDB = entityManager.find(User.class, u.getID());
		if(userDB.getFollowing()!=null){
			return userDB.getFollowing();
		}else{
			return null;
		}
	}
	
}
