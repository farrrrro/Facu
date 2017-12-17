package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Follower;
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
	
	public boolean changePassword(String username, String newPassword){
		entityManager.getTransaction().begin();
		String hql = "UPDATE User SET password=?1 WHERE username=?2";
		int executeUpdate = entityManager.createQuery(hql).setParameter(1, newPassword).setParameter(2, username).executeUpdate();
		entityManager.getTransaction().commit();
	    entityManager.close();
	    if(executeUpdate > 1)
	    	return true;
		
		return false;
	}
	
	public void update(User user){
		entityManager.merge(user);
	}
	
	public User follow(User me, String follower) {
		String jpql = "Select u from User u where u.name = :username ";
		TypedQuery<User> q = entityManager.createQuery(jpql, User.class);
		q.setParameter("username", follower);
		if (q.getSingleResult().getUsername().equals(follower)) {
			Follower f = new Follower();
			f.setMe(me);
			f.setOtherUser(q.getSingleResult());
			entityManager.persist(f);
		}
		return q.getSingleResult();
	}
}
