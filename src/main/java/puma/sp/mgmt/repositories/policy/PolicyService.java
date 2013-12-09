package puma.sp.mgmt.repositories.policy;

import java.util.List;

import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.model.policy.Policy;

public interface PolicyService {

	public Policy getApplicationPolicy();

	public void storeApplicationPolicy(String contents);
	
	public Policy getCentralPUMAPDPPolicy();
	
	public void storeCentralPUMAPDPPolicy(String contents);
	
	public List<Policy> getPolicies(Organization organization);
	
	public void storePolicy(Policy policy);
	
	public void removePolicy(String policyId);
}
