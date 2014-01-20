package puma.sp.mgmt.repositories.user.group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@SuppressWarnings("unused")
	@PersistenceContext
	private EntityManager em;
}
