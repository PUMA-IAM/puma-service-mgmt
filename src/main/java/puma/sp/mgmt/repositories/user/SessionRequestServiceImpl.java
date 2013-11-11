package puma.sp.mgmt.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.user.SessionRequest;

@Service
public class SessionRequestServiceImpl implements SessionRequestService {
	@Autowired
	private SessionRequestRepository repository;
	
	@Autowired
	private SessionRequestDAO dao;
	
	@Override
	public void addSessionRequest(SessionRequest request) {
		this.repository.saveAndFlush(request);
	}

	@Override
	public void deleteSessionRequest(Long requestId) {
		this.repository.delete(requestId);
	}

	@Override
	public SessionRequest bySessionId(String id) {
		return this.dao.bySessionId(id);
	}

}
