package puma.sp.mgmt.repositories.policy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.model.policy.Policy;

@Repository
public class PolicyDAOImpl implements PolicyDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Policy> getPolicies(Organization organization) {
		TypedQuery<Policy> query = em.createNamedQuery("Policy.byOrganization", Policy.class);
		query.setParameter("organization", organization);
		return query.getResultList();
	}

}
