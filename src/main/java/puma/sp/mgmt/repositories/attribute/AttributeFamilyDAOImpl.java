package puma.sp.mgmt.repositories.attribute;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;

@Repository
public class AttributeFamilyDAOImpl implements AttributeFamilyDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AttributeFamily> findAllOrganization(Organization org) {
		if (org == null) {
			throw new RuntimeException("Could not execute query: null pointer organization");
		}
		TypedQuery<AttributeFamily> query = null;
		query = em.createNamedQuery("AttributeFamily.byOrganization", AttributeFamily.class);
		query.setParameter("organization", org);
		return query.getResultList();
	}

	@Override
	public List<AttributeFamily> byName(String xacmlIdentifier) {
		if (xacmlIdentifier == null) {
			throw new RuntimeException("Could not execute query: null pointer xacml id");
		}
		TypedQuery<AttributeFamily> query = null;
		query = em.createNamedQuery("AttributeFamily.byOrganization", AttributeFamily.class);
		query.setParameter("name", xacmlIdentifier);
		return query.getResultList();
	}	
}
