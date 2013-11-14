package puma.sp.mgmt.repositories.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.policy.Policy;
import puma.sp.mgmt.model.policy.PolicyType;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	private static final String APPLICATION_POLICY_ID = "application-policy";

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
	
	
}
