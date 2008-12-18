package test.seam.action;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.security.Identity;

import test.biz.Profile;
import test.biz.User;

@Name("authenticator")
public class AuthenticatorAction {

	@In
	EntityManager em;

	@In
	Identity identity;

	@Out(required=false, scope = ScopeType.SESSION)
	private User user;

	public boolean authenticate() {
		List results = em.createQuery("select u from User u where u.username=:username " +
				"and u.password=:password")
			.setParameter("username", identity.getCredentials().getUsername())
			.setParameter("password", identity.getCredentials().getPassword())
			.getResultList();

		if (results.size() == 0) {
			return false;
		} else {
			user = (User) results.get(0);
			for (Profile p : user.getProfileList()) {
				identity.addRole(p.getDescription());
			}
			return true;
		}
	}

}
