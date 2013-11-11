package puma.sp.mgmt.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import puma.sp.mgmt.model.user.SessionRequest;

public interface SessionRequestRepository extends JpaRepository<SessionRequest, Long> {
	
}
