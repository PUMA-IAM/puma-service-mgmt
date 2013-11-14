package puma.sp.mgmt.repositories.policy;

import org.springframework.data.jpa.repository.JpaRepository;

import puma.sp.mgmt.model.policy.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {
	
}
