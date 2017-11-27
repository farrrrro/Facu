package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.User;

@Stateless
public class UserController {

   @PersistenceContext
   private EntityManager entityManager;

   public void addUser(User user){
     entityManager.persist(user);
   }
	public User getAuthUser(String username,String password){
		String jpql = "Select u from User u where u.username = :username and u.password = :password";
		TypedQuery<User> q = entityManager.createQuery(jpql, User.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		return q.getSingleResult();
	}
	
	public boolean userNameExist(String username){
		if(entityManager.find(User.class, username).equals("")){
			return true;
		}else {
			return false;
		}
	}
	
	public void register(User user){		
		if(userNameExist(user.getUsername())){
			entityManager.persist(user);
		}
		else{
			throw new RuntimeException("Usuario ya existe");
		}
	}
}
