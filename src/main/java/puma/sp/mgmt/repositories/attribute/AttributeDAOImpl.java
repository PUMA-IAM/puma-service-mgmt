package puma.sp.mgmt.repositories.attribute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AttributeDAOImpl implements AttributeDAO {
	
	@SuppressWarnings("unused")
	@PersistenceContext
	private EntityManager em;
}
