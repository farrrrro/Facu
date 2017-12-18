package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Likes;
import model.Post;
import model.User;

@Stateless
public class LikeController {

	@PersistenceContext
	private EntityManager entityManager;

	public void like(User user, Post post) {
		Likes l = new Likes();
		l.setPost(post);
		l.setUser(user);
		TypedQuery<Likes> q = entityManager.createQuery("Select l from Likes l where l.user =:user and l.post = :post",
				Likes.class);
		q.setParameter("user", user);
		q.setParameter("post", post);
		entityManager.getTransaction().begin();
		if (q.getResultList().equals(null)) {
			entityManager.persist(l);
			String hql = "UPDATE Post SET likes=likes + 1 WHERE post=?1";
		} else {
			entityManager.remove(q.getSingleResult());
			entityManager.persist(l);
			String hql = "UPDATE Post SET likes=likes - 1 WHERE post=?1";
		}
		int executeUpdate = entityManager.createQuery(hql).setParameter(1, post).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public int amount(Post post) {
		TypedQuery<Likes> q = entityManager.createQuery("Select l from Likes l where l.post = :post", Likes.class);
		q.setParameter("post", post);
		return q.getResultList().size();
	}
}
