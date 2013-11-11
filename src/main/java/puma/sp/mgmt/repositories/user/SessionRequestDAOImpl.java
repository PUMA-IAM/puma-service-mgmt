package puma.sp.mgmt.repositories.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.user.SessionRequest;

@Repository
public class SessionRequestDAOImpl implements SessionRequestDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SessionRequest bySessionId(String id) {
		TypedQuery<SessionRequest> query = em.createNamedQuery("SessionRequest.bySessionId", SessionRequest.class);
		query.setParameter("id", id);
		if (query.getResultList().isEmpty())
			return null;
		if (query.getResultList().size() != 1)
			throw new RuntimeException("Could not find a unique session request for id " + id + ": multiple exist!");
		return query.getResultList().get(0);
	}

}
