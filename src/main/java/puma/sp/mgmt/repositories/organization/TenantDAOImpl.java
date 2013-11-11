package puma.sp.mgmt.repositories.organization;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Tenant;

@Repository
public class TenantDAOImpl implements TenantDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Tenant byName(String name) {
		TypedQuery<Tenant> query = em.createNamedQuery("Tenant.byName", Tenant.class);
		query.setParameter("name", name);
		List<Tenant> results = query.getResultList();
		if (results.isEmpty())
			return null;
		if (results.size() > 1)
			throw new RuntimeException("Multiple tenants with name " + name + " found!");
		return results.get(0);
	}

	@Override
	public List<Tenant> subtenants(Tenant tenant) {
		TypedQuery<Tenant> query = em.createNamedQuery("Tenant.subtenants", Tenant.class);
		query.setParameter("tenant", tenant);
		return query.getResultList();
	}
}
