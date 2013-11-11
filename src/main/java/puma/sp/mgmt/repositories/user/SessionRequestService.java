package puma.sp.mgmt.repositories.user;

import puma.sp.mgmt.model.user.SessionRequest;
public interface SessionRequestService {
	public void addSessionRequest(SessionRequest request);
	public void deleteSessionRequest(Long requestId);
	public SessionRequest bySessionId(String id);
}
