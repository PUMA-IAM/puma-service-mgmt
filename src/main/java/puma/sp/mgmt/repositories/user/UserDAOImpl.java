package puma.sp.mgmt.repositories.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User byNameTenant(String name, Tenant tenant) {
		TypedQuery<User> query = null;
		if (tenant != null) {
			query = em.createNamedQuery("User.byNameTenant", User.class);
			query.setParameter("tenant", tenant);
		} else {
			query = em.createNamedQuery("User.byNameTenantNULL", User.class);
		}		
		query.setParameter("name", name);
		List<User> results = query.getResultList();
		if (results.size() > 1)
			throw new RuntimeException("Multiple users found with name " + name + " with tenant " + tenant==null?"null":tenant.getName() + ".");
		if (results.isEmpty())
			return null;
		return results.get(0);
	}

	@Override
	public List<User> byTenant(Tenant tenant) {
		TypedQuery<User> query = em.createNamedQuery("User.byTenant", User.class);
		query.setParameter("tenant", tenant);
		return query.getResultList();
	}

}
