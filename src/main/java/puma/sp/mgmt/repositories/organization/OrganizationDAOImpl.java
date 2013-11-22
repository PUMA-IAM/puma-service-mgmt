package puma.sp.mgmt.repositories.organization;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Organization;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Organization byName(String name) {
		TypedQuery<Organization> query = em.createNamedQuery("Organization.byName", Organization.class);
		query.setParameter("name", name);
		List<Organization> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		}
		if (results.size() > 1) {
			throw new RuntimeException("Multiple organizations with name " + name + " found!");
		}
		return results.get(0);
	}
}
