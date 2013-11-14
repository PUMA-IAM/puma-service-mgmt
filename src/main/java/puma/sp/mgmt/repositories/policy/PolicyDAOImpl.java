package puma.sp.mgmt.repositories.policy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PolicyDAOImpl implements PolicyDAO {

	@PersistenceContext
	private EntityManager em;

}
