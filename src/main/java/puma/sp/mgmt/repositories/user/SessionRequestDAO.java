package puma.sp.mgmt.repositories.user;

import puma.sp.mgmt.model.user.SessionRequest;

public interface SessionRequestDAO {
	public SessionRequest bySessionId(String id);
}
