package puma.sp.mgmt.repositories.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.model.policy.Policy;
import puma.sp.mgmt.model.policy.PolicyType;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	private static final String APPLICATION_POLICY_ID = "application-policy";
	
	private static final String CENTRAL_PUMA_PDP_POLICY_ID = "central-puma-policy";

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private PolicyDAO policyDAO;

	@Override
	public Policy getApplicationPolicy() {
		return policyRepository.findOne(APPLICATION_POLICY_ID);
	}

	@Override
	public void storeApplicationPolicy(String contents) {
		Policy ap = policyRepository.findOne(APPLICATION_POLICY_ID);
		if(ap == null) {
			ap = new Policy(APPLICATION_POLICY_ID, PolicyType.APPLICATION, null, contents);
		}
		ap.setContent(contents);
		policyRepository.save(ap);
	}

	@Override
	public Policy getCentralPUMAPDPPolicy() {
		return policyRepository.findOne(CENTRAL_PUMA_PDP_POLICY_ID);
	}

	@Override
	public void storeCentralPUMAPDPPolicy(String contents) {
		Policy cpp = policyRepository.findOne(CENTRAL_PUMA_PDP_POLICY_ID);
		if(cpp == null) {
			cpp = new Policy(CENTRAL_PUMA_PDP_POLICY_ID, PolicyType.MIDDLEWARE, null, contents);
		}
		cpp.setContent(contents);
		policyRepository.save(cpp);
	}

	@Override
	public List<Policy> getPolicies(Organization organization) {
		return this.policyDAO.getPolicies(organization);
	}

	@Override
	public void storePolicy(Policy policy) {
		this.policyRepository.save(policy);
	}

	@Override
	public void removePolicy(String policyId) {
		this.policyRepository.delete(policyId);
	}
	
	
}
