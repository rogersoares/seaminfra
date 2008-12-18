package test.seam.action;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import test.biz.User;

@Name("userAction")
public class UserAction {

	@In
	EntityManager em;

	private int id;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		user = em.find(User.class, id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
